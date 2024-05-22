function main() {
	// Get the URL query parameters
	const queryParams = new URLSearchParams(window.location.search);

	// Get the value of a specific parameter, for example 'id'
	const id = queryParams.get('id');
	
	fetch(`http://localhost:8080/webAppExercise/getStudentById?id=${id}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Failed to retrieve student data');
			}
			return response.json();
		})
		.then(studentData => {
			populateUpdateForm(studentData);
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

function populateUpdateForm(studentData) {
	
	document.getElementById("txtId").value = studentData.id;
	document.getElementById("txtFirstName").value = studentData.firstname();
	document.getElementById("txtLasttName").value = studentData.lastname();
	document.getElementById("txtStreetAddress").value = studentData.streetname();
	document.getElementById("txtPostalCode").value = studentData.postalcode();
	document.getElementById("txtPostOffice").value = studentData.postoffice();

	// Show the update form
	//document.getElementById("updateForm").style.display = "block";
}

main();


function updateStudent() {

	const form = document.forms["updateForm"];
const requestParameters = "id=" + encodeURIComponent(form["txtId"].value) +
    "&firstname=" + encodeURIComponent(form["txtFirstName"].value) +
    "&lastname=" + encodeURIComponent(form["txtLastName"].value) +
    "&streetaddress=" + encodeURIComponent(form["txtStreetAddress"].value) +
    "&postalcode=" + encodeURIComponent(form["txtPostalCode"].value) +
    "&postoffice=" + encodeURIComponent(form["txtPostOffice"].value);
;

	fetch("http://localhost:8080/webAppExercise/studentUpdate", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		},
		body: requestParameters
	})
		.then(response => response.json())
		.then(data => processResponseStatus(data))
		.catch(error => {
			alert("Post request failed: " + error);
		});
}

function processResponseStatus(data) {
	if (data.errorCode === 0) {
		alert("Chnage saved");
		location.replace("studentList.html");
	} else if (data.errorCode === 1) {
		alert("Failed to update record. Please try again later.");
	} else {
		alert("The database is temporarily unavailable. Please try again later." + data.errorCode);
	}
}

function cancel() {
    window.location.href = "Final.html";
}
