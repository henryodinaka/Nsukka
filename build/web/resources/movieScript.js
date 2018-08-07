
function myAlert() {
    window.alert("message");
}

window.onscroll = function () {
//    classNameSticky();
//    classNameStickySideBar();
};
var navbar = document.getElementById("header");
var sideBar = document.getElementById("sidebar");
var sticky = navbar.offsetTop;
function classNameSticky() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}
function classNameStickySideBar() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky-side-bar")
    } else {
        navbar.classList.remove("sticky-side-bar");
    }
}
// Get the modal 
var modal = document.getElementById('id01');
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
var username = document.getElementById("regForm:uName");
var password = document.getElementById("regForm:pass");
var cPassword = document.getElementById("regForm:cPass");
var fName = document.getElementById("regForm:fName");
var lName = document.getElementById("regForm:lName"); 
var phone = document.getElementById("regForm:phone");
var email = document.getElementById("regForm:email"); 

//login form validation
function loginValidation() {
    var username = document.getElementById("login-form:uName");
    var password = document.getElementById("login-form:pass");
    if (username == null || username.value == '') {
        return false;
        alert('Username can not be empty');
    }
    if (password == null || password.value == '') {
        alert('Password field must be field')
        return false;
    }

    return true;
}

function userNameVale() {
    var username_len = username.value.length;
    if (username_len == 0 || username_len >= max || username_len < min)
    {
        document.getElementById("uNameErr").innerHTML = ("User Id should not be empty / length be between " + min + " to " + max);
        username.focus();
        return false;
    }
    return true;
}

function passwordVal()
{
    var min =5, max=10;
    var passwordLength = password.value.length;
    if (passwordLength == 0 || passwordLength >= max || passwordLength < min)
    {
        document.getElementById("passErr").innerHTML = ("Password should not be empty / length be between " + min + " to " + max);
        password.focus();
        return false;
    }
    return true;
}
function confrimPass() {
    var pass = document.getElementById("regForm:pass").value;
    var cPass = document.getElementById("regForm:cPass").value;
    if (cPass !== pass) {
        document.getElementById("passMatch").innerHTML = "Password not match";
        return false;
    }
    return true;
}
function allLetterFname()
{
    var letters = /^[A-Za-z]+$/;
    if (fName.value.match(letters))
    {
        return true;
    } else
    {
        document.getElementById("fNameErr").innerHTML = ('Name must have alphabet characters only');
        fName.focus();
        return false;
    }
}
function allLetterLname()
{
    var letters = /^[A-Za-z]+$/;
    if (lName.value.match(letters))
    {
        return true;
    } else
    {
        document.getElementById("lNameErr").innerHTML = ('Name must have alphabet characters only');
        lName.focus();
        return false;
    }
}
function alphanumeric()
{
    var letters = /^[0-9a-zA-Z]+$/;
    if (uadd.value.match(letters))
    {
        return true;
    } else
    {
        document.getElementById("uAddress").innerHTML = ('User address must have alphanumeric characters only');
        uadd.focus();
        return false;
    }
}
//function countryselect(ucountry)
//{
//    if (ucountry.value == "Default")
//    {
//        alert('Select your country from the list');
//        ucountry.focus();
//        return false;
//    } else
//    {
//        return true;
//    }
//}
function allnumeric()
{
    var len = 11;
    var numbers = /^[0-9]+$/;
    var phoneLen = phone.value.length();
    if (phone.value.match(numbers) && phoneLen == len)
    {
        return true;
    } else
    {
        document.getElementById('phoneErr').innerHTML = ('phone number must contain 10 numeric characters only');
        phone.focus();
        return false;
    }
}
function ValidateEmail()
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (email != null && email.value.match(mailformat))
    {
        return true;
    } else
    {
        document.getElementById("emailErr").innerHTML = ("You have entered an invalid email address!");
        email.focus();
        return false;
    }
}
//function validsex(umsex, ufsex){
//    x = 0;
//
//    if (umsex.checked)
//    {
//        x++;
//    }
//    if (ufsex.checked)
//    {
//        x++;
//    }
//    if (x == 0)
//    {
//        alert('Select Male/Female');
//        umsex.focus();
//        return false;
//    } else
//    {
//        alert('Form Succesfully Submitted');
//        window.location.reload()
//        return true;
//    }
//}
