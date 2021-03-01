<?php
require '../Classes/Authentication.php';

if(isset($_POST['email']) && isset($_POST['password']) ){
    $email = $_POST['email'];
    $password = md5($_POST['password']);

    if(Authentication::check($email,$password)){
        Authentication::auth(1);
        echo 1;
    }else{
        echo 0;
    }

}
?>