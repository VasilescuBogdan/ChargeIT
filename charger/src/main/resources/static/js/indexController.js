function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onclick=showDialog("${data.id}") data-bs-toggle="modal" data-bs-target="#staticBackdrop">Book now!</button>`;
    parent.appendChild(buttonsTd);
}

function showDialog(id){
    console.log(id);
    $("#inputId").val(id);
    var myModalEl = document.getElementById('staticBackdrop');
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
            createElementFromAttribute(station.id, newStationTr);
            createElementFromAttribute(station.name, newStationTr);
            createElementFromAttribute(station.location.address, newStationTr);
            createElementFromAttribute(station.isOpen, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.stationType.plugType, newStationTr);
            createButtons(newStationTr, station);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
})

async function addBooking(){    
     
    /*'use strict';
      
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');
      
    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
              event.preventDefault()
              event.stopPropagation()
            }
      
            form.classList.add('was-validated')
        }, false)
    });
    */
    
    const data = {
       startDateTime: $('#inputStartTime').val(),
       duration: $('#inputDuration').val(),
       licenceCar: $('#inputLicence').val(),
       stationId: $('#inputId').val()
    };
    

    const responseJson = fetch(
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
        const newStationTr = document.createElement("tr");
        createElementFromAttribute(response.id, newStationTr);
        createElementFromAttribute(response.name, newStationTr);
        createElementFromAttribute(response.location.address, newStationTr);
        createElementFromAttribute(response.isOpen, newStationTr);
        createElementFromAttribute(response.stationType.name, newStationTr);
        createElementFromAttribute(response.stationType.plugType, newStationTr);
        createButtons(newStationTr, response);
        table.append(newStationTr);
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
            createElementFromAttribute(station.id, newStationTr);
            createElementFromAttribute(station.name, newStationTr);
            createElementFromAttribute(station.location.address, newStationTr);
            createElementFromAttribute(station.isOpen, newStationTr);
            createElementFromAttribute(station.stationType.name, newStationTr);
            createElementFromAttribute(station.stationType.plugType, newStationTr);
            createButtons(newStationTr, station);
            table.append(newStationTr);
        }
    } else {
        console.log("Errror ");
    }
}


