function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onClick="updateStationTypeInit(${data.id})">Update</button>
    <button type="submit" class="btn btn-primary btn-big" onClick="deleteStationType(${data.id})">Delete</button>`;
    parent.appendChild(buttonsTd);
}

const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
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
        const table = $("#station-types-table tbody");
        for (const stationType of response) {
            const newStationTypeTr = document.createElement("tr");
            createElementFromAttribute(stationType.name, newStationTypeTr);
            createElementFromAttribute(stationType.plugType, newStationTypeTr);
            createElementFromAttribute(stationType.power, newStationTypeTr);
            createButtons(newStationTypeTr, stationType);
            table.append(newStationTypeTr);
        }
    } else {
        console.log("Errror ");
    }
})

async function addStationType(){
 
    
    const data = {
        name: $('#inputName').val(),
        plugType: $('#inputPlugType').val(),
        power: $('#inputPower').val(),
    };    
    
    const responseJson = await fetch(
        baseURL + `/api/stationTypes`,
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

async function updateStationTypeInit(id){
    
    console.log(id);
    const data = await fetchData(id);
    $("#inputUpdateId").val(id);
    $("#inputUpdateName").val(data.name);
    $("#inputUpdatePlugType").val(data.plugType); 
    $("#inputUpdatePower").val(data.power);

    const myModalEl = document.getElementById('updateForm');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchData(id){ 
    const responseJson = await fetch(
        baseURL + `/api/stationTypes/` + id,
        {
            method: 'GET',
            headers:{
            'Content-Type':'application/json'
            },
        });
    const response = await responseJson.json();
    console.log(responseJson);
    return response;
}

async function updateStationType(){
    
    const data = {
        id: $("#inputUpdateId").val(),
        name: $('#inputUpdateName').val(),
        plugType: $('#inputUpdatePlugType').val(),
        power: $('#inputUpdatePower').val(),
    };

    const responseJson = await fetch(
        baseURL + `/api/stationTypes`,
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

async function deleteStationType(id){
    
    const responseJson = await fetch(
        baseURL + `/api/stationTypes/` + id,
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