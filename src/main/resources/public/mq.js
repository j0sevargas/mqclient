

let favorites = new Array();

function getFavoriteString({label, message, toQueue}) {
    return `${label} - ${toQueue} - ${atob(message).substring(20,50)}`;
}

function updateFavoriteCombo(){
    const selectElement = document.getElementById("favorites");
    const comboSize = selectElement.options.length;
    for (let i = 0; i < comboSize; i++) {
        selectElement.remove(0);        
    }
    favorites.forEach (fav => addSingleFavoriteToCombo(fav));
}


function addSingleFavorite(){
    const obj = getFormInfo();
    favorites.push(obj);
    addSingleFavoriteToCombo(obj);
}

function addSingleFavoriteToCombo(obj){
    const option = document.createElement("option");
    option.text = getFavoriteString(obj);
    document.getElementById("favorites").add(option);
}

function getThisFavorite(){
    const index = document.getElementById("favorites").selectedIndex;
    const obj = favorites[index];
    if (obj){
        setFormInfo(obj);
    }
}

function removeAll(){
    favorites = new Array();
    updateFavoriteCombo();
}

function updateThisFavorite(){
    const obj = getFormInfo();
    const index = document.getElementById("favorites").selectedIndex;
    favorites[index] = obj;
    updateFavoriteCombo();
}


function getFormInfo() {
    const obj = {};
    obj.label = document.getElementById("label").value;
    obj.message = btoa(document.getElementById("message").value);
    obj.toQueue = document.getElementById("toQueue").value;
    obj.replyTo = document.getElementById("replyTo").value;
    obj.queueManager = document.getElementById("queueManager").value;
    obj.host = document.getElementById("host").value;
    obj.port = document.getElementById("port").value;
    obj.channel = document.getElementById("channel").value;
    obj.timeout = document.getElementById("timeout").value;
    obj.response = btoa(document.getElementById("response").value);
    return obj;
}

function setFormInfo(obj) {
    document.getElementById("label").value = obj.label;
    document.getElementById("message").value = atob(obj.message)
    document.getElementById("toQueue").value = obj.toQueue;
    document.getElementById("replyTo").value = obj.replyTo;
    document.getElementById("queueManager").value = obj.queueManager;
    document.getElementById("host").value = obj.host;
    document.getElementById("port").value = obj.port;
    document.getElementById("channel").value = obj.channel;
    document.getElementById("timeout").value = obj.timeout;
    document.getElementById("response").value = (obj.response)?atob(obj.message):"";
}

function saveFavorites() {
    if (favorites.length > 0) {
        localStorage.setItem("favorites", JSON.stringify(favorites));
    }
}

function loadFavorites() {
    if (localStorage.getItem("favorites")) {
        favorites = JSON.parse(localStorage.getItem("favorites"));
    }
    updateFavoriteCombo();
}


function sendMessage() {
    fetchInfo();
}

function getMessage() {
    fetchInfo('/mq/get','sendingMessageSpinner2');
}

function fetchInfo(theUri = '/mq/message',spinnerID = "sendingMessageSpinner"){

    const obj = getFormInfo();

    document.getElementById("response").value = "";
    document.getElementById(spinnerID).style.display = "inherit";

    fetch(theUri, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(obj),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Success: ' + atob(data.message), data);
            document.getElementById("response").value = atob(data.message);
            document.getElementById(spinnerID).style.display = "none";

        })
        .catch((error) => {
            console.error('Error:', error);
            document.getElementById(spinnerID).style.display = "none";
        });
}


loadFavorites();