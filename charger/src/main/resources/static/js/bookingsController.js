function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onClick="updateBookingInit(${data.id})">Update</button>
    <button type="submit" class="btn btn-primary btn-big" onClick="deleteBooking(${data.id})">Delete</button>`;
    parent.appendChild(buttonsTd);
}

const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
    const responseJson = await fetch(
        baseURL + `/api/bookings`,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

    const response = await responseJson.json();
    if (responseJson.ok) {
        console.log(response);
        const table = $("#bookings-table tbody");
        for (const booking of response) {
            const newBookingTr = document.createElement("tr");
            createElementFromAttribute(booking.startDate, newBookingTr);
            createElementFromAttribute(booking.endDate, newBookingTr);
            createElementFromAttribute(booking.licenceCar, newBookingTr);
            createElementFromAttribute(booking.station.name, newBookingTr);
            createButtons(newBookingTr, booking);
            table.append(newBookingTr);
        }
    } else {
        console.log("Errror ");
    }

    addOptionsStation()

})

async function deleteBooking(id){
    
    const responseJson = await fetch(
        baseURL + `/api/bookings/` + id,
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

async function sortBooking(attribute){
    
    
    const responseJson = await fetch(
        baseURL + `/api/bookings/sort/` + attribute,
        {
            method: 'GET',
            headers: {
                'Content-Type' : 'application/json'
            },
        });

        const response = await responseJson.json();
        if (responseJson.ok) {
            console.log(response);
            const table = $("#bookings-table tbody");
            table.empty();
        for (const booking of response) {
            const newBookingTr = document.createElement("tr");
            createElementFromAttribute(booking.startDate, newBookingTr);
            createElementFromAttribute(booking.endDate, newBookingTr);
            createElementFromAttribute(booking.licenceCar, newBookingTr);
            createElementFromAttribute(booking.station.name, newBookingTr);
            createButtons(newBookingTr, booking);
            table.append(newBookingTr);
        }
    } else {
        console.log("Errror ");
    }
}

async function updateBookingInit(id){

    console.log(id);
    const data = await fetchData(id);

    const duration = Math.abs(data.endDate - data.startDate) / 60000;

    $("#inputId").val(id);
    $("#inputStartTime").val(data.startDate);
    $("#inputDuration").val(duration);
    $("#inputLicence").val(data.licenceCar);
    $("#inputStationId").val(data.station.id);

    const myModalEl = document.getElementById('updateForm');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchData(id){
        const responseJson = await fetch(
            baseURL + `/api/bookings/` + id,
        {
            method: 'GET',
            headers:{
                'Content-Type':'application/json'
            },
        });
    const response = await responseJson.json();
    return response;
}

async function updateBooking(){
    
    const data = {
        id: $('#inputId').val(),
        startDate: $('#inputStartTime').val(),
        duration: $('#inputDuration').val(),
        licenceCar: $('#inputLicence').val(),
        stationId: $('#inputStationId').val()
    };

    const responseJson = await fetch(
        baseURL + `/api/bookings`,
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

async function addOptionsStation(){
    
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
        for(const station of response){
            $('#inputStationId').append(new Option(station.name, station.id));
        }
    }
}