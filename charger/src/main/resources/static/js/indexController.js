function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onclick=showDialog("${data.id}")>Book now!</button>`;
    parent.appendChild(buttonsTd);
}

function showDialog(id){    
        console.log(id);
        $("#inputId").val(id);
        var myModalEl = document.getElementById('addForm');
        var modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
        modal.show();
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
            createElementFromAttribute(station.location.address, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.stationType.plugType, newStationTr);
            if(station.isOpen)
                createButtons(newStationTr, station);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
})

async function addBooking(){    
    
    const data = {
       startDateTime: $('#inputStartTime').val(),
       duration: $('#inputDuration').val(),
       licenceCar: $('#inputLicence').val(),
       stationId: $('#inputId').val()
    };

    const responseJson = await fetch(
        baseURL + '/api/bookings',
        {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
    
    
    if(!responseJson.ok)
        alert("This station is not available in this time slot\n");
            
    const response = await responseJson.json();
    console.log(response);
    window.location.reload();
        
}

async function search(){
    
    const result = $('#searchResult').val(); 

    const responseJson = await fetch(
        baseURL + `/api/stations/search/` + result,
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
            createElementFromAttribute(station.location.address, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.stationType.plugType, newStationTr);
            if(station.isOpen)
                createButtons(newStationTr, response);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
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
            createElementFromAttribute(station.location.address, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.stationType.plugType, newStationTr);
            if(station.isOpen)
                createButtons(newStationTr, station);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
}

let map;

function initMap() {
    const center = {lng: 23.82568, lat: 44.29849};
    map = new google.maps.Map(document.getElementById("map"), {
        center: center,
        zoom: 8,  
    });
}

window.initMap = initMap;


