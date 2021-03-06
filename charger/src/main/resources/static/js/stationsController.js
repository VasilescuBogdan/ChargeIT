function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onClick="updateStationInit(${data.id})">Update</button>
    <button type="submit" class="btn btn-primary btn-big" onClick="deleteStation(${data.id})">Delete</button>`;
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
        createElementFromAttribute(station.name, newStationTr);
        createElementFromAttribute(station.isOpen, newStationTr);
        createElementFromAttribute(station.stationType.name, newStationTr);
        createElementFromAttribute(station.location.address, newStationTr);
        createButtons(newStationTr, station);
        table.append(newStationTr);
    }
} else {
    console.log("Errror ");
}

addOptionsType();
addOptionsLocation();
})

async function addStation(){
 
    
    const data = {
        name: $('#inputName').val(),
        isOpen: $('#inputIsOpen').is(":checked"),
        stationTypeId: $('#inputType').val(),
        locationId: $('#inputLocation').val()
    };    
    
    const responseJson = await fetch(
        baseURL + `/api/stations`,
        {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
        
        const response = responseJson.JSON;
        console.log(responseJson);
        window.location.reload();
}

async function sortStations(attribute){
    
    
    const responseJson = await fetch(
        baseURL + `/api/stations/sort/` + attribute,
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
        table.empty();
        for (const station of response) {
            const newStationTr = document.createElement("tr");
            createElementFromAttribute(station.name, newStationTr);
            createElementFromAttribute(station.isOpen, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.location.address, newStationTr);
            createButtons(newStationTr, station);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
}

async function deleteStation(id){
    
    const responseJson = await fetch(
        baseURL + `/api/stations/` + id,
        {
            method: 'DELETE',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = responseJson.Json;
    console.log(responseJson);
    window.location.reload();
}

async function updateStationInit(id){
    
    console.log(id);
    const data = await fetchData(id);

    $("#inputUpdateId").val(id)
    $("#inputUpdateName").val(data.name);
    $("#inputUpdateIsOpen").val(data.isOpen); 
    $("#inputUpdateType").val(data.stationType.id);
    $("#inputUpdateLocation").val(data.location.id);

    const myModalEl = document.getElementById('updateForm');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchData(id){ 
    const responseJson = await fetch(
        baseURL + `/api/stations/` + id,
        {
            method: 'GET',
            headers:{
            'Content-Type':'application/json'
            },
        });
    const response = await responseJson.json();
    return response;
}

async function updateStation(){
    
    const data = {
        id: $('#inputUpdateId').val(),
        name: $('#inputUpdateName').val(),
        isOpen: $('#inputUpdateIsOpen').is(":checked"),
        stationTypeId: $('#inputUpdateType').val(),
        locationId: $('#inputUpdateLocation').val()
    };

    const responseJson = await fetch(
        baseURL + `/api/stations`,
        {
            method: 'PATCH',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
        
        const response = responseJson.JSON;
        console.log(responseJson);
        window.location.reload();
        
}

async function addOptionsType(){
    
    const responseJson = await fetch(
        baseURL + `/api/stationTypes`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        for(const stationType of response){
            $('#inputType').append(new Option(stationType.name, stationType.id));
            $('#inputUpdateType').append(new Option(stationType.name, stationType.id));
        }
    }
}

async function addOptionsLocation(){
    
    const responseJson = await fetch(
        baseURL + `/api/locations`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        for(const location of response){
            $('#inputLocation').append(new Option(location.address, location.id));
            $('#inputUpdateLocation').append(new Option(location.address, location.id));
        }
    }
}


