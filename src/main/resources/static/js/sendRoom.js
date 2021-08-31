function showNewRoomWindow(){
    hideEditRoomWindow();
    document.getElementById("new_room").style.display = "block";
}

function hideNewRoomWindow(){
    document.getElementById("new_room").style.display = "none";
}

function hideEditRoomWindow(){
    document.getElementById("edit_room").style.display = "none";
}

function editRoom(room_id, room_number, room_capacity) {
    hideNewRoomWindow();
    document.getElementById("edit_room").style.display = "block";
    document.getElementById("edit_room_number").value = room_number;
    document.getElementById("edit_capacity").value =  room_capacity;

    document.getElementById("edit_form_button" ).onclick = function (){
        sendPutData(room_id);
    }
}

function deleteRoom(id) {
    const XHR = new XMLHttpRequest();
    XHR.open("DELETE", "http://localhost:8080/rooms/" + id);
    XHR.send();

    window.location.replace("/rooms");
}

function sendPutData(room_id) {
    var jsonData = {
        "roomNumber": document.getElementById("edit_room_number").value,
        "capacity": document.getElementById("edit_capacity").value,
    };
    var jsonString = JSON.stringify(jsonData);

    const XHR = new XMLHttpRequest();
    XHR.open("PUT", "http://localhost:8080/rooms/" + room_id);
    XHR.setRequestHeader('Content-Type', 'application/json');
    XHR.send(jsonString);

    window.location.replace("/rooms");
}

function sendData() {
    var jsonData = {
        "roomNumber": document.getElementById("room_number").value,
        "capacity": document.getElementById("capacity").value,
    };
    var jsonString = JSON.stringify(jsonData);

    const XHR = new XMLHttpRequest();
    XHR.open("POST", "http://localhost:8080/rooms");
    XHR.setRequestHeader('Content-Type', 'application/json');
    XHR.send(jsonString);

    window.location.replace("/rooms");
}