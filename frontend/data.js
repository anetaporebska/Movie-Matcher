const MOVIE_URL = "http://localhost:8080/api/movie";

var curr_movie = -1;

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

function load(){
    const options = {
        method: 'GET', 
        headers: getHeaders()
    }
    fetch(MOVIE_URL, options).then(response => response.json()).then(result => {
        curr_movie = result;
        console.log(curr_movie)
        document.getElementById("title").innerHTML = curr_movie.title;
        document.getElementById("date").innerHTML = curr_movie.releaseDate;
        document.getElementById("vote").innerHTML = curr_movie.vote;

        var genres = "";
        for(var i=0; i<curr_movie.genres.length; i++){
            genres += curr_movie.genres[i].name + " ";
        }
        document.getElementById("genres").innerHTML = genres;
    });

    
}

function addPreference(num){
    const options = {
        method: 'POST', 
        headers: getHeaders()
    }
    fetch(MOVIE_URL + "/" + curr_movie.movieId + "/" + num, options).then(response => response.json()).then(result => {
        console.log(result);
        load();
    });
}


function seen(){
    addPreference("0");
}

function wantToWatch(){
    addPreference("1");
}

function doNotWantToWatch(){
    addPreference("2");
}

load();