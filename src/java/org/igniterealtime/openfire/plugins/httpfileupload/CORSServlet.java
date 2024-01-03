package org.igniterealtime.openfire.plugins.httpfileupload;

import nl.goodbytes.xmpp.xep0363.Servlet;
import org.jivesoftware.openfire.http.HttpBindManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSServlet extends Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpBindManager boshManager = HttpBindManager.getInstance();

        // Add CORS headers for all HTTP responses (errors, etc.)
        if (HttpBindManager.HTTP_BIND_CORS_ENABLED.getValue()) {
            String origin = request.getHeader("Origin");

            if (boshManager.isAllOriginsAllowed()) {
                response.setHeader("Access-Control-Allow-Origin", "*");
            } else if (boshManager.isThisOriginAllowed(origin)) {
                response.setHeader("Access-Control-Allow-Origin", origin);
            }

            response.setHeader("Access-Control-Allow-Methods", String.join(", ", HttpBindManager.HTTP_BIND_CORS_ALLOW_METHODS.getDefaultValue()));
            response.setHeader("Access-Control-Allow-Headers", String.join(", ", HttpBindManager.HTTP_BIND_CORS_ALLOW_HEADERS.getDefaultValue()));
            response.setHeader("Access-Control-Max-Age", String.valueOf(HttpBindManager.HTTP_BIND_CORS_MAX_AGE.getDefaultValue().getSeconds()));
        }

        super.service(request, response);
    }
}
