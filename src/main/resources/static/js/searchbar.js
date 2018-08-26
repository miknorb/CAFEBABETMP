window.onload = function () {
    initSerachBox();
}

function initSerachBox() {
    var searchfrom = document.getElementById("searchform");
    var searcbox = document.getElementById("searchbox");
    searchfrom.addEventListener("submit", function (event) {
        window.location.href = "/products.html?url=" + searcbox.value;
        return false;
    });
}