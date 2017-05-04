<%--
  Created by IntelliJ IDEA.
  User: Adam Blasko
  Date: 04.05.2017
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Products</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="jumbotron">
        <div class="container">
            <h1>Checkout</h1>
        </div>
    </div>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productsWithQuantities}" var="qProduct">
                <tr>
                    <td class="col-md-6">${qProduct.product.name}</td>
                    <td class="col-md-4">${qProduct.product.price}</td>
                    <td class="col-md-2 text-center">${qProduct.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h1>Total quantity: ${quantitySum}</h1>
        <h1>Total Sum: ${sum}</h1>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>

<script type="text/javascript">
    function handleClick()
    {
        $.ajax({
            type: "get",
            url: "jms?text=" + $('#inputMessage').val(),
            success: function(msg) {
                alert(msg);
            }
        });
    }
</script>
