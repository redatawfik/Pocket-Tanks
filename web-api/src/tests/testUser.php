<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';

if(isset($_GET['id'])) {
    $id = $_GET['id'];

    $user = new User();
    $user->setId($id);

    $userController = new userController();
    //echo $userController->get($user)->toJson();

    $result = array();
    $users = $userController->getAll();
    foreach ($users as $userInfo){
       $result[] = json_decode($userInfo->toJson());
    }
    echo json_encode($result);

   // echo $userController->login('test@gmail.com', '1234');
   /* $u = new User();
    $u->setEmail("gtgdgfdg@gdfgdfg.com");
    $u->setUsername("bghju");

    $userController->register($u, '1234');*/
}