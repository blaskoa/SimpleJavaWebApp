<%--
  Created by IntelliJ IDEA.
  User: Adam Blasko
  Date: 03.05.2017
  Time: 22:47
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
            <h1>Order</h1>
        </div>
    </div>
    <div class="container">
        <div class="modal fade" id="modal-id">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Products added successfully!</h4>
                    </div>
                    <div class="modal-body">
                        Products added to cart. Continue to checkout?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Not now</button>
                        <a type="button" class="btn btn-primary" href="CartServlet">Continue to checkout</a>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td class="col-md-6">${product.name}</td>
                    <td class="col-md-4">${product.price}</td>

                    <td class="col-md-2 text-center">
                        <div id="id${product.identificator}" class="input-group">
                            <input class="form-control" type="number" style="text-align: center;"/>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="button" class="btn btn-success" onclick="handleClick()">Add products to cart</button>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>

<script type="text/javascript">

    function handleClick(){
        var sentCounter = 0;
        var successCounter = 0;
        var productsToOrder = [];
        var quantitiesToOrder = [];
        $(".input-group").each(function (){

            var quantity = this.firstElementChild.value;
            if (quantity !== null && quantity > 0){
                sentCounter++;
                $.ajax({
                    type: "get",
                    url: "ProductsServlet?id=" + this.id.substring(2),
                    success: function(product) {
                        productsToOrder.push(product);
                        var test = $("#id" + product.identificator).get(0);
                        quantitiesToOrder.push(test.firstElementChild.value);
                        successCounter++;
                        if (successCounter === sentCounter){
                            orderProducts(productsToOrder, quantitiesToOrder);
                        }
                    }
                });
            }
        });
    }

    function orderProducts(products, quantities) {
        var combinedData = {
            products: products,
            quantities: quantities
        };
        var json = JSON.stringify(combinedData);
        $.ajax({
            type: "post",
            url: "CartServlet",
            dataType: "json",
            data: json,
            success: function(msg) {
                showSuccessModal();
            }
        });
    }

    function showSuccessModal() {
        $('#modal-id').modal('show');

    }

</script>