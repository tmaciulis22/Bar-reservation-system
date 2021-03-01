<?php



class Expense {

    public static function getExpenses($reservation_id){ 
        return Db::executeS("Select * FROM expense WHERE reservation_id='$reservation_id'");
    }

}
?>