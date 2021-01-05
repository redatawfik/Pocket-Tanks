<?php

namespace MyApp;

use Ratchet\ConnectionInterface;
use Ratchet\MessageComponentInterface;

class Match implements MessageComponentInterface
{
    protected $clients;
    protected $user_map;

    public function __construct()
    {
        $this->clients = new \SplObjectStorage;
        $this->user_map = array();
        echo "Server Started.";
    }

    public function onOpen(ConnectionInterface $conn)
    {
        if (sizeof($this->clients) === 0) {
            // Store the new connection to send messages to later
            $this->clients->attach($conn);
        } else {
            $host = $this->clients->current();
            array_push($this->user_map, array($host, $conn));

            $host->send("left");
            $conn->send("right");
            $this->clients->detach($host);
        }

        echo "New connection! ({$conn->resourceId})\n";
    }
    public function sendResult($result){
        // API URL
        $url = 'http://localhost:63343/web-api/src/api/signMatch.php';

        // Create a new cURL resource
        $ch = curl_init($url);

        // Setup request to send json via POST
        $payload = $result;

        // Attach encoded JSON string to the POST fields
        curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);

        // Set the content type to application/json
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type:application/json'));

        // Return response instead of outputting
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        // Execute the POST request
        $result = curl_exec($ch);

        // Close cURL resource
        curl_close($ch);
    }
    public function onMessage(ConnectionInterface $from, $msg)
    {
        if(str_contains($msg, "{")){
            $this->sendResult($msg);
        }
        else{
            for ($i = 0; $i < sizeof($this->user_map); $i++) {
                $match = $this->user_map[$i];

                $client1 = $match[0];
                $client2 = $match[1];

                if ($client1 === $from) {
                    $client2->send($msg);
                    echo sprintf('Connection %d sending message "%s" to %d' . "\n"
                        , $client1->resourceId, $msg, $client2->resourceId);
                    break;
                } else if ($client2 === $from) {
                    $client1->send($msg);
                    echo sprintf('Connection %d sending message "%s" to %d' . "\n"
                        , $client2->resourceId, $msg, $client1->resourceId);
                    break;
                }
            }
        }
    }

    public function onClose(ConnectionInterface $conn)
    {

        for ($i = 0; $i < sizeof($this->user_map); $i++) {
            $match = $this->user_map[$i];

            $client1 = $match[0];
            $client2 = $match[1];

            if ($client1 === $conn) {
                $client2->send("CLOSE");
                echo sprintf('Connection %d sending message "%s" to %d' . "\n"
                    , $client1->resourceId, "CLOSE", $client2->resourceId);
                break;
            } else if ($client2 === $conn) {
                $client1->send("CLOSE");
                echo sprintf('Connection %d sending message "%s" to %d' . "\n"
                    , $client2->resourceId, "CLOSE", $client1->resourceId);
                break;
            }
        }

        echo "Connection {$conn->resourceId} has disconnected\n";
    }

    public function onError(ConnectionInterface $conn, \Exception $e)
    {
        echo "An error has occurred: {$e->getMessage()}\n";

        $conn->close();
    }
}