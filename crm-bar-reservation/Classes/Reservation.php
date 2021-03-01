<?php



class Reservation {

    public static function changeStatus($reservation_id,$status){ 

        // Create connection
        $conn = new mysqli("localhost", "root", "", "bar_reservation");
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "UPDATE reservation SET status='$status' WHERE reservation_id='$reservation_id'";
        $result = $conn->query($sql);

        $conn->close();
        if($result){
            return 1;
        }else{
            return 0;
        }

    }

    public static function changeReservationData($reservation_id,$name,$date){ 

        // Create connection
        $conn = new mysqli("localhost", "root", "", "bar_reservation");
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "UPDATE reservation SET name='$name', date='$date', status='1' WHERE reservation_id='$reservation_id'";
        $result = $conn->query($sql);

        $conn->close();
        if($result){
            return 1;
        }else{
            return 0;
        }

    }

    public static function addExpense($reservation_id,$name,$price){ 

        // Create connection
        $conn = new mysqli("localhost", "root", "", "bar_reservation");
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "INSERT INTO expense (reservation_id, name, price)
        VALUES ('$reservation_id', '$name', '$price')";
        $result = $conn->query($sql);

        $conn->close();
        if($result){
            return 1;
        }else{
            return 0;
        }

    }

    public static function getReservations($bar_id){ 
        return Db::executeS("Select * FROM reservation WHERE bar_id='$bar_id' ORDER BY date");
    }

    public static function getReservationData($reservation_id){ 
        return array(
            "reservation" => Db::executeS("SELECT * FROM reservation WHERE reservation_id='$reservation_id'"),
            "expenses" => Db::executeS("SELECT * FROM expense WHERE reservation_id='$reservation_id'"),
            "total" => Db::executeS("SELECT SUM(price) FROM expense WHERE reservation_id='$reservation_id'")
        );
    }

}
?>