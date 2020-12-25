<?php
$db_hostname = 'localhost';
$db_username = 'root';
$db_password = '';

// Create connection
$conn = new mysqli($db_hostname, $db_username, $db_password);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Create database
$sql = "CREATE DATABASE pocket_tank";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully";
} else {
    echo "Error creating database: " . $conn->error;
}

$conn->close();

//add tables
$mysql_host = "localhost";
$mysql_database = "pocket_tank";
$mysql_user = "root";
$mysql_password = "";
# MySQL with PDO_MYSQL
$db = new PDO("mysql:host=$mysql_host;dbname=$mysql_database", $mysql_user, $mysql_password);

$query = file_get_contents("pocket_tank.sql");

$stmt = $db->prepare($query);

if ($stmt->execute()){
    echo "Success";
}else{
    echo "Fail";
}