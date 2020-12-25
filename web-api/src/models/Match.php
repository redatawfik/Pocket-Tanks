<?php


class Match
{
    private $id;
    private $winner_id;
    private $looser_id;
    private $winner_score;
    private $looser_score;

    /**
     * Match constructor.
     */
    public function __construct()
    {
    }

    /**
     * multi params constructor
     * @param $id
     * @param $winner_id
     * @param $looser_id
     * @param $winner_score
     * @param $looser_score
     * @return Match
     */
    public static function Build($id,
                                 $winner_id,
                                 $looser_id,
                                 $winner_score,
                                 $looser_score){
        $instance = new self();
        $instance->setId($id);
        $instance->setWinnerId($winner_id);
        $instance->setLooserId($looser_id);
        $instance->setWinnerScore($winner_score);
        $instance->setLooserScore($looser_score);
        return $instance;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getWinnerId()
    {
        return $this->winner_id;
    }

    /**
     * @return mixed
     */
    public function getLooserId()
    {
        return $this->looser_id;
    }

    /**
     * @return mixed
     */
    public function getWinnerScore()
    {
        return $this->winner_score;
    }

    /**
     * @return mixed
     */
    public function getLooserScore()
    {
        return $this->looser_score;
    }

    /**
     * @param mixed $id
     */
    public function setId($id): void
    {
        $this->id = $id;
    }

    /**
     * @param mixed $winner_id
     */
    public function setWinnerId($winner_id): void
    {
        $this->winner_id = $winner_id;
    }

    /**
     * @param mixed $looser_id
     */
    public function setLooserId($looser_id): void
    {
        $this->looser_id = $looser_id;
    }

    /**
     * @param mixed $winner_score
     */
    public function setWinnerScore($winner_score): void
    {
        $this->winner_score = $winner_score;
    }

    /**
     * @param mixed $looser_score
     */
    public function setLooserScore($looser_score): void
    {
        $this->looser_score = $looser_score;
    }

    /**
     * @return mixed
     */
    public function toJson()
    {
        header('Content-Type: application/json');
        $values = array("id" => $this->getId(),
            "winner_id" => $this->getWinnerId(),
            "looser_id" => $this->getLooserId(),
            "winner_score" => $this->getWinnerScore(),
            "looser_score" => $this->getLooserScore());
        return json_encode($values);
    }
}