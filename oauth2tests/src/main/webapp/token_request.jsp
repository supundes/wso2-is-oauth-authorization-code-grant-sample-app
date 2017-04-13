<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>OAuth 2.0 Authorization Code Grant Test Application</title>
</head>
<body>

<%String authorization_code = String.valueOf(session.getAttribute("authorization_code"));%>

<table align="center">
    <tr>
        <td><h2>Request for Access Token</h2></td>
    </tr>
</table>

<form action="tokenrequest" method="POST">

    <table align="center">
        <tr>
            <td>Authorization Code</td>
            <td><input type="text" id="authorization_code" name="authorization_code" value="<%=authorization_code%>"
                       size="100" readonly/></td>
        </tr>
        <tr>
            <td>Token Endpoint</td>
            <td><input type="text" id="token_endpoint" name="token_endpoint" value="" size="100" required/></td>
        </tr>
        <tr>
            <td>Redirect URI</td>
            <td><input type="text" id="redirect_uri" name="redirect_uri" value="" size="100" required/></td>
        </tr>
        <tr>
            <td>Client ID</td>
            <td><input type="text" id="client_id" name="client_id" value="" size="100" required/></td>
        </tr>
        <tr>
            <td>Client Secret</td>
            <td><input type="password" id="client_secret" name="client_secret" value="" size="100" autocomplete="off"
                       required/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Retrieve Access Token"/>
            </td>
        </tr>


    </table>
</form>

</body>
</html>
