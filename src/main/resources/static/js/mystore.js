/*
Method to add contents to cart.
 */
$(document).ready(function () {

    $.ajax({
        url: 'getCartCount',
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',// An object with the key 'submit' and value 'true;
        success: function (result) {

            if (JSON.parse(result) != 0) {
                $('#cartQuantity').text(JSON.parse(result));
            }

        },
        error: function (e) {
            console.log(e);
        }
    });


    $('#ixr').click(function () {
        console.log(" Posting to Cart ");
        var cartitem = {
            "item": "ixr"
        };
        $.ajax({
            url: 'addToCart',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(cartitem), // An object with the key 'submit' and value 'true;
            success: function (result) {
                $('#cartQuantity').text(JSON.parse(result));
            },
            error: function (e) {
                console.log(e);
            }
        });

    });
    $('#ixs').click(function () {
        console.log(" Posting to Cart ");
        var cartitem = {
            "item": "ixs"
        };
        $.ajax({
            url: 'addToCart',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(cartitem), // An object with the key 'submit' and value 'true;
            success: function (result) {
                $('#cartQuantity').text(JSON.parse(result));
            },
            error: function (e) {
                console.log(e);
            }
        });

    });
    $('#ixsm').click(function () {
        console.log(" Posting to Cart ");
        var cartitem = {
            "item": "ixsm"
        };
        $.ajax({
            url: 'addToCart',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(cartitem), // An object with the key 'submit' and value 'true;
            success: function (result) {
                $('#cartQuantity').text(JSON.parse(result));
            },
            error: function (e) {
                console.log(e);
            }
        });

    });
    $('#ipad').click(function () {
        console.log(" Posting to Cart ");
        var cartitem = {
            "item": "ipad"
        };
        $.ajax({
            url: 'addToCart',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(cartitem), // An object with the key 'submit' and value 'true;
            success: function (result) {
                $('#cartQuantity').text(JSON.parse(result));
            },
            error: function (e) {
                console.log(e);
            }
        });

    });
});