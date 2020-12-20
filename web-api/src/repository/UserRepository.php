<?php

require_once "../model/User.php";


class UserRepository
{

    public function getAll()
    {
        $db_hostname = 'localhost';
        $db_database = 'pocket_tank';
        $db_username = 'root';
        $db_password = '';

        $connection = new mysqli($db_hostname, $db_username, $db_password, $db_database);
        if ($connection->connect_error) die($connection->connect_error);


        $query = "SELECT * FROM users";
        $result = $connection->query($query);
        if (!$result) die($connection->error);

        $arr_user = array();
        $rows = $result->num_rows;

        for ($j = 0; $j < $rows; ++$j) {
            $user = new User();
            $result->data_seek($j);
            $user->setName($result->fetch_assoc()['name']);
            $result->data_seek($j);
            $user->setBirthday($result->fetch_assoc()['birthday']);
            $result->data_seek($j);
            $user->setEmail($result->fetch_assoc()['email']);
            $result->data_seek($j);
            $user->setUsername($result->fetch_assoc()['username']);
            $arr_user[] = $user;
        }

        $result->close();
        $connection->close();
        return $arr_user;
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

    public function login($username, $password){
        $db_hostname = 'localhost';
        $db_database = 'pocket_tank';
        $db_username = 'root';
        $db_password = '';
        $connection = new mysqli($db_hostname, $db_username, $db_password, $db_database);
        if ($connection->connect_error) die($connection->connect_error);

        $sql ='SELECT * FROM users WHERE email = ? and passwordhash = ?';
        $params = [$username, md5($password)];
        $types = "ss";
        $sql = $this->mysqli_query_params($connection, $sql, $params, $types);

        $result = $sql->get_result();
        if(!$result) return null;
        return $result->fetch_all();

    }

    public function create($user)
    {
        $db_hostname = 'localhost';
        $db_database = 'pocket_tank';
        $db_username = 'root';
        $db_password = '';

        $connection = new mysqli($db_hostname, $db_username, $db_password, $db_database);
        if ($connection->connect_error) die($connection->connect_error);
        $user_email = $user->getEmail();
        $user_username = $user->getUsername();
        $user_password = $user->getPasswdhash();
        $query = "SELECT * FROM users WHERE username= ? OR email= ? LIMIT 1";
        $params = [$user_username, $user_email];
        $types = "ss";
        $query = $this->mysqli_query_params($connection, $query, $params, $types);
        $result = $query->get_result();

        if($result->num_rows == 0) {
            $query = "INSERT INTO users (email, username, passwordhash) VALUES (?, ?, ?)";
            $params = [$user_email,$user_username, md5($user_password)];
            $types = "sss";
            $query = $this->mysqli_query_params($connection, $query, $params, $types);
            $query->get_result();
            return true;
        }
        else{
            return null;
        }

        $connection->close();
    }

    public function update($user)
    {
        $db_hostname = 'localhost';
        $db_database = 'pocket_tank';
        $db_username = 'root';
        $db_password = '';

        $connection = new mysqli($db_hostname, $db_username, $db_password, $db_database);
        if ($connection->connect_error) die($connection->connect_error);


        $user_id = $user->getId();
        $user_name = $user->getName();
        $user_email = $user->getEmail();
        $user_username = $user->getUsername();
        $user_birthday = $user->getBirthday();

        $query = "UPDATE Uer SET name = '$user_name', email= '$user_email', username = '$user_name' WHERE id = $user_id";
        $result = $connection->query($query);

        if (!$result) die($connection->error);

        $connection->close();
    }

    public function delete($user_id)
    {
        $db_hostname = 'localhost';
        $db_database = 'pocket_tank';
        $db_username = 'root';
        $db_password = '';

        $connection = new mysqli($db_hostname, $db_username, $db_password, $db_database);
        if ($connection->connect_error) die($connection->connect_error);


        $query = "DELETE FROM User WHERE id = $user_id";
        $result = $connection->query($query);

        if (!$result) die($connection->error);

        $connection->close();
    }
}