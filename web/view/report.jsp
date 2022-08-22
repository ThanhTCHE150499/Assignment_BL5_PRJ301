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
            a{
                text-decoration: none;
            }

        </style>
    </head>
    <body>
        <h2 style="padding: 0;


            margin-left: -1045px;


            border: none;

            ">Công Ty TNHH T&T
        </h2>
        <div class="titleTimesheet"> 
            <h3 style="padding: 0;


                margin-left: -1120px;


                border: none;">MST: 2808251201</h3>
            <h3 style="padding: 0;
                margin-left: -815px;
                border: none;">Địa chỉ: Nguyen Co Thach Street, Nam Tu Liem, Hanoi </h3>
            <h1>BẢNG CHẤM CÔNG NHÂN VIÊN</h1>
            <h3>Tháng 08-2022</h3>
        </div>

        <table border="1px">

            <tr style ="background-color:  lightblue;">
                <td >STT</td>
                <td >Họ tên</td>
                <td>Chức vụ</td>
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
                <td>Tổng cộng</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${requestScope.emps}" var="e">
                <tr >
                    <td style="background-color: gray;" >${e.id}</td>
                    <td style="background-color: green;" >${e.name}</td>
                    <td style="background-color: cyan;">${e.role}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td 
                            <c:if test="${d.dow eq 1 or d.dow eq 7}">
                                style="background-color: lightcoral" 
                            </c:if>
                            >
                            <c:forEach items="${e.timesheets}" var="t">
                                <c:if test="${d.value eq t.cidate}">
                                    x
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                    <td>${e.getWorkingDays()}</td>

                    <td><a href="EditController?eid=${e.id}">Edit</a><br><a href="EmployeeController?eid=${e.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <h3 style="padding: 0;


                margin-left: 750px;


                border: none;">Ngày 31 tháng 8 năm 2022</h3>
            <h3 

                style="padding: 0;


                margin-left: 750px;


                border: none;">Giám đốc

            </h3>
            <h3 

                style="padding: 0;


                margin-left: 750px;


                border: none;">(Ký, họ tên)

            </h3>
        </div>
    </body>
</html>
