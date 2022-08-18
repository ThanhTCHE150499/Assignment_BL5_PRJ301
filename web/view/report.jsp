<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .titleTimesheet{
                justify-content: center;
                align-items: center;
                text-align: center;
            }
            table{
                text-align: center;
                justify-content: center;
                align-items: center;
                margin: 50px 100px;
            }
            body{

                text-align: center;
                justify-content: center;
                align-items: center;
            }

        </style>
    </head>
    <body>
        <h2 style="padding: 0;


            margin-left: -1025px;


            border: none;

            ">Công Ty TNHH T&T
        </h2>
        <div class="titleTimesheet"> 
            <h3 style="padding: 0;


                margin-left: -1070px;


                border: none;">Tax code: 2808251201</h3>
            <h3 style="padding: 0;
                margin-left: -1070px;
                border: none;">Address: </h3>
            <h1>TimeSheets</h1>
            <h3>August-2022</h3>
        </div>

        <table border="1px">
            
            <tr style ="background-color:  lightblue;">
            <tr>
                <td>Name</td>
                <td>Role</td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td
                        <c:if test="${d.dow eq 1 or d.dow eq 7}">
                            style="background-color: lightcoral" 
                        </c:if>
                        >
                        <fmt:formatDate pattern = "dd" 
                                        value = "${d.value}" /> <br/>
                        <fmt:formatDate pattern = "EEE" 
                                        value = "${d.value}" />
                    </td>
                </c:forEach>
                <td>Hours</td>
                <td>Days</td>
                <td>Salary</td>
            </tr>
            <c:forEach items="${requestScope.emps}" var="e">
                <tr>
                    <td style="background-color: green;">${e.name}</td>
                    <td style="background-color: cyan;">${e.role}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td 
                            <c:if test="${d.dow eq 1 or d.dow eq 7}">
                                style="background-color: lightcoral" 
                            </c:if>
                            >
                            <c:forEach items="${e.timesheets}" var="t">
                                <c:if test="${d.value eq t.cidate}">
                                    ${t.getWorkingHours()}
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <td>${e.getWorkingHours()}</td>
                    <td>${e.getWorkingDays()}</td>
                </tr>
            </c:forEach>
        </table>
                <h3 style="padding: 0;


            margin-left: 750px;


            border: none;">Ngày 31 tháng 8 năm 2022</h3>
        <h3 style="padding: 0;


            margin-left: 750px;


            border: none;">Giám đốc</h3>
    </body>
</html>
