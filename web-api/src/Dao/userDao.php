<?php
require_once '../Core/dbContext.php';
require_once '../Models/User.php';

class userDao extends dbContext
{
    private mysqli $connection;

    public function __construct(){
        $this->connection = self::dbContextBuilder('127.0.0.1',
        'root',
        '',
        'pocket_tank');
    }

    //prepared statements and parameterized queries to prevent sqli attacks
    function mysqli_query_params($mysqli, $query, $params, $types = NULL)
    {
        $statement = $mysqli->prepare($query);
        $types = $types ?: str_repeat('s', count($params));
        $statement->bind_param($types, ...$params);
        $statement->execute();
        return $statement;
    }

    public function get($id){
        $query = "SELECT * FROM users where id= ?";
        $params = [$id];
        $types = "i";
        $mysqlExecutor =$this->mysqli_query_params($this->connection,
        $query,
        $params,
        $types);

        $result = $mysqlExecutor->get_result();
        if(!$result) return null;
        $xx = $result->fetch_all();
        if(!empty($xx)) {
            $result = $xx[0];
            $user = User::Build($result[0], $result[2], $result[1]);
            $this->connection->close();
            $user->setScore($result[4]);
            return $user;
        }
        return null;
    }

    public function getAll(){
        $query = "SELECT * FROM users ORDER BY score DESC;";
        $result = $this->connection->query($query);
        //print_r($result);
        if(!$result) return null;
        $result =  $result->fetch_all();
        //print_r($result);
        $users = [];
        foreach ($result as $userInfo){
            $user = User::buildWithAll($userInfo[0], $userInfo[2], $userInfo[1], $userInfo[4], $userInfo[5]);
            $users[] = $user;
        }
        $this->connection->close();
        return $users;
    }

    public function update($user){
        $query = "UPDATE users SET username = ?, email=?, imageUrl=?, Score=? WHERE id=?";

        $params = [$user->getUsername(),
            $user->getEmail(),
            null,
            $user->getScore(),
            $user->getID()];
        $types = "sssii";
        $mysqlExecutor =$this->mysqli_query_params($this->connection,
            $query,
            $params,
            $types);
        $result = $mysqlExecutor->get_result();
        if(!$result) return null;
        $this->connection->close();
    }

    public function login($email, $password){
        $sql ='SELECT * FROM users WHERE email = ? and passwordhash = ?';
        $params = [$email, md5($password)];
        $types = "ss";
        $sql = $this->mysqli_query_params($this->connection, $sql, $params, $types);
        $result = $sql->get_result();
        if($result->num_rows == 0){
            http_response_code(401);
            header('Content-Type: application/json');
            echo json_encode(array("Status" => "Error", "Msg" => 'Wrong info'));
        }else {
            $result = $result->fetch_all()[0];
            $user = User::Build($result[0], $result[2], $result[1]);
            $this->connection->close();
            return $user;
        }
    }

    public function register($user, $password){
        $query = "SELECT * FROM users WHERE username= ? OR email= ? LIMIT 1";
        $params = [$user->getUsername(), $user->getEmail()];
        $types = "ss";
        $query = $this->mysqli_query_params($this->connection, $query, $params, $types);
        $result = $query->get_result();

        if($result->num_rows == 0) {
            $query = "INSERT INTO users (email, username, passwordhash) VALUES (?, ?, ?)";
            $params = [$user->getEmail(), $user->getUsername(), md5($password)];
            $types = "sss";
            $query = $this->mysqli_query_params($this->connection, $query, $params, $types);
            $query->get_result();
            http_response_code(201);
        }
        else{
            http_response_code(409);
            header('Content-Type: application/json');
            echo json_encode(array("Status" => "Error", "Msg" => 'this Username or Email already taken'));
        }

        $this->connection->close();
    }

}