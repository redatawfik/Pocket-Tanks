<?php


abstract class Controller
{
    abstract public function getAll();
    abstract public function get($id);
    abstract public function create($model);
    abstract public function update($model);
    abstract public function delete($model);

}