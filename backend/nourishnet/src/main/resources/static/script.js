function validateRegisterForm(event)
{
    event.preventDefault();
console.log("Register button clicked");
    let name = document.getElementById("fullName").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let role = document.getElementById("role").value;

    if(name === "" || email === "" || password === "")
    {
        alert("Please fill all fields");
        return false;
    }

    fetch("/api/users/register", {
//sends a request to your Spring Boot backend
        method: "POST",
//create a new record
        headers: {

            "Content-Type":"application/json"

        },

        body: JSON.stringify({
//converts javascript to json
            fullName: name,
            email: email,
            password: password,
            role: role

        })

    })

    .then(response => {

        if(!response.ok){

            throw new Error("Registration Failed");

        }

        return response.json();

    })

    .then(data => {

        alert("Registration Successful!");

        window.location.href = "login.html";
//Takes the user to the login page
    })

    .catch(error => {

        alert(error.message);

    });

    return false;
}
async function validateLoginForm(event) {

    event.preventDefault();

    let email = document.getElementById("loginEmail").value;
    let password = document.getElementById("loginPassword").value;


    let user = {
        email: email,
        password: password
    };


    try {

        let response = await fetch(
            "http://localhost:8080/api/users/login",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(user)
            }
        );


        if(response.ok) {

            let userData = await response.json();

            alert("Login successful");


            // Store logged-in user details
            localStorage.setItem(
                "user",
                JSON.stringify(userData)
            );


            // Redirect based on role
            if(userData.role === "Donor") {

    window.location.href = "donor-dashboard.html";

}
else if(userData.role === "NGO") {

    window.location.href = "ngo-dashboard.html";

}
else if(userData.role === "Volunteer") {

    window.location.href = "volunteer-dashboard.html";

}
else{

    alert("Unknown user role");

}
        }
        else {

            alert("Invalid email or password");

        }

    }
    catch(error) {

        console.log(error);
        alert("Unable to connect to server");

    }

}
async function validateDonationForm(event)
{
    event.preventDefault();

    let foodName = document.getElementById("foodName").value;
    let foodQuantity = document.getElementById("foodQuantity").value;
    let preparationTime = document.getElementById("preparationTime").value;
    let expiryDate = document.getElementById("expiryDate").value;
    let donorLocation = document.getElementById("donorLocation").value;
    let foodType = document.getElementById("foodType").value;

    if(
        foodName==="" ||
        foodQuantity==="" ||
        preparationTime==="" ||
        expiryDate==="" ||
        donorLocation==="")
    {
        alert("Please fill all fields");
        return;
    }

    let loggedInUser =
        JSON.parse(localStorage.getItem("user"));

    let donation = {

    foodName: foodName,

    foodQuantity: foodQuantity,

    preparationTime: preparationTime,

    expiryDate: expiryDate,

    donorLocation: donorLocation,

    foodType: foodType

};
   try{

        let response =
        await fetch(
    "http://localhost:8080/api/users/" +
    loggedInUser.userId +
    "/donations",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify(donation)

            });

      if(response.ok){

    let savedDonation = await response.json();

    let donationId = savedDonation.donationId;


    localStorage.setItem(
        "donationId",
        donationId
    );


    alert("Food Donation Submitted Successfully!");


    showNearestNgo(donationId);


}
        else{

            alert("Unable to submit donation.");

        }

    }
    catch(error){

        console.log(error);

        alert("Server Error");

    }
}
    async function loadDonations() {

    let table = document.getElementById("donationTable");

    if (!table) {
        return;
    }

    try {

        let response = await fetch("/api/donations");

        let donations = await response.json();
//This converts JSON into JavaScript objects.
// Hide expired food donations from NGO dashboard
donations = donations.filter(function(donation){

    return donation.freshnessStatus !== "Expired";

});
console.log(donations);
        table.innerHTML = "";

        donations.forEach(function(donation) {
//loop through every donation
            let row = `
                <tr>

                    <td>${donation.foodName}</td>

                    <td>${donation.foodQuantity}</td>

                    <td>${donation.donorLocation}</td>

                    <td>${donation.freshnessStatus}</td>

                    <td>

                        <button onclick="acceptDonation(${donation.donationId})">
                            Accept
                        </button>

                    </td>

                </tr>
            `;

            table.innerHTML += row;

        });

    }

    catch(error) {

        console.log(error);

        alert("Unable to load donations");

    }

}
window.onload = function() {

    loadDonations();

}
async function acceptDonation(id)
{

    let user =
    JSON.parse(localStorage.getItem("user"));


    if(!user)
    {
        alert("Please login again");
        return;
    }


    try
    {

        let pickupResponse = await fetch(
            "http://localhost:8080/api/donations/"
            + id
            + "/accept/"
            + user.userId,
            {
                method:"POST"
            }
        );


        if(pickupResponse.ok)
        {
            alert("Donation Accepted and Pickup Request Created!");

            loadDonations();

        }
        else
        {
            alert("Unable to accept donation");
        }


    }
    catch(error)
    {
        console.log(error);
        alert("Server Error");
    }

}
async function getVolunteers() {

    try {

        let response = await fetch(
            "http://localhost:8080/api/users/volunteers"
        );

        if (!response.ok) {
            throw new Error("Unable to load volunteers");
        }

        return await response.json();

    }
    catch (error) {

        console.log(error);

        return [];

    }

}
async function showNearestNgo(donationId)
{

    try{

        let response = await fetch(
            "http://localhost:8080/api/recommendation/nearest?donationId="
            + donationId
        );


        let ngos = await response.json();


        if(ngos.length > 0)
        {

            let nearestNgo = ngos[0];


            alert(
                "Recommended NGO:\n\n"
                + nearestNgo.ngoName
                + "\nDistance: "
                + nearestNgo.distance
                + " km"
            );

        }
        else
        {

            alert(
                "No nearby NGO found"
            );

        }


        window.location.href =
        "donor-dashboard.html";


    }
    catch(error)
    {

        console.log(error);

        alert(
            "Unable to get NGO recommendation"
        );

    }

}

