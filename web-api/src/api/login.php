<?php
require_once '../Controllers/userController.php';
require_once '../Models/User.php';


//get the json request
$data = json_decode(file_get_contents('php://input'), true);

if($data == null){
    http_response_code(405);
}
else{
    if(!isset($data['email']) || !isset($data['password'])) {
        http_response_code(400);
    }
    else {
        //parse request data
        $email = $data['email'];
        $password = $data['password'];

        $m = null;
        //check email format
        preg_match('/[a-z0-9_\-\+\.]+@[a-z0-9\-]+\.([a-z]{2,4})(?:\.[a-z]{2})?/i', $email, $m);

        if (sizeof($m) < 1) {
            http_response_code(400);
            $response = array('Status' => 'Error',
                'Msg' => 'Email format Not valid');
            echo json_encode($response);
        } else {
            $user_controller = new UserController();
            $user = $user_controller->login($email, $password);
            if($user) {
                ini_set('session.gc_maxlifetime', 1800);
                ini_set('session.cookie_httponly', 1);
                ini_set('session.cookie_secure', 1);
                session_start();
                $_SESSION['userid'] = $user->getId();
            }
        }

    }
}