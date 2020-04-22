// This Servlet will be used to listen for users trying to view all the
// Products - likely by going to a page like "/products'

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowProductServlet", urlPatterns = "/product/show")
public class ShowProductServlet extends HttpServlet {
    // will require a doGet()


    // this method assumes that there **IS** a product id in the URL parameters

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check to see if there is an id passed in URL parameters
            // i.e. http://localhost:8080/products/show?id=27
        long productId = Long.parseLong(request.getParameter("id")); // checking for Id parameters

        // Create a connection to the DaoFactory
        Products productDao = DaoFactory.getProductsDao();
            // This will give us access to all the Products Interface methods

        // Get the product by its ID from ListProductsDao
        Product product = productDao.findById(productId);

        // set the attribute "product" to the object we just created
        request.setAttribute("product", product);
        request.getRequestDispatcher("/products/product-show.jsp").forward(request,response);

    }
}
