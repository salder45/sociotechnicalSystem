/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchBarcodeAjax() {
    $.ajax({
        url: "/diasetProject/user/credential/barcode/" + $('#barcode').val(),
        success: function (data) {
            console.log(data);
            obj = JSON.parse(data);
            $('#username').val(obj['username']);
            $('#password').val(obj['password']);
            $('#submitButton').prop('disabled',false);
        }
    });

}

