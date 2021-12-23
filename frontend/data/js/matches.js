const MOVIE_URL = "http://localhost:8080/api/movie";


function getCurrentUser(){
    return JSON.parse(localStorage.getItem("user"));
}

function getHeaders(){
    const user = getCurrentUser();
    const auth = 'Bearer ' + user.accessToken
    const headers = {
            'Content-Type': 'application/json',
            'Authorization': auth
        }
    return headers;
}

function matchingMovies(){
    const options = {
        method: 'GET', 
        headers: getHeaders()
    }
    const user = document.getElementById("user").value;
    fetch(MOVIE_URL + "/" + user, options).then(response => response.json()).then(result => {
        var resultText = "<div id=\"movie-result\"> <ul>"
        for(var i=0; i<result.length; i++){
            resultText += ("<li>" + result[i].title +  "</li>");
        }
        resultText += "</ul> </div>";
        document.getElementById("matching-movies").innerHTML = resultText;
    });
}