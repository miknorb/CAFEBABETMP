window.onload = function() {
    fillTable();
};

function deleteProduct(id) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', '/products/' + id);
    xhr.onreadystatechange = function () {
          var DONE = 4;
          var OK = 200;
          if (xhr.readyState === DONE) {
            if (xhr.status === OK && xhr.responseText == "true") {
              console.log("Sikeres törlés");
                  fillTable();
              } else {
                console.log('Hiba történt a törlés során.');
              }
            }
          };
    xhr.send();
    return false;
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
          var productsTable = document.getElementById("product-container");
          productsTable.innerHTML = "";
          for (var i = 0; i < products.length; i++) {
            var tr = document.createElement("tr");
            productsTable.appendChild(tr);
            tr["raw-data"] = products[i];

            var idTd = document.createElement("td");
            idTd.innerHTML = products[i].productKey;
            tr.appendChild(idTd);

            var nameTd = document.createElement("td");
            nameTd.innerHTML = products[i].name;
            tr.appendChild(nameTd);

            var manufacturerTd = document.createElement("td");
            manufacturerTd.innerHTML = products[i].manufacturer;
            tr.appendChild(manufacturerTd);

            var priceTd = document.createElement("td");
            priceTd.innerHTML = products[i].price;
            tr.appendChild(priceTd);

            var buttonsTd = document.createElement("td");
            tr.appendChild(buttonsTd);
            var editButton = document.createElement("button");
            buttonsTd.appendChild(editButton);
            editButton.innerHTML= "Szerkeszt";
            editButton.onclick = function() {
                /*var product = this.parentElement.parentElement["raw-data"];
                editOnClick();
                document.getElementById("update-product-id").value = product.id;
                document.getElementById("update-product-name").value = product.name;
                document.getElementById("update-product-coords").value = product.lat + "," + product.lon;*/
            };

            var deleteButton = document.createElement("button");
            buttonsTd.appendChild(deleteButton);
            deleteButton.innerHTML= "Törlés";
            deleteButton.onclick = function() {
                if(confirm("Biztos vagy benne, hogy törlöd a terméket?")){
                    var product = this.parentElement.parentElement["raw-data"];
                    deleteProduct(product.id);
                }
            };
          }
        } else {
          console.log('Error: ' + xhr.status);
        }
      }
    };
    xhr.send(null);

}
