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
import model.Student;

@WebServlet("/getStudentById")
public class GetStudentById extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int studentId = Integer.parseInt(request.getParameter("id"));

       
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

       
        String jsonStudent = new Gson().toJson(student);

        
        response.setContentType("application/json");
        
       
        PrintWriter out = response.getWriter();
        out.print(jsonStudent);
        out.flush();
    }
}
