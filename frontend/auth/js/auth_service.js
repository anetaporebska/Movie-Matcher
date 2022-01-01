const AUTH_URL = "http://localhost:8080/api/auth/";

function getOptions(){
    const username = document.getElementById("uname").value;
    const password = document.getElementById("psw").value;

    console.log("login " + username);

    const options = {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "username": username, 
            "password": password
        })
    }
    return options;
}


function login(){
    const options = getOptions();
    fetch(AUTH_URL + "signin", options).then(response => response.json()).then(result => {
        if (result.accessToken){
            localStorage.setItem("user", JSON.stringify(result));
        }
        location.href = "http://127.0.0.1:5500/data/data.html";
        return result;
    });
}

function logout(){
    localStorage.removeItem("user");
    // TODO send logout to server
    location.href = "index.html";
}

function register(){
    const options = getOptions();
    fetch(AUTH_URL + "signup", options).then(response => response.json()).then(result => {
        console.log(result);
        location.href = "login.html";
        return result;
    });
}

