function deleteStudent() {
	
	const studentId = document.getElementById('txtId').value;
	
    let url = "http://localhost:8080/webAppExercise/deleteStudent";
    let requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `studentID=${studentId}`
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
        .catch(error => console.error("Fetch failed: " + error))
return false;
	
}

function processResponseStatus(status) {
    if (status.errorCode === 0) {
        alert("Student data deleted.");
    } else if (status.errorCode === 1) {
        alert("Student data not deleted. Unknown student ID!");
    } else {
        alert("The database is temporarily unavailable. Please try again later.");
    }
}
