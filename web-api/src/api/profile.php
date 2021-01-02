<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';

session_start();
//get the json request
if(isset($_SESSION['userid'])){
    $id = $_SESSION['userid'];
    $data = json_decode(file_get_contents('php://input'), true);
    $userController = new userController();
if($data == null){
    echo $userController->get($id)->toJson();
}
else {
    $user = new User();
    $user->setId($id);
    if(isset($data['username']))
        $user->setUsername($data['username']);
    if(isset($data['email']))
        $user->setEmail($data['email']);
    if(isset($data['imageUrl']))
        $user->setImageURL($data['imageUrl']);
    $userController->update($user);
}
}else{
    http_response_code(400);
}


