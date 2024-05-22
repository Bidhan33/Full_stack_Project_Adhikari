function fetchStudentList() {
	fetch('http://localhost:8080/webAppExercise/Student')
		.then(response => response.json())
		.then(data => {

			const studentListBody = document.getElementById('studentList');

			data.forEach(student => {
				const row = document.createElement('tr');
				row.innerHTML = `
                <td>${student.id}</td>
                <td>${student.lastname}</td>
                <td>${student.firstname}</td>
                <td>${student.streetaddress}</td>
                <td>${student.postcode}</td>
                <td>${student.postoffice}</td>
                <td>
            <button onClick="updateStudent('${student.id}')" class="add-btn">UPDATE</button>
            <button onClick="deleteStudent('${student.id}')" class="delete-btn">DELETE</button>
          </td
            `;
				studentListBody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching student data:', error);
		});
		function createUpdateDeleteLinks(studentId) {
  
  const deleteLink = `<a href="#" onclick="deleteStudent(${studentId})">Delete</a>`;
  return  deleteLink; // Use a space for better readability
}
}

function deleteStudent(id) {
	var confirmDelete = confirm("Are you sure you want to delete this record id("+id+")?\nPress OK if you wish to continue, else CANCEL");

	if (confirmDelete) {
		fetch("http://localhost:8080/webAppExercise/deleteStudent", {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			body: `id=${id}`
		})
			.then(response => response.json())
			.then(data => processResponseStatus(data))
			.catch(error => {
				alert("Post request failed: " + error);
			});
	}

}

function processResponseStatus(data) {
	if (data.errorCode === 0) {
		alert("Student data deleted!");
		location.reload();
	} else if (data.errorCode === 1) {
		alert("Student data not deleted. Unknown student id!");
	} else {
		alert("The database is temporarily unavailable. Please try again later.");
	}
}

function updateStudent(id) {
	window.location.href = `studentUpdate.html?id=${id}`;
}

window.onload = fetchStudentList;
