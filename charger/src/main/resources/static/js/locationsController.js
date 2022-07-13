function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute}</p>`;
    parent.appendChild(openCell);
}

function createButtons(parent, data) {
    const buttonsTd = document.createElement("td");
    buttonsTd.innerHTML = `<button type="button" class="btn btn-primary btn-big" onClick="updateLocationInit(${data.id})">Update</button>
    <button type="submit" class="btn btn-primary btn-big" onClick="deleteLocation(${data.id})">Delete</button>`;
    parent.appendChild(buttonsTd);
}

const baseURL = 'http://localhost:8090';
$(document).ready(async function() {
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
        const table = $("#locations-table tbody");
        for (const location of response) {
            const newLocationTr = document.createElement("tr");
            createElementFromAttribute(location.address, newLocationTr);
            createElementFromAttribute(location.city, newLocationTr);
            createElementFromAttribute(location.coordinateX, newLocationTr);
            createElementFromAttribute(location.coordinateY, newLocationTr);
            createButtons(newLocationTr, location);
            createPushPin(null, location.coordinateY, location.coordinateX, location.address)
            table.append(newLocationTr);
        }
    } else {
        console.log("Errror ");
    }
})

async function addLocation(){
 
    
    const data = {
        address: $('#inputAddress').val(),
        city: $('#inputCity').val(),
        coordinateX: $('#inputCoordX').val(),
        coordinateY: $('#inputCoordY').val()
    };    
    
    const responseJson = await fetch(
        baseURL + `/api/locations`,
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

async function deleteLocation(id){
    
    const responseJson = await fetch(
        baseURL + `/api/locations/` + id,
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


function createPushPin(name, lat, long, content){
    const pos = {lat: lat, lng: long};
    const window = new google.maps.InfoWindow({
        content: content
    })
    const marker = new google.maps.Marker({
        position: pos,
        map,
        title: name,
      });

    marker.addListener("click", () => {
        window.open({
            anchor: marker,
            map,
            shouldFocus: false,
        });
    });
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