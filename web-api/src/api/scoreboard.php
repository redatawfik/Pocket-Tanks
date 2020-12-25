<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';

$userController = new userController();

$result = array();
$users = $userController->getAll();
foreach ($users as $userInfo){
    $result[] = json_decode($userInfo->toJson());
}
echo json_encode($result);
