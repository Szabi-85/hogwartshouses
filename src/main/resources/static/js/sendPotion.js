function showNewPotionWindow(){
    hideEditPotionWindow();
    document.getElementById("new_potion").style.display = "block";
}

function hideNewPotionWindow(){
    document.getElementById("new_potion").style.display = "none";
}

function hideEditPotionWindow(){
    document.getElementById("edit_potion").style.display = "none";
}

function editPotion(potion_id) {
    hideNewPotionWindow();
    let potion_name = document.getElementById("data" + potion_id).textContent;
    document.getElementById("edit_potion").style.display = "block";
    document.getElementById("edit_potion_name").value = potion_name;

    document.getElementById("edit_form_button" ).onclick = function (){
        sendPutData(potion_id);
    }
}

function deletePotion(id) {
    const XHR = new XMLHttpRequest();
    XHR.open("DELETE", "http://localhost:8080/potions/delete/" + id);
    XHR.send();

    window.location.replace("/potions");
}

function sendPutData(potion_id) {
    var jsonData = {
        "name": document.getElementById("edit_potion_name").value,
    };
    var jsonString = JSON.stringify(jsonData);

    const XHR = new XMLHttpRequest();
    XHR.open("PUT", "http://localhost:8080/potions/rename/" + potion_id);
    XHR.setRequestHeader('Content-Type', 'application/json');
    XHR.send(jsonString);

    window.location.replace("/potions");
}

function sendData() {
    let jsonString = '["' +
        document.getElementById("ingredient1").value +
        '", "' +
        document.getElementById("ingredient2").value +
        '", "' +
        document.getElementById("ingredient3").value +
        '", "' +
        document.getElementById("ingredient4").value +
        '", "' +
        document.getElementById("ingredient5").value +
        '"]';

    const XHR = new XMLHttpRequest();
    XHR.open("POST", "http://localhost:8080/potions/" + document.getElementById("brewer_student").value);
    XHR.setRequestHeader('Content-Type', 'application/json');
    XHR.send(jsonString);

    window.location.replace("/potions");
}