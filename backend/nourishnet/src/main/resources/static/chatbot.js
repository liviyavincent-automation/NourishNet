async function askBot() {

    let question =
        document.getElementById("question").value.trim();

    if(question === ""){

        alert("Please enter your question.");

        return;

    }

    document.getElementById("answer").innerHTML =
        "Thinking...";


    try{

        let response = await fetch(

            "http://localhost:8080/api/chat",

            {

                method:"POST",

                headers:{
                    "Content-Type":"text/plain"
                },

                body:question

            }

        );


        if(!response.ok){

            throw new Error("Unable to get response");

        }


        let answer =
            await response.text();


        document.getElementById("answer").innerHTML =
            answer;

    }

    catch(error){

        console.log(error);

        document.getElementById("answer").innerHTML =
            "Unable to contact NourishBot.";

    }

}