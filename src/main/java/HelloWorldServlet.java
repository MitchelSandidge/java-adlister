import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    int count = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        count ++;
        String name = request.getParameter("name");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (name == null) {
            out.println("<h1>Hello, World!</h1><h4>This page has been viewed: " + count + " times.</h4>");
        } else {
            out.println("<h1>Hello, " + name + "!</h1><h4>This page has been viewed: " + count + " times.</h4>");
        }
    }

}



