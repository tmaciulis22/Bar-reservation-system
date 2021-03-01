$( ".login-button" ).on( "click",  (e) => {
    e.preventDefault();
    e.stopPropagation();
    var email = $("#Email").val();
    var password = $("#Password").val();

    $.ajax({
        type: "POST",
        url: "Controllers/AuthController.php",
        dataType: "json",
        data: {email: email,password:password},
        success : function(data){
            if(data){
                window.location.replace("/barurezervacija/reservations");
            }
            
        }
    });
});

$( ".changeStatus" ).on( "click",  (e) => {
    e.preventDefault();
    e.stopPropagation();
    var reservation_id = $(e.currentTarget).attr("data-id");
    var status = $(e.currentTarget).attr("data-status");


    $.ajax({
        type: "POST",
        url: "Controllers/ReservationController.php",
        dataType: "json",
        data: {reservation_id: reservation_id,status:status},
        success : function(data){
            if(data){
                
                if(status == "1"){
                    $("#reservation_"+reservation_id+" span").html('&#10004;');
                    $("#reservation_"+reservation_id+" .changeStatus").attr("data-status","0");
                    $("#reservation_"+reservation_id+" .changeStatus").css("color","red");
                    $("#reservation_"+reservation_id+" .changeStatus").text("AtÅ¡aukti");
                    
                }else{

                    $("#reservation_"+reservation_id+" span").html('&#10060;');
                    $("#reservation_"+reservation_id+" .changeStatus").attr("data-status",1);
                    $("#reservation_"+reservation_id+" .changeStatus").css("color","green");
                    $("#reservation_"+reservation_id+" .changeStatus").text("Patvirtinti");


                }
                
            }
            
        }
    });
});

$( ".saveReservation" ).on( "click",  (e) => {
    e.preventDefault();
    e.stopPropagation();
    var date = $("#reservationDate").val();
    var name = $("#reservationName").val();
    var reservation_id = $(e.currentTarget).attr("data-id");

    $.ajax({
        type: "POST",
        url: "Controllers/ReservationController.php",
        dataType: "json",
        data: {date: date,name:name,reservation_id:reservation_id},
        success : function(data){
            if(data){
                window.location.replace("/barurezervacija/reservations");
            }
            
        }
    });
});

$( ".cancelReservation" ).on( "click",  (e) => {
    e.preventDefault();
    e.stopPropagation();
    var reservation_id = $(e.currentTarget).attr("data-id");

    $.ajax({
        type: "POST",
        url: "Controllers/ReservationController.php",
        dataType: "json",
        data: {reservation_id: reservation_id,status:0},
        success : function(data){
            if(data){
                
                window.location.replace("/barurezervacija/reservations");
                
            }
            
        }
    });
});

$( ".saveExpense" ).on( "click",  (e) => {
    e.preventDefault();
    e.stopPropagation();
    var price = $("#expensePrice").val();
    var name = $("#expenseName").val();
    var reservation_id = $(e.currentTarget).attr("data-id");

    if(name != ""){
        $.ajax({
            type: "POST",
            url: "Controllers/ReservationController.php",
            dataType: "json",
            data: {price: price,name:name,reservation_id:reservation_id},
            success : function(data){
                if(data){
                    location.reload();
                }
                
            }
        });
    }


});