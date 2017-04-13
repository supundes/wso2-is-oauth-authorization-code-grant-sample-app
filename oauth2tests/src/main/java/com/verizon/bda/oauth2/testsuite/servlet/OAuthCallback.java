package com.verizon.bda.oauth2.testsuite.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthCallback extends HttpServlet {

    private static Log logger = LogFactory.getLog(OAuthCallback.class);


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {

        String authorizationCode = request.getParameter("code");

        logger.info("Authorization Code : " + authorizationCode);

        //Set the authorization code value to the session
        request.getSession().setAttribute("authorization_code", authorizationCode);

        response.sendRedirect("token_request.jsp");

    }
}
