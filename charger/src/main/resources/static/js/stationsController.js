function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" data-bs-toggle="modal" data-bs-target="#updateBackdrop">Update</button>
    <button type="button" class="btn btn-primary btn-big" data-bs-toggle="modal" data-bs-target="#deleteBackdrop">Delete</button>`;
    parent.appendChild(buttonsTd);
}


const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
    const responseJson = await fetch(
        baseURL + `/api/stations`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

const response = await responseJson.json();
if (responseJson.ok) {
    console.log(response);
    const table = $("#stations-table tbody");
    for (const station of response) {
        const newStationTr = document.createElement("tr");
        createElementFromAttribute(station.id, newStationTr);
        createElementFromAttribute(station.name, newStationTr);
        createElementFromAttribute(station.isOpen, newStationTr);
        createElementFromAttribute(station.stationType.id, newStationTr);
        createElementFromAttribute(station.location.id, newStationTr);
        createButtons(newStationTr, station);
        table.append(newStationTr);
    }
} else {
    console.log("Errror ");
}
})

async function addStation(){

    const data = {
        name: $('#inputName').val(),
        isOpen: $('#inputIsOpen').val(),
        type: $('#inputType').val(),
        location: $('#inputLocation').val()
    };
    
    
    const responseJson = await fetch(
        baseURL + '/api/stations',
        {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
        
        const response = responseJson.JSON;
        console.log(responseJson);
}

async function deleteStation(id){
    
    const responseJson = await fetch(
        baseURL + '/api/stations/{id}',
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = responseJson.Json;
    console.log(responseJson);
}

function updateStation(id){
    
}


