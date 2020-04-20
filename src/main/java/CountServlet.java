import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



    @WebServlet(name = "CountServlet", urlPatterns = "/count")
    public class CountServlet extends HttpServlet {

        int count = 0;
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            count ++;


            String reset = request.getParameter("reset");

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (reset == null) {
                out.println("<h4>This page has been viewed: " + count + " times.</h4>");
            } else if (reset.equalsIgnoreCase("true")) {
                count = 0;
                out.println("<h4 style=color:rebeccapurple>Page counter has been reset.</h4>");
            } else {
                out.println("<h4>This page has been viewed: " + count + " times.</h4>");
            }
        }
    }