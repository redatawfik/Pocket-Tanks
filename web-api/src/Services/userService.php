<?php
require_once '../Dao/userDao.php';

class userService
{
    public function get($id){
        $userDao = new userDao();
        return $userDao->get($id);
    }

    public function getAll(){
        $userDao = new userDao();
        return $userDao->getAll();
    }

    public function update($user){
        $userDao = new userDao();
        return $userDao->update($user);
    }

    public function register($user, $password){
        $userDao = new userDao();
        $userDao->register($user, $password);
    }
}