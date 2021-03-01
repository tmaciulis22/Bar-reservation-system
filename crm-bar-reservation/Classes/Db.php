<?php

class Db {

    public static function executeS($sql){
        
        $host = "localhost";
        $username = "root";
        $password = "";
        $db = "bar_reservation";    

        // Create connection
        $conn = new mysqli($host, $username, $password, $db);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        mysqli_set_charset($conn,"utf8");

        $result = $conn->query($sql);

        $return = array();
        
        if ($result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
                array_push($return,$row);
            }
        } else {
            $return = null;
        }
        $conn->close();

        return $return;
    }

    public static function execute($sql){
        
        $host = "localhost";
        $username = "root";
        $password = "";
        $db = "bar_reservation";    

        // Create connection
        $conn = new mysqli($host, $username, $password, $db);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $result = $conn->query($sql);

    }
}

?>