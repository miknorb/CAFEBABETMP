window.onload = function () {
    fillPage();

}

function fillPage() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/products?url=" + getUrlVars()["url"]);
    xhr.onreadystatechange = function () {
        var DONE = 4;
        var OK = 200;
        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                var productResponse = JSON.parse(xhr.responseText);
                var container = document.getElementById("product-container");
                if (productResponse.present == false) {
                    container.innerHTML = "";
                    var h3 = document.createElement("h3");
                    h3.innerText = "Whoops! Seems like we could not find the item, which you are looking for! :(";
                    container.appendChild(h3);
                } else {
                    var product = productResponse.product;
                    displayProduct(container, product);
                }

            }
        }
    }
    xhr.send(null);
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

function displayProduct(container, product) {
    container.innerHTML = "";
    var h5 = document.createElement("h5");
    h5.setAttribute("class", "card-header");
    h5.innerText = product.productKey + " - " + product.name;
    var body0 = document.createElement("div");
    body0.setAttribute("class", "card-body");
    var p0 = document.createElement("p");
    p0.setAttribute("class", "card-text");
    p0.innerText = "Manufacturer:\n" + product.manufacturer;
    body0.appendChild(p0);
    var body1 = document.createElement("div");
    body1.setAttribute("class", "card-body");
    var p1 = document.createElement("p");
    p1.setAttribute("class", "card-text");
    p1.innerText = "URL:\n" + product.url;
    body1.appendChild(p1);
    var body2 = document.createElement("div");
    body2.setAttribute("class", "card-footer");
    body2.innerText = "Price:\n" + product.price.toLocaleString() + ".-";
    container.appendChild(h5);
    container.appendChild(body0);
    container.appendChild(body1);
    container.appendChild(body2);
}