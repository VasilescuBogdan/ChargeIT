$(document).ready(function() {
    
    const data = new FormData(document.getElementById('form'));

    fetch(
        baseURL + `/api/bookings`,
        {
            method: 'POST',
            body: JSON.stringify(data),
            headders: {
                'Content-Type' : 'application/json'
            },
        });
})