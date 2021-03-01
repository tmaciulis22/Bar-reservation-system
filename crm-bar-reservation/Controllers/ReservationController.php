<?php
require '../Classes/Reservation.php';

if(isset($_POST['reservation_id']) && isset($_POST['status']) ){
    $reservation_id = $_POST['reservation_id'];
    $status = $_POST['status'];

    if(Reservation::changeStatus($reservation_id,$status)){
        echo 1;
    }else{
        echo 0;
    }
}

if(isset($_POST['reservation_id']) && isset($_POST['name']) && isset($_POST['date'])) {
    $reservation_id = $_POST['reservation_id'];
    $name = $_POST['name'];
    $date = $_POST['date'];

    if(Reservation::changeReservationData($reservation_id,$name,$date)){
        echo 1;
    }else{
        echo 0;
    }
}

if(isset($_POST['reservation_id']) && isset($_POST['name']) && isset($_POST['price'])) {
    $reservation_id = $_POST['reservation_id'];
    $name = $_POST['name'];
    $price = $_POST['price'];

    if(Reservation::addExpense($reservation_id,$name,$price)){
        echo 1;
    }else{
        echo 0;
    }
}
?>