<%--
  Created by IntelliJ IDEA.
  User: jangjeong-yun
  Date: 2023/04/02
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/docreate">
        <table class="formtable">
            <tr>
                <td class="label">Name:</td>
                <td><input class="control" type="text" name="name"> </td>
            </tr>
            <tr>
                <td class="label">Email:</td>
                <td><input class="control" type="text" name="email"> </td>
            </tr>
            <tr>
                <td class="label">Offer:</td>
                <td><textarea class="control" rows="10" cols="10" name="text"> </textarea></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="새 제안"> </td>
            </tr>
        </table>
    </form>
</body>
</html>
