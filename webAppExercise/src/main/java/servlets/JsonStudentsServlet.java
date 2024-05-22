package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Student;

@WebServlet("/JsonStudentsServlet")
public class JsonStudentsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json"); // 0.
		response.setCharacterEncoding("UTF-8");
		StudentDAO studentDAO = new StudentDAO();
		List<Student> studentList = studentDAO.getAllStudents();
		Gson gson = new Gson();
		String jsonString = gson.toJson(studentList);

		out.println(jsonString);

	}
}
