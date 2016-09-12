/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchBarcodeAjax() {
    $.ajax({
        url: "/diasetProject/user/credential/barcode/" + $('#barcode').val(),
        success: function (data) {
            console.log("success");
            obj = JSON.parse(data);
            //console.log(obj);
            $('#username').val(obj['username']);
            $('#password').val(obj['password']);
            /*
             obj = JSON.parse(data);
             //set data to hidden fields
             $('#username').val(obj['username']);
             $('#password').val(obj['password']);
             //enable button
             $('#submitButton').prop('disabled', false);
             */
        },
        error: function (e) {
            //disable button
            console.log("error");
            /*
             $('#submitButton').prop('disabled', true);
             //clean data
             $('#username').val("");
             $('#password').val("");
             */
        }
    });

}

function checkLenghtTen(val) {
    console.log(val);
    if (val.length != 10) {
        $('#submitButton').prop('disabled', true);
        $('#username').val("");
        $('#password').val("");
    } else {
        $('#submitButton').prop('disabled', false);
    }
}

