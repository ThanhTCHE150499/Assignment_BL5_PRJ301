<%-- 
    Document   : edit
    Created on : Aug 22, 2022, 10:30:32 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            a{
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <form action="EditController" method="POST">
            Mã nhân viên <input type="text" name="eid" value="${requestScope.employee.id}" readonly><br>
            Họ và tên <input type="text" name="ename" value="${requestScope.employee.name}"><br>
            Chức vụ <input type="text" name="erole" value="${requestScope.employee.role}"><br>
            <input type="submit" value="Cập nhật">
        </form><br>
            <button><a href="AddWorkingDate?eid=${requestScope.employee.id}" >Thêm ngày làm việc</a></button>
    </body>
</html>
