<?php


class User
{
    private $id;
    private $email;
    private $username;
    private $score;
    private $matches;
    private $imageURL;


    /**
     * remember to delete this function that prints user
     * */
    public function printUser(){
        //print_r($this->getId());
        echo "id: " . $this->getId() . "</br>".
            "Email: " . $this->getEmail() . "</br>".
            "Username: " . $this->getUsername() . "</br>".
            "Score: " . $this->getScore() .  "</br>".
            "ImageUrl: " . $this->getImageURL() . "</br>";
    }
    /**
     * return json representation for this user
    */
    public function toJson(){
        header('Content-Type: application/json');
        $values = array("id" => $this->id,
            "userName" => $this->getUsername(),
            "email" => $this->getEmail(),
            "score" => $this->getScore(),
            "imageUrl" => $this->getImageURL());
        return json_encode($values);
    }

    /**
     * multi params constructor
     * @param $id
     * @param $email
     * @param $username
     * @return User
     */
    public static function Build($id, $email, $username){
        $instance = new self();
        $instance->setId($id);
        $instance->setUsername($username);
        $instance->setEmail($email);
        return $instance;
    }

    public static function buildWithAll($id, $email, $username, $score, $imageUrl){
        $instance = new self();
        $instance->setId($id);
        $instance->setUsername($username);
        $instance->setEmail($email);
        $instance->setScore($score);
        $instance->setImageURL($imageUrl);
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
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return mixed
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * @return mixed
     */
    public function getScore()
    {
        return $this->score;
    }

    /**
     * @return mixed
     */
    public function getMatches()
    {
        return $this->matches;
    }

    /**
     * @return mixed
     */
    public function getImageURL()
    {
        return $this->imageURL;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email)
    {
        $this->email = $email;
    }

    /**
     * @param mixed $username
     */
    public function setUsername($username)
    {
        $this->username = $username;
    }

    /**
     * @param mixed $score
     */
    public function setScore($score)
    {
        $this->score = $score;
    }

    /**
     * @param mixed $matches
     */
    public function setMatches($matches)
    {
        $this->matches = $matches;
    }

    /**
     * @param mixed $imageURL
     */
    public function setImageURL($imageURL)
    {
        $this->imageURL = $imageURL;
    }


}