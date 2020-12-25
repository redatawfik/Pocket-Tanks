<?php
require_once '../Core/Controller.php';
require_once '../Services/matchesService.php';

class matchController extends Controller
{
    private $matchService;
    /**
     * matchController constructor.
     */
    public function __construct()
    {
        $this->matchService = new matchesService();
    }

    public function getAll()
    {
        return $this->matchService->getAll();
    }

    public function get($id)
    {
        return $this->matchService->get($id);
    }

    public function getUserMatches($id){
        return $this->matchService->getUserMatches($id);
    }
    public function create($model)
    {
        // TODO: Implement create() method.
    }

    public function update($model)
    {
        // TODO: Implement update() method.
    }

    public function delete($model)
    {
        // TODO: Implement delete() method.
    }
}