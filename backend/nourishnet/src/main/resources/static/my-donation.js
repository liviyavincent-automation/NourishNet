window.onload=function(){

loadMyDonations();

};



async function loadMyDonations(){


let user =
JSON.parse(localStorage.getItem("user"));


let response =
await fetch(
"http://localhost:8080/api/donations/user/"
+ user.userId
);


let donations =
await response.json();



let table =
document.getElementById("myDonationTable");


table.innerHTML="";


donations.forEach(function(donation){


let row = `

<tr>

<td>
${donation.foodName}
</td>

<td>
${donation.foodQuantity}
</td>

<td>
${donation.donorLocation}
</td>

<td>
${donation.freshnessStatus}
</td>


</tr>

`;


table.innerHTML += row;


});


}