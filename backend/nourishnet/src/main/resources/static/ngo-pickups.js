window.onload = function () {

    loadPickupRequests();

};

async function loadPickupRequests() {

    let user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
        alert("Please login again");
        return;
    }

    try {

        // Load pickup requests
        let pickupResponse = await fetch(
            "http://localhost:8080/api/pickups/ngo/" + user.userId
        );

        let pickups = await pickupResponse.json();

        // Load volunteers
        let volunteerResponse = await fetch(
            "http://localhost:8080/api/users/volunteers"
        );

        let volunteers = await volunteerResponse.json();

        let table = document.getElementById("pickupTable");

        table.innerHTML = "";

        pickups.forEach(function (pickup) {

            let volunteerOptions = "";

            volunteers.forEach(function (volunteer) {

                volunteerOptions += `
                    <option value="${volunteer.userId}">
                        ${volunteer.fullName}
                    </option>
                `;

            });

            let volunteerColumn = "";
            let actionColumn = "";

            if (pickup.volunteer != null) {

    volunteerColumn = pickup.volunteer.fullName;


    if(pickup.pickupStatus === "Delivered"){

        actionColumn = "Delivered";

    }
    else{

        actionColumn = "Assigned";

    }

}else {

                volunteerColumn = `
                    <select id="volunteer${pickup.requestId}">
                        ${volunteerOptions}
                    </select>
                `;

                actionColumn = `
                    <button onclick="assignVolunteer(${pickup.requestId})">
                        Assign
                    </button>
                `;
            }

            let row = `
                <tr>

                    <td>${pickup.donation.foodName}</td>

                    <td>${pickup.donation.foodQuantity}</td>

                    <td>${pickup.donation.user.fullName}</td>

                    <td>${pickup.pickupStatus}</td>

                    <td>${volunteerColumn}</td>

                    <td>${actionColumn}</td>

                </tr>
            `;

            table.innerHTML += row;

        });

    }
    catch (error) {

        console.log(error);

        alert("Unable to load pickup requests");

    }

}

async function assignVolunteer(requestId) {

    let volunteerId = document.getElementById(
        "volunteer" + requestId
    ).value;

    try {

        let response = await fetch(
            "http://localhost:8080/api/pickups/"
            + requestId
            + "/assign/"
            + volunteerId,
            {
                method: "PUT"
            }
        );

        if (response.ok) {

            alert("Volunteer assigned successfully!");

            loadPickupRequests();

        } else {

            alert("Unable to assign volunteer");

        }

    }
    catch (error) {

        console.log(error);

        alert("Server Error");

    }

}