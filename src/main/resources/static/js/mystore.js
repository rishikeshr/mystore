/*
Method to add contents to cart.
 */
$('.ixr').click(function () {

    $.ajax({
        url: '/addToCart',
        type: 'POST',
        data: {'item': 'ixr'}, // An object with the key 'submit' and value 'true;
        success: function (result) {
            console.log(" Added to Cart ");
        }
    });

});