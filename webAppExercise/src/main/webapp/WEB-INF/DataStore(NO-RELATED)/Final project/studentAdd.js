function addStudent() {
    let form = document.getElementById("formStudent");
    let url = "http://localhost:8080/webAppExercise/addStudent";
    let parameterData =
        "id=" + encodeURIComponent(form["txtId"].value) +
        "&firstname=" + encodeURIComponent(form["txtFirstName"].value) +
        "&lastname=" + encodeURIComponent(form["txtLastName"].value) +
        "&streetaddress=" + encodeURIComponent(form["txtStreetAddress"].value) +
        "&postalcode=" + encodeURIComponent(form["txtPostalCode"].value) +
        "&postoffice=" + encodeURIComponent(form["txtPostOffice"].value);

    let requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: parameterData
    };

    fetch(url, requestOptions)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw "HTTP status code is " + response.status;
            }
        })
        .then(status => processResponseStatus(status))
        .catch(error => {
            alert("Post request failed: " + error);
        });
}

function processResponseStatus(status) {
    if (status.errorCode === 0) {
        alert("Student data added.");
    } else if (status.errorCode === 1) {
        alert("Cannot add the student. The ID is already in use.");
    } else {
        alert("The database is temporarily unavailable. Please try again later.");
    }
}
