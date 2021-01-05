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

    public function onMessage(ConnectionInterface $from, $msg)
    {
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