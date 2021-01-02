<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';

$userController = new userController();

$result = array();
$users = $userController->getAll();
for($i = 0; $i < 10; $i++){
    $result[] = json_decode($users[$i]->toJson());
}

echo json_encode($result);
