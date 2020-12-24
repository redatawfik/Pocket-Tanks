<?php
require_once "../controller/UserController.php";
require_once "../model/User.php";

//get the json request
$data = json_decode(file_get_contents('php://input'), true);
if($data == null){
    http_response_code(404);
}
else {
    if(!isset($data['email']) || !isset($data['password']) ||!isset($data['username'])) {
        http_response_code(400);
    }
    else {
        $email = $data['email'];
        $username = $data['username'];
        $password = $data['password'];

        $response = null;
        $m = null;
        //check email format
        preg_match('/[a-z0-9_\-\+\.]+@[a-z0-9\-]+\.([a-z]{2,4})(?:\.[a-z]{2})?/i', $email, $m);

        if (sizeof($m) < 1) {
            $response = array('Status' => 'Error', 'Msg' => 'Email format Not valid');
        } else {

            $user = new User();
            $user->setEmail($email);
            $user->setUsername($username);
            $user->setPasswdhash($password);

            $user_controller = new UserController();
            $res = $user_controller->Create($user);
            if ($res == null)
                $response = array('status' => 'error', 'msg' => 'Already registered');
            else {
                http_response_code(201);
            }
        }
        if ($response)
            echo json_encode($response);
    }
}