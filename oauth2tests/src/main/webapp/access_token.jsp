<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OAuth 2.0 Authorization Code Grant Test Application</title>
</head>
<body>

<%
    String access_token = String.valueOf(session.getAttribute("access_token"));

    //Clearn the access token value from session
    session.setAttribute("access_token", "");
%>

<table align="center">
    <tr>
        <td><h2>Access Token :</h2></td>
        <td><h2><div id="access_token"><%=access_token%></div></h2></td>
    </tr>
</table>

</body>
</html>
