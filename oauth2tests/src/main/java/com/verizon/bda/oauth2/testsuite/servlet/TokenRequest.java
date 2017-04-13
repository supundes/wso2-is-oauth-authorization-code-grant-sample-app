package com.verizon.bda.oauth2.testsuite.servlet;

import org.apache.amber.oauth2.client.OAuthClient;
import org.apache.amber.oauth2.client.URLConnectionClient;
import org.apache.amber.oauth2.client.request.OAuthClientRequest;
import org.apache.amber.oauth2.client.response.OAuthClientResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.types.GrantType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenRequest extends HttpServlet {

    private static Log logger = LogFactory.getLog(TokenRequest.class);


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,
            IOException {

        String authorization_code = String.valueOf(request.getParameter("authorization_code"));
        String token_endpoint = String.valueOf(request.getParameter("token_endpoint"));
        String redirect_uri = String.valueOf(request.getParameter("redirect_uri"));
        String client_id = String.valueOf(request.getParameter("client_id"));
        String client_secret = String.valueOf(request.getParameter("client_secret"));

        //Clear the authorization code value from session
        request.getSession().setAttribute("authorization_code", "");

        OAuthClientRequest accessRequest = null;
        try {
            accessRequest = OAuthClientRequest.tokenLocation(token_endpoint)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(client_id)
                    .setClientSecret(client_secret)
                    .setRedirectURI(redirect_uri)
                    .setCode(authorization_code)
                    .buildBodyMessage();


            //create OAuth client that uses custom http client under the hood
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientResponse oAuthResponse = oAuthClient.accessToken(accessRequest);
            String access_token = oAuthResponse.getParam("access_token");

            logger.info("Access token : " + access_token);

            //Set the access token value to the session
            request.getSession().setAttribute("access_token", access_token);

            response.sendRedirect("access_token.jsp");

        } catch (OAuthSystemException e) {
            e.printStackTrace();
        } catch (OAuthProblemException e) {
            e.printStackTrace();
        }

    }
}
