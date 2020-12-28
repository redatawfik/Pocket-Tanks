<?php
require_once '../Dao/matchesDao.php';


class matchesService
{
    private $matchDao;

    /**
     * matchesService constructor.
     */
    public function __construct()
    {
        $this->matchDao = new matchesDao();
    }

    public function getAll(){
        return $this->matchDao->getAll();
    }

    public function get($id){
        return $this->matchDao->get($id);
    }

    public function getUserMatches($id){
        return $this->matchDao->getUserMatches($id);
    }

    public function create($match){
        return $this->matchDao->create($match);
    }

}