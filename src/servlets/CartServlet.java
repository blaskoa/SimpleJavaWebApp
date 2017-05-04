package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import models.Cart;
import models.CombinedData;
import models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Adam Blasko on 03.05.2017.
 */
@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    private Cart cart;

    @Override
    public void init() throws ServletException {
        super.init();
        cart = new Cart();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            BufferedReader reader = new BufferedReader(request.getReader());
            String data = reader.lines().collect(Collectors.joining());

            CombinedData combinedData = new Gson().fromJson(data, CombinedData.class);
            List<Product> products = combinedData.getProducts();
            List<Integer> quantities = combinedData.getQuantities();
            if (products.size() != quantities.size()){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            else{
                for (int i = 0; i < products.size(); i++){
                    cart.changeProductQuantity(products.get(i), quantities.get(i));
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(data);
            }
        }
        catch (JsonSyntaxException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sum", cart.getProductsPriceSum());
        request.setAttribute("quantitySum", cart.getQuantitySum());
        request.setAttribute("productsWithQuantities", cart.getProductsWithQuantities());
        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
    }
}
