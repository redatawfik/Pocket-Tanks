<?php
require_once '../Dao/userDao.php';

class authService
{
   public function login($username, $password){
       $userDao = new userDao();
       return $userDao->login($username, $password);
   }

}