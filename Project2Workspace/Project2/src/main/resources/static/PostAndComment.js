import { CommentDisplayService } from "./js/comment-display-service";
import { PostDisplayService } from "./js/post-display-service";
import { PostGetService } from "./js/post-get-service";
import {CommentGetService} from "./js/comment-get-service"




window.onload= function(){
document.getElementById('submitPost').addEventListener('click',CreatePost);
//document.getElementById('reply').addEventListener('click', CreateComment);
}
function CreatePost(){
    let xmlhttp = new XMLHttpRequest();
    let post= {
        "postMessage":document.getElementById("postMessage").value,
        "numOfLikes":0
    };


    console.log("made it to Create Post");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200")

    readallPost();

           
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:2005/AddPost');
    xmlhttp.setRequestHeader('content-type','application/json')
    xmlhttp.send(JSON.stringify(post));

     
    

};

function CreateComment(){
    let xmlhttp = new XMLHttpRequest();
    let Comment= {
        "commentMessage":document.getElementById("commentMessage").value
     
    };


    console.log("made it to Create message");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200");
           

             //DOMManipulation(Object); Frederick stuff 
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:2005/AddComment');
    xmlhttp.setRequestHeader('content-type','application/json')
    xmlhttp.send(JSON.stringify(Comment));
}

function readallPost(){


let xmlhttp = new XMLHttpRequest();
    let post= {
        "postMessage":document.getElementById("postMessage").value,
        "numOfLikes":0
    };


    console.log("made it to Create Post");

    xmlhttp.onreadystatechange = function (){
        console.log("inside on ready state changefunction")

        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            console.log("state is 4 status 200")
            let post1=JSON.parse(xmlhttp.responsebody);

    

           
           
        };
    };
    
    xmlhttp.open('POST', 'http://localhost:2005/ReadPost');
    xmlhttp.send();

     
    

};