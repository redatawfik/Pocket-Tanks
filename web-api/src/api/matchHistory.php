<?php

require_once '../Controllers/matchController.php';
require_once '../Models/User.php';
require_once  '../Models/Match.php';

session_start();
if (isset($_SESSION['userid'])) {
    $id = $_SESSION['userid'];

    $matchController = new matchController();
    $result = array();

    foreach ($matchController->getUserMatches($id) as $match) {
        $result[] = json_decode($match->toJson());
    }
    echo json_encode($result);
} else {
    http_response_code(400);
}
