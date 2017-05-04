package servlets;

import com.google.gson.Gson;
import models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Adam Blasko on 03.05.2017.
 */
@WebServlet(name = "ProductsServlet")
public class ProductsServlet extends HttpServlet {
    private static List<Product> productsList;

    @Override
    public void init() throws ServletException {
        super.init();
        productsList = new ArrayList<>();
        productsList.add(new Product(1, 132, "Prod1"));
        productsList.add(new Product(2, 314, "Prod2"));
        productsList.add(new Product(3, 634, "Prod3"));
        productsList.add(new Product(4, 981, "Prod4"));
        productsList.add(new Product(5, 156987, "Prod5"));
        productsList.add(new Product(6, 1635, "Prod6"));
        productsList.add(new Product(7, 7980, "Prod7"));
        productsList.add(new Product(8, 800, "Prod8"));
        productsList.add(new Product(9, 150, "Prod9"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedProductId = request.getParameter("id");

        if (requestedProductId != null && !Objects.equals(requestedProductId, "")){
            Gson gson = new Gson();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            int productId;
            Product requestedProduct = null;
            try{
                productId = Integer.parseInt(requestedProductId);
                for (Product product : productsList) {
                    if (product.getIdentificator() == productId){
                        requestedProduct = product;
                        break;
                    }
                }
                if (requestedProduct == null){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                else{
                    response.getWriter().write(gson.toJson(requestedProduct));
                }
            }
            catch (NumberFormatException e){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }
        else{
            request.setAttribute("products", productsList);
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        }
    }
}
