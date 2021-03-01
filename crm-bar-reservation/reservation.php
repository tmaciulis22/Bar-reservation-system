<?php

require 'Classes/AuthCheck.php';
require 'Classes/Reservation.php';
require 'Classes/Authentication.php';
require 'Classes/Expense.php';
$data = Authentication::getUserInfo($_COOKIE["auth_user"]);

$reservation = Reservation::getReservationData($_GET["id"]);

?>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Rezervacijos Informacija</title>
  </head>
  <body>

    <div class="jumbotron">
    <div class="container" style="text-align:center">
    <h1 class="display-4">Rezervacijos informacija</h1>
    <p class="lead">Rezervacijos nr: <?php echo $reservation["reservation"][0]["reservation_id"];?> (<?php echo $data[0]["name"];?>)</p>
    </div>
    </div>

    <div class="container">
    <div class="container">
    <div class="row">
    <div class="col-6">
    <h1 class="display-4">Informacija</h1>
    <br>
    <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">Vardas</span>
            </div>
            <input type="text" id="reservationName" class="form-control" placeholder="Vardas" aria-label="Username" value="<?php echo $reservation["reservation"][0]["name"];?>" aria-describedby="basic-addon1">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon1">Data</span>
            </div>
            <input type="text" id="reservationDate" class="form-control" placeholder="Vardas" aria-label="Username" value="<?php echo $reservation["reservation"][0]["date"];?>" aria-describedby="basic-addon1">
        </div>
        <button type="button" class="btn btn-success saveReservation" data-id="<?php echo $reservation["reservation"][0]["reservation_id"];?>">Išsaugoti ir patvirtinti</button>
        <button type="button" class="btn btn-danger cancelReservation" data-id="<?php echo $reservation["reservation"][0]["reservation_id"];?>">Atšaukti</button>
    </div>
    
    <div class="col-6">
    <h1 class="display-4">Išlaidos</h1>
    <br>
    <ul class="list-group">
    <?php foreach($reservation["expenses"] as $expense): ?>
        <li class="list-group-item"><?php echo $expense["name"];?>
        <span style="float:right;color:green"><?php echo round($expense["price"],2);?>€</span>
        </li>

    <?php endforeach; ?>
    <li class="list-group-item"><b>Viso: </b> <span style="float:right;color:green"><?php echo round($reservation["total"][0]["SUM(price)"],2);?>€</span>
    </li>
    </ul>
    <br>
    <h1 class="display-4">Pridėti išlaidą</h1>
    <br>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">Pavadinimas</span>
    </div>
    <input type="text" class="form-control" id="expenseName" placeholder="Pavadinimas" aria-label="Pavadinimas" aria-describedby="basic-addon1">
    </div>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">Kaina</span>
    </div>
    <input type="number" step="0.01" class="form-control" id="expensePrice" placeholder="Kaina" aria-label="Kaina" aria-describedby="basic-addon1">
    </div>
    <button type="button" class="btn btn-success saveExpense" data-id="<?php echo $reservation["reservation"][0]["reservation_id"];?>">Pridėti</button>
    <br><br>

    </div>
    </div>
    </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="Assets/custom.js" ></script>
  </body>
</html>
