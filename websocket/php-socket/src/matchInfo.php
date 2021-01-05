<?php


namespace MyApp;


class matchInfo
{
    private $user1Name;
    private $user2Name;
    private $user1Score;
    private $user2Score;
    private $connection1;
    private $connection2;

    /**
     * @param mixed $connection1
     */
    public function setConnection1($connection1)
    {
        $this->connection1 = $connection1;
    }

    /**
     * @param mixed $connection2
     */
    public function setConnection2($connection2)
    {
        $this->connection2 = $connection2;
    }

    /**
     * @return mixed
     */
    public function getConnection1()
    {
        return $this->connection1;
    }

    /**
     * @return mixed
     */
    public function getConnection2()
    {
        return $this->connection2;
    }

    public function __construct()
    {

    }

    /**
     * @param mixed $user1Name
     */
    public function setUser1Name($user1Name)
    {
        $this->user1Name = $user1Name;
    }

    /**
     * @param mixed $user2Name
     */
    public function setUser2Name($user2Name)
    {
        $this->user2Name = $user2Name;
    }

    /**
     * @param mixed $user1Score
     */
    public function setUser1Score($user1Score)
    {
        $this->user1Score = $user1Score;
    }

    /**
     * @param mixed $user2Score
     */
    public function setUser2Score($user2Score)
    {
        $this->user2Score = $user2Score;
    }



    /**
     * @return mixed
     */
    public function getUser1Name()
    {
        return $this->user1Name;
    }

    /**
     * @return mixed
     */
    public function getUser2Name()
    {
        return $this->user2Name;
    }

    /**
     * @return mixed
     */
    public function getUser1Score()
    {
        return $this->user1Score;
    }

    /**
     * @return mixed
     */
    public function getUser2Score()
    {
        return $this->user2Score;
    }


}