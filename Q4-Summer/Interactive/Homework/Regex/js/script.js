console.log("Ooog");

const form = document.getElementById("myForm");
form.addEventListener('submit', handleForm);

const fNameTag = document.getElementById("inFName");
const lNameTag = document.getElementById("inLName");
const emailTag = document.getElementById("inEmail");
const addressTag = document.getElementById("inAddr");
const cityTag = document.getElementById("inCity");
const stateTag = document.getElementById("inState");
const zipTag = document.getElementById("inZip");
const phoneTag = document.getElementById("inPhone");

const passTag = document.getElementById("inPass");
const vPassTag = document.getElementById("inVPass");

function handleForm(e) {
    e.preventDefault();
    console.log("urmom");


    // Get values from text boxes
    let fName = fNameTag.value;
    let lName = lNameTag.value;
    let email = emailTag.value;
    let address = addressTag.value;
    let city = cityTag.value;
    let state = stateTag.value;
    let zipcode = zipTag.value;
    let phoneNum = phoneTag.value;

    let password = passTag.value;
    let vPassword = vPassTag.value;


    // validate
    // Check if the user even entered anything
    let isValid = true;

    if (fName == null || fName == '') {
        isValid = false;
        console.log("fName");
    }

    if (lName == null || lName == '') {
        isValid = false;
        console.log("lName");
    }

    if (email == null || email == '') {
        isValid = false;
        console.log("email");
    }

    if (address == null || address == '') {
        isValid = false;
        console.log("adress");
    }

    if (city == null || city == '') {
        isValid = false;
        console.log("city");
    }

    if (state == null || state == '') {
        isValid = false;
        console.log("state");
    }

    if (zipcode == null || zipcode == '') {
        isValid = false;
        console.log("zip");
    }

    if (phoneNum == null || phoneNum == '') {
        isValid = false;
        console.log("phone");
    }

    if (password == null || password == '') {
        isValid == false;
        console.log("pass");
    }

    if (vPassword == null || vPassword == '' || vPassword != password) {
        isValid = false;
        console.log("vPass");
    }

    // Check that entered data is valid via regex
    let fNameReg = /^\w{2,}$/;
    let lNameReg = /^\w{2,}$/;
    let emailReg = /^(?=[A-z])[A-z0-9_.]*@(?=[^0-9])[A-z0-9]*?\.[A-z]{3,4}$/;
    let addressReg = /^.+ .+$/;
    let cityReg = /^\w{2,}$/;
    let stateReg = /^([Aa][LKSZRAEPlkszraep]|[Cc][AOTaot]|[Dd][ECec]|[Ff][LMlm]|[Gg][AUau]|[Hh][Ii]|[Ii][ADLNadln]|[Kk][SYsy]|[Ll][Aa]|[Mm][ADEHINOPSTadehinopst]|[Nn][CDEHJMVYcdehjmvy]|[Oo][HKRhkr]|[Pp][ARWarw]|[Rr][Ii]|[Ss][CDcd]|[Tt][NXnx]|[Uu][Tt]|[Vv][AITait]|[Ww][AIVYaivy])$/;
    let zipReg = /^(\d{5})(-(\d{4}))?$/;
    let phoneReg = /^(((\d)\()?\W?([0-9]{3})\W*([0-9]{3})\W*([0-9]{4}))$/;
    let passReg = /^.*(?=.*([0-9].*){1,})(?=.*(\W.*){1,})(?=.*([A-Z].*){1,}).{8,}.*$/;


    var fNameResult = fName.match(fNameReg);
    var lNameResult = lName.match(lNameReg);
    var emailResult = email.match(emailReg);
    var addressResult = address.match(addressReg);
    var cityResult = city.match(cityReg);
    var stateResult = state.match(stateReg);
    var zipResult = zipcode.match(zipReg);
    var phoneResult = phoneNum.match(phoneReg);
    var passResult = password.match(passReg);
    
    if (!fNameResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your first name is entered correctly";
        node.appendChild(elmnt);
    }

    if (!lNameResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your last name is entered correctly";
        node.appendChild(elmnt);
    }

    if (!emailResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your email is entered correctly";
        node.appendChild(elmnt);
    }

    if (!addressResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your address is entered correctly";
        node.appendChild(elmnt);
    }

    if (!cityResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your city is entered correctly";
        node.appendChild(elmnt);
    }

    if (!stateResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that the state field is a valid 2 character state code";
        node.appendChild(elmnt);
    }

    if (!zipResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your zipcode is entered correctly";
        node.appendChild(elmnt);
    }

    if (!phoneResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Please check that your phone number is entered correctly";
        node.appendChild(elmnt);
    }

    if (!passResult) {
        isValid = false;
        var node = document.getElementById("header")
        var elmnt = document.createElement("h2");
        elmnt.innerText = "Error - Password must reach complexity requirements";
        node.appendChild(elmnt);
    }


    // if valid
    // success page
    // else
    // Update form with error messages

    if (isValid) {
        window.location.href = "success.html";
    }
}