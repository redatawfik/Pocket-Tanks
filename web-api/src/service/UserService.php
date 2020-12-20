<?php

require_once "src/repository/UserRepository.php";

class UserService
{

    public function getAll()
    {
        $user_repo = new UserRepository();
        return $user_repo->getAll();
    }

    public function create($user)
    {
        $user_repo = new UserRepository();
        $user_repo->create($user);
    }

    public function update($user)
    {
        $user_repo = new UserRepository();
        $user_repo->update($user);
    }

    public function delete($user)
    {
        $user_repo = new UserRepository();
        $user_repo->delete($user);
    }
}
