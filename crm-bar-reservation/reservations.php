<?php

require 'Classes/AuthCheck.php';
require 'Classes/Reservation.php';
require 'Classes/Authentication.php';
require 'Classes/Expense.php';
$data = Authentication::getUserInfo($_COOKIE["auth_user"]);

$reservations = Reservation::getReservations($data[0]["bar_id"]);
?>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Rezervacijos</title>
  </head>
  <body>

    <div class="jumbotron">
    <div class="container" style="text-align:center">
    <h1 class="display-4">Rezervacijos</h1>
    <p class="lead">Rezervacijos bare: <?php echo $data[0]["name"];?> (<?php echo $data[0]["address"];?>)</p>
    </div>
    </div>

    <div class="container">
    <ul class="list-group">
    <?php foreach($reservations as $reservation): ?>
  <li class="list-group-item" id="reservation_<?php echo $reservation["reservation_id"]; ?>">#<?php echo $reservation["reservation_id"]; ?> - <b><?php echo $reservation["date"]; ?></b> - Vardas: <?php echo $reservation["name"]; ?> - 
  
  <?php if($reservation["status"] == 1): ?>
  <a href="#" class="changeStatus" data-id="<?php echo $reservation["reservation_id"];?>" data-status="0" style="color:red">Atšaukti</a>
  <?php else: ?>
  <a href="#" class="changeStatus" data-id="<?php echo $reservation["reservation_id"];?>" data-status="1" style="color:green">Patvirtinti</a>
  <?php endif; ?> - 
  <a href="/barurezervacija/reservation?id=<?php echo $reservation["reservation_id"];?>" style="color:orange">Daugiau informacijos</a>

  <?php if($reservation["status"] == 1): ?>
  <span style="float:right">&#10004;</span>
  <?php else: ?>
  <span style="float:right">&#10060;</span>
  <?php endif; ?>

  </li>

<?php endforeach; ?>
</ul>
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
