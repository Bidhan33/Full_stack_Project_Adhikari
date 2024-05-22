package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Student;

@WebServlet("/studentUpdate")
public class studentUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the updated student data from the request parameters
    	int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String streetaddress = request.getParameter("streetaddress");
        int postalcode = Integer.parseInt(request.getParameter("postalcode"));
        String postoffice = request.getParameter("postoffice");

        // Create a Student object with the updated data
        Student student = new Student(id, firstname, lastname, streetaddress, postoffice, postalcode);

        // Update the student record in the database
        StudentDAO studentDAO = new StudentDAO();
        int errorCode = studentDAO.insertStudent(student);

        // Prepare JSON response
        String jsonResponse = new Gson().toJson(new ErrorResult(errorCode));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send JSON response
        response.getWriter().write(jsonResponse);
    }

    // Inner class for error result
    class ErrorResult {
        private int errorCode;

        public ErrorResult(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return errorCode;
        }
    }
}