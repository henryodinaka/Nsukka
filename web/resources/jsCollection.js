//adding sticky navbar
window.onscroll = function() {myFunction();changeFont();};

var navbar = document.getElementById("header");
var sticky = navbar.offsetTop;

function myFunction() {
  if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky")
  } else {
    navbar.classList.remove("sticky");
  }
}
//Getting element by Id the contents of htlm

var change = document.getElementById("change");
change.innerHTML = "This Text was changed using javascript";


//gettung element by tag name
var h = document.getElementById("home");
h.onclick =changeFont();
var para = document.getElementsByTagName("p");
function changeFont(){ 
    if (window.pageYOffset >= sticky) {
    para.classList.add("para");
  } else {
    para.classList.remove("para");
  }
}

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};