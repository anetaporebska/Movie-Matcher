console.log("hi")

async function getData(){
    const response = await fetch('http://localhost:8080/api/v1/movie/278');
    var movie = await response.json();
    console.log(movie);
}

getData()