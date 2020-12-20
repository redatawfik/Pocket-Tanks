<?php

require_once "src/service/UserService.php";

class UserController
{
    public function getAll()
    {
        $user_ser = new UserService();
        return $user_ser->getAll();
    }

    public function create($user)
    {
        $user_ser = new UserService();
        return $user_ser->create($user);
    }

    public function update($user)
    {
        $user_ser = new UserService();
        return $user_ser->update($user);
    }

    public function delete($user)
    {
        $user_ser = new UserService();
        return $user_ser->delete($user);
    }
}