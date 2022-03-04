<%-- 
    Document   : register
    Created on : Feb 25, 2022, 11:40:15 PM
    Author     : Ronald Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List - Register Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <form action="ShoppingList" method="post">
            <label>Username:</label>
            <input type="text" name="username">
            <input type="hidden" name="action" value="register">
            <br>
            <input type="submit" name="register" value="Register">
        </form>
    </body>
</html>
