<?php


class Helpers {

    public static function getReservations($bar_id){ 
        return Db::executeS("Select * FROM reservation WHERE bar_id='$bar_id' ORDER BY date");
    }
}
?>