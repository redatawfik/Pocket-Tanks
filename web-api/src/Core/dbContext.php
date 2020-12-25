<?php


class dbContext
{
    public static function dbContextBuilder($dbHost, $dbUserName, $dbPassword, $dbName){
        return new mysqli($dbHost,
            $dbUserName,
            $dbPassword,
            $dbName);
    }


}