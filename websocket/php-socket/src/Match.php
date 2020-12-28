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
        echo "Server Started.";
    }

    public function onOpen(ConnectionInterface $conn)
    {
        // Store the new connection to send messages to later
        if (sizeof($this->clients) === 0) {
            $this->clients->attach($conn);
        } else {
            $host = $this->clients->current();
            $this->user_map[1] = array($host, $conn);

            $host->send("left");
            $conn->send("right");
        }

        echo "New connection! ({$conn->resourceId})\n";
    }

    public function onMessage(ConnectionInterface $from, $msg)
    {
        foreach ($this->user_map as $match) {
            $client1 = $match[0];
            $client2 = $match[1];

            if ($client1 === $from) {
                $client2->send($msg);
                echo sprintf('Connection %d sending message "%s" to %d other connection' . "\n"
                    , $client1->resourceId, $msg, $client2->resourceId);
                break;
            } else if ($client2 === $from) {
                $client1->send($msg);
                echo sprintf('Connection %d sending message "%s" to %d other connection' . "\n"
                    , $client2->resourceId, $msg, $client1->resourceId);
                break;
            }
        }
    }

    public function onClose(ConnectionInterface $conn)
    {
        // The connection is closed, remove it, as we can no longer send it messages
        $this->clients->detach($conn);
        unset($this->user_map);

        echo "Connection {$conn->resourceId} has disconnected\n";
    }

    public function onError(ConnectionInterface $conn, \Exception $e)
    {
        echo "An error has occurred: {$e->getMessage()}\n";

        $conn->close();
    }
}