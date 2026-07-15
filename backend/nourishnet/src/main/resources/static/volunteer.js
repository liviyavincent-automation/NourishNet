window.onload = function(){

    loadVolunteerPickups();

};



async function loadVolunteerPickups(){


    let user =
    JSON.parse(localStorage.getItem("user"));


    if(!user){
        alert("Please login again");
        return;
    }


    let volunteerId = user.userId;



    try{


        let response =
        await fetch(
        "http://localhost:8080/api/pickups/volunteer/"
        + volunteerId
        );


        let pickups =
        await response.json();


        console.log(pickups);


        let table =
        document.getElementById("pickupTable");


        table.innerHTML="";



        pickups.forEach(function(pickup){


            let row = `

            <tr>

            <td>
            ${pickup.donation.foodName}
            </td>


            <td>
            ${pickup.donation.foodQuantity}
            </td>


            <td>
            ${pickup.donation.user.fullName}
            </td>


            <td>
            ${pickup.ngo.fullName}
            </td>


            <td>
            ${pickup.pickupStatus}
            </td>




<td>

${
pickup.pickupStatus === "Assigned"
?
`
<button onclick="startTracking(${pickup.requestId})">
Start Tracking
</button>

<br><br>
`
:
""
}


${
pickup.pickupStatus !== "Delivered"
?
`
<button onclick="deliverPickup(${pickup.requestId})">
Deliver Food
</button>
`
:
`
Delivered
`
}

</td>
            </tr>

            `;


            table.innerHTML += row;


        });



    }
    catch(error){

        console.log(error);

        alert("Unable to load pickups");

    }

}




function startTracking(requestId){


    localStorage.setItem(
        "pickupRequestId",
        requestId
    );


    window.location.href =
    "tracking.html";


}
async function deliverPickup(requestId) {

    try {

        let response = await fetch(
            "http://localhost:8080/api/pickups/"
            + requestId
            + "/status?status=Delivered",
            {
                method:"PUT"
            }
        );


        if(response.ok){

            alert(
                "Food delivered successfully to NGO!"
            );


            loadVolunteerPickups();

        }
        else{

            alert(
                "Unable to update delivery status"
            );

        }

    }
    catch(error){

        console.log(error);

        alert(
            "Server Error"
        );

    }

}