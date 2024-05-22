package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        // Extract Exam Record id from request body
        int id = Integer.parseInt(request.getParameter("id"));

        // Call DAO method to delete student
        int errorCode = StudentDAO.deleteStudent(id);

        
        // Prepare JSON response
        String jsonResponse = new Gson().toJson(new Status(errorCode));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send JSON response
        response.getWriter().write(jsonResponse);
        
    }
}

class Status {
    private int errorCode;

    public Status(int errorCode) {
        this.errorCode = errorCode;
    }
}
