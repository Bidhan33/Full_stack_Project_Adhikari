package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class CalcServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	PrintWriter out = response.getWriter();

		// 2. Get the values of the request parameters
		int x = Integer.parseInt(request.getParameter("x"));

		int y = Integer.parseInt(request.getParameter("y"));
		String operation = request.getParameter("operation");

        int result = 0;

       
        if (operation.equals("subtract")) {
            result = x - y;
        } else {
            // Handle other operations if needed
            out.println("Error: Invalid operation");
            return;
        }

        // Write the text to the response (to be sent to the browser)
        out.println(x + " - " + y + " = " + result);
    }
}