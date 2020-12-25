<?php
require_once '../Core/Controller.php';
require_once '../Services/userService.php';
require_once '../Services/authService.php';
class userController extends Controller
{

    public function getAll()
    {
        $userService = new userService();
        return $userService->getAll();
    }

    public function get($user)
    {
        $userService = new userService();
        return $userService->get($user);
    }

    public function create($model)
    {
    }

    public function update($model)
    {
        $userService = new userService();
        return $userService->update($model);
    }

    public function delete($model)
    {
        $userService = new userService();
        return $userService->delete($model);
    }

    public function login($email, $password){
        $authService = new authService();
        return $authService->login($email, $password);
    }

    public function register($user, $password){
        $userService = new userService();
        $userService->register($user, $password);
    }
}