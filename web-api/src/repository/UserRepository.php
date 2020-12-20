<?php

require_once "src/model/User.php";


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


        $query = "SELECT * FROM User";
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

    public function create($user)
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

        $query = "INSERT INTO User (id,name, email, usernane, birthday) VALUES ($user_id, '$user_name', '$user_email', '$user_username', '$user_birthday')";
        $result = $connection->query($query);

        if (!$result) die($connection->error);

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