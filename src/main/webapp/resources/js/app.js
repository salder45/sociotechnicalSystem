/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchBarcodeAjax() {
    $.ajax({
        url: "/diasetProject/user/credential/barcode/" + $('#barcode').val(),
        success: function (data) {
            obj = JSON.parse(data);
            //set data to hidden fields
            $('#username').val(obj['username']);
            $('#password').val(obj['password']);
            //enable button
            $('#submitButton').prop('disabled', false);
        },
        error: function(e){
            //disable button
            $('#submitButton').prop('disabled', true);
            //clean data
            $('#username').val("");
            $('#password').val("");
        }
    });

}

