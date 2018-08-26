window.onload = function () {
    fillTable();
}

function fillTable() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/products');
    xhr.onreadystatechange = function () {
        var DONE = 4;
        var OK = 200;
        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                var products = JSON.parse(xhr.responseText);
                var productsTable = document.getElementById("products-table");
                for (var i = 0; i < products.length; i++) {
                    productsTable.innerHTML += "<tr><td>" + products[i].productKey +
                        "</td><td>" + "<a>" + products[i].name + "</a>" +
                        "</td><td>" + products[i].manufacturer +
                        "</td><td>" + products[i].price + "</td></tr>";
                }
            } else {
                console.log('Error: ' + xhr.status);
            }
        }
    };
    xhr.send(null);

}