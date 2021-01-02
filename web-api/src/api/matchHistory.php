<?php

require_once '../Controllers/matchController.php';
require_once '../Controllers/userController.php';

require_once '../Models/User.php';
require_once  '../Models/Match.php';

session_start();
if (isset($_SESSION['userid'])) {
    $id = $_SESSION['userid'];

    $matchController = new matchController();
    $result = array();


    foreach ($matchController->getUserMatches($id) as $match) {
        $userContoller = new userController();
        $winner = $userContoller->get($match->getWinnerId());
        if($winner) {
            $winner = $winner->getUsername();
            $looser = $userContoller->get($match->getLooserId());
            if ($looser) {
                $looser = $looser->getUsername();
                $x = array("winner_uname" => $winner, "looser_uname" => $looser,
                    "winner_score" => $match->getWinnerScore(),
                    "looser_score" => $match->getLooserScore());
            }
        }

        $result[] = $x;
    }
    header('Content-Type: application/json');
    echo json_encode($result);
} else {
    http_response_code(400);
}
