package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_access.StudentDAO;
import model.Student;

/**
 * Servlet implementation class StudentAddServlet
 */
@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String streetaddress = request.getParameter("streetaddress");
        int postalcode = Integer.parseInt(request.getParameter("postalcode"));
        String postoffice = request.getParameter("postoffice");

   
        Student student = new Student(id, firstname, lastname, streetaddress, postoffice, postalcode);


        
        StudentDAO studentDAO = new StudentDAO();
        int errorCode = studentDAO.insertStudent(student);

       
        String jsonResponse = "{\"errorCode\": " + errorCode + "}";

        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
	