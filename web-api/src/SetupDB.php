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
$db_name = "pocket_tank";
$conn = new mysqli($db_hostname, $db_username, $db_password, $db_name);
$sql = "CREATE TABLE Users (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(30) NOT NULL,
email VARCHAR(245) NOT NULL,
passwordhash VARCHAR(128),
birthdate DATE NOT NULL
)";


if ($conn->query($sql) === TRUE) {
    echo "Table Users created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
