<%-- 
    Document   : adddate
    Created on : Aug 22, 2022, 10:57:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AddWorkingDate" method="POST">
            Employee ID <input type="text" name="eid" value="${requestScope.eid}" readonly><br>
            Checkin <input type="date" name="checkin" ><br>
            Checkout <input type="date" name="checkout"><br>
            <input type="submit" value="ADD">
        </form>
    </body>
</html>
