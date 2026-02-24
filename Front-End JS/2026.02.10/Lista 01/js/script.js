window.addEventListener("DOMContentLoaded", function () {

    function exibirMsg(msg) {
        alert(msg);
    }

    var btn = document.getElementById("btnClicar1");
    var btn2 = document.getElementById("btnClicar2");
    var btn3 = document.getElementById("btnClicar3");

    btn.addEventListener("click", function () {
        exibirMsg("Vai tomar no cu");
    });

    btn2.addEventListener("click", function () {
        exibirMsg("Piranha");
    });

    btn3.addEventListener("click", function () {
        const somar = (x, y) => {
            alert(x + y);
        };

        somar(10, 20);
    });

});
