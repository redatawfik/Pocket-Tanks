<?php
require_once "../controller/UserController.php";

//get the json request
$data = json_decode(file_get_contents('php://input'), true);

if($data == null){
    http_response_code(405);
}
else {
    if(!isset($data['email']) || !isset($data['password'])) {
        http_response_code(400);
    }
    else {
        //parse request data
        $email = $data['email'];
        $password = $data['password'];

        $response = null;
        $m = null;

        //check email format
        preg_match('/[a-z0-9_\-\+\.]+@[a-z0-9\-]+\.([a-z]{2,4})(?:\.[a-z]{2})?/i', $email, $m);

        if (sizeof($m) < 1) {
            http_response_code(201);
            $response = array('Status' => 'Error',
                'Msg' => 'Email format Not valid');
        } else {
            $user_controller = new UserController();
            $res = $user_controller->loginUser($email, $password);
            if (!$res)
                $response = array('Status' => 'Error',
                    'Msg' => 'Wrong data');
            else {
                $response = array('Status' => 'Success',
                    'id' => $res[0][0],
                    'username' => $res[0][1],
                    'email' => $res[0][2],
                    'birthdate' => $res[0][4]);
            }
        }
        if ($response)
            echo json_encode($response);
    }
}