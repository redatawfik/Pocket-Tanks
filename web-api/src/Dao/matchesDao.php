<?php
require_once '../Core/dbContext.php';
require_once '../Models/Match.php';

class matchesDao extends dbContext
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
        $query = "SELECT * FROM matches where id= ?";
        $params = [$id];
        $types = "i";
        $mysqlExecutor =$this->mysqli_query_params($this->connection,
            $query,
            $params,
            $types);

        $matchInfo = $mysqlExecutor->get_result();
        if(!$matchInfo) return null;
        $matchInfo =  $matchInfo->fetch_all()[0];
        $match = Match::Build($matchInfo[0], $matchInfo[1], $matchInfo[2], $matchInfo[3], $matchInfo[4]);
        $this->connection->close();
        return $match;
    }
    public function getUserMatches($user_id){
        $query = "SELECT * FROM matches where winner_id= ? or looser_id = ?";
        $params = [$user_id, $user_id];
        $types = "ii";
        $mysqlExecutor =$this->mysqli_query_params($this->connection,
            $query,
            $params,
            $types);

        $matchInfo = $mysqlExecutor->get_result();
        if(!$matchInfo) return null;
        $result =  $matchInfo->fetch_all();

        foreach ($result as $matchInfo)
            $matches[] = Match::Build($matchInfo[0], $matchInfo[1], $matchInfo[2], $matchInfo[3], $matchInfo[4]);

        $this->connection->close();
        return $matches;
    }
    public function getAll(){
        $query = "SELECT * FROM matches";
        $result = $this->connection->query($query);

        if(!$result) return null;
        $result =  $result->fetch_all();

        $matches = [];
        foreach ($result as $matchInfo)
            $matches[] = Match::Build($matchInfo[0], $matchInfo[1], $matchInfo[2], $matchInfo[3], $matchInfo[4]);

        $this->connection->close();
        return $matches;
    }


}