<?php
require_once '../Controllers/matchController.php';
require_once '../Models/Match.php';
require_once '../Controllers/userController.php';
require_once '../Models/User.php';


//get the json request
$data = json_decode(file_get_contents('php://input'), true);

if($data == null){
    http_response_code(405);
}
else{
    if(!isset($data['user1']) || !isset($data['user2']) || !isset($data['score1']) || !isset($data['score2'])) {
        http_response_code(400);
    }
    else {
        //parse request data
        $name1 = $data['user1'];
        $name2 = $data['user2'];

        //create 2 user objects
        $user_controller = new UserController();
        $user1 = new User();
        $user2 = new User();
        $users = $user_controller->getAll();
        $setted2 = false;
        $setted1 = false;

        foreach ($users as $user){
            $currentName = $user->getUsername();
            if($currentName === $name1 && !$setted1){
                $user1 = $user;
                $user1->setScore($data['score1']);
                $setted1 = true;
            }
            else if($currentName === $name2 && !$setted2){
                $user2 = $user;
                $user2->setScore($data['score2']);
                $setted2 = true;
            }
        }

        //add match to the db
        if($user1->getUsername() === $name1 && $user2->getUsername() === $name2){
            $matchController = new matchController();
            $winnerID = $user1->getScore() > $user2->getScore() ? $user1->getId() : $user2->getId();
            $looserID = $user1->getScore() < $user2->getScore() ? $user1->getId() : $user2->getId();
            $winnerScore = max($user1->getScore(), $user2->getScore());
            $looserScore = min($user1->getScore(), $user2->getScore());
            $match = Match::Build($winnerID, $looserID, $winnerScore, $looserScore);
            $match = $matchController->create($match);
            //add win points to the winner
            $winner = $user_controller->get($winnerID);
            echo $winnerID;
            $winner->setScore($winner->getScore() + 5);
            $user_controller->update($winner);
        }


    }
}