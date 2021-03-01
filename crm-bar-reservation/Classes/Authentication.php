<?php

require 'Db.php';


class Authentication {

    public static function check($email,$password){ 

        // Create connection
        $conn = new mysqli("localhost", "root", "", "bar_reservation");
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "SELECT manager_id FROM manager WHERE email='$email' AND password='$password'";
        $result = $conn->query($sql);

        $conn->close();
        return $result->num_rows;
    }

    public static function auth($id){ 
        setcookie("auth_user", $id, time() + (86400 * 30), "/");
    }

    public static function logout($id){ 
        setcookie("auth_user", $id, 0, "/");
    }

    public static function getUserInfo($id){ 
        return Db::executeS("Select * FROM manager INNER JOIN bar ON manager.bar_id=bar.bar_id WHERE manager.manager_id='$id' ");
    }
}
?>