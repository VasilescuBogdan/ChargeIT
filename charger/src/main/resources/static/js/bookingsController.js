
const baseURL = 'http://localhost:8090';
function addBooking(){    
     
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
            headders: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(data)
        });
    
    console.log(responseJson);
}

