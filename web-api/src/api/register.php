<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';


//get the json request
$data = json_decode(file_get_contents('php://input'), true);

if($data == null){
    http_response_code(405);
}
else{
    if(!isset($data['email']) || !isset($data['password']) ||!isset($data['username'])) {
        http_response_code(400);
    }
    else {
        $email = $data['email'];
        $username = $data['username'];
        $password = $data['password'];

        $m = null;
        //check email format
        preg_match('/[a-z0-9_\-\+\.]+@[a-z0-9\-]+\.([a-z]{2,4})(?:\.[a-z]{2})?/i', $email, $m);

        if (sizeof($m) < 1) {
            http_response_code(400);
            $response = array('Status' => 'Error', 'Msg' => 'Email format Not valid');
            echo json_encode($response);
        } else {
            $user = new User();
            $user->setEmail($email);
            $user->setUsername($username);

            $user_controller = new UserController();
            $res = $user_controller->register($user, $password);

        }

    }
}