let map;

let volunteerMarker;
let donorMarker;
let ngoMarker;
let routeLine;


const donorIcon = L.icon({
    iconUrl:
    "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-blue.png",

    shadowUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png",

    iconSize:[25,41],
    iconAnchor:[12,41]
});


const ngoIcon = L.icon({
    iconUrl:
    "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-green.png",

    shadowUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png",

    iconSize:[25,41],
    iconAnchor:[12,41]
});


const volunteerIcon = L.icon({
    iconUrl:
    "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-red.png",

    shadowUrl:
    "https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png",

    iconSize:[25,41],
    iconAnchor:[12,41]
});



function initializeMap(lat, lon){

    if(map){
        return;
    }

    map = L.map('map').setView(
        [lat,lon],
        12
    );


    L.tileLayer(
        'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
        {
            attribution:'© OpenStreetMap'
        }
    ).addTo(map);

}





function startTracking(){

    let pickupRequestId =
    localStorage.getItem("pickupRequestId");


    if(!pickupRequestId){

        alert("No pickup request assigned");
        return;

    }


    if(!navigator.geolocation){

        alert("Geolocation not supported");
        return;

    }


    document.getElementById("status").innerHTML =
    "Tracking Started...";


    // Removed loadTracking here
    // Location must be saved first


    navigator.geolocation.watchPosition(

        updatePosition,

        showError,

        {
            enableHighAccuracy:true
        }

    );

}





function updatePosition(position){


    let latitude =
    position.coords.latitude;


    let longitude =
    position.coords.longitude;



    document.getElementById("lat").innerHTML =
    latitude;


    document.getElementById("lon").innerHTML =
    longitude;



    if(!map){

        initializeMap(
            latitude,
            longitude
        );

    }



    let pickupRequestId =
    localStorage.getItem("pickupRequestId");



    fetch(
        "/api/volunteer/location/" + pickupRequestId,
        {

            method:"PUT",

            headers:{
                "Content-Type":"application/json"
            },


            body:JSON.stringify({

                latitude:latitude,

                longitude:longitude

            })

        }
    )


    .then(response=>{


        if(!response.ok){

            throw new Error(
                "Location update failed"
            );

        }


        return response.text();

    })


    .then(()=>{

        console.log(
            "Location updated"
        );


        // Now tracking data exists
        loadTracking();


    })


    .catch(error=>{

        console.log(error);

    });

}





function showError(){

    alert(
        "Unable to fetch location"
    );

}





function loadTracking(){


    let requestId =
    localStorage.getItem("pickupRequestId");


    if(!requestId){

        console.log(
            "Pickup ID missing"
        );

        return;

    }



    console.log(
        "Loading tracking for:",
        requestId
    );



    fetch(
        "http://localhost:8080/api/tracking/" + requestId
    )


    .then(response=>{


        console.log(
            "Tracking Status:",
            response.status
        );


        return response.json();


    })


    .then(data=>{


        console.log(
            "Tracking Data:",
            data
        );


        updateTrackingUI(data);


    })


    .catch(error=>{


        console.log(
            "Tracking Error:",
            error
        );


    });


}







function updateTrackingUI(data){


    document.getElementById("volunteerName").innerHTML =
    data.volunteerName || "-";


    document.getElementById("donorName").innerHTML =
    data.donorName || "-";


    document.getElementById("ngoName").innerHTML =
    data.ngoName || "-";


    document.getElementById("pickupStatus").innerHTML =
    data.pickupStatus || "-";



    // Initialize map when first location is available
    if(!map && data.volunteerLatitude != null){

        initializeMap(
            data.volunteerLatitude,
            data.volunteerLongitude
        );

    }



    // Donor Marker
    if(
        !donorMarker &&
        data.donorLatitude != null &&
        data.donorLongitude != null
    ){

        donorMarker = L.marker(

            [
                data.donorLatitude,
                data.donorLongitude
            ],

            {
                icon: donorIcon
            }

        )
        .addTo(map)
        .bindPopup("📍 Donor");

    }




    // NGO Marker
    if(
        !ngoMarker &&
        data.ngoLatitude != null &&
        data.ngoLongitude != null
    ){

        ngoMarker = L.marker(

            [
                data.ngoLatitude,
                data.ngoLongitude
            ],

            {
                icon: ngoIcon
            }

        )
        .addTo(map)
        .bindPopup("NGO");

    }




    // Volunteer Marker
    if(
        data.volunteerLatitude != null &&
        data.volunteerLongitude != null
    ){


        if(!volunteerMarker){


            volunteerMarker = L.marker(

                [
                    data.volunteerLatitude,
                    data.volunteerLongitude
                ],

                {
                    icon: volunteerIcon
                }

            )
            .addTo(map)
            .bindPopup("Volunteer");


        }
        else{


            volunteerMarker.setLatLng(

                [
                    data.volunteerLatitude,
                    data.volunteerLongitude
                ]

            );

        }

    }





    // Remove previous route
    if(routeLine){

        map.removeLayer(routeLine);

    }




    // Draw route only when all three locations exist

    if(

        data.donorLatitude != null &&
        data.donorLongitude != null &&

        data.volunteerLatitude != null &&
        data.volunteerLongitude != null &&

        data.ngoLatitude != null &&
        data.ngoLongitude != null

    ){


        routeLine = L.polyline(

            [

                [
                    data.donorLatitude,
                    data.donorLongitude
                ],


                [
                    data.volunteerLatitude,
                    data.volunteerLongitude
                ],


                [
                    data.ngoLatitude,
                    data.ngoLongitude
                ]

            ],

            {
                weight:5
            }

        )
        .addTo(map);



        map.fitBounds(

            routeLine.getBounds()

        );


    }
    else{


        console.log(
            "Waiting for all locations to draw route"
        );


    }

}