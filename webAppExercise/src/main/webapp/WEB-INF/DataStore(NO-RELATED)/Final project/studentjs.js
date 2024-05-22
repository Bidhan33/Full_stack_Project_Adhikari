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
            <button data-id="${student.id}" class="add-btn">Add</button>
            <button data-id="${student.id}" class="delete-btn">Delete</button>
          </td
            `;
				studentListBody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching student data:', error);
		});
}

window.onload = fetchStudentList;