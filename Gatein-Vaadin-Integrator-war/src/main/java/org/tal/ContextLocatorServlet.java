package org.tal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ContextLocatorServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinIntegrationStarter.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        LOG.info("Init context locator servlet at path " + config.getServletContext().getContextPath());
        ContextLocator.getInstance().setServletContext(config.getServletContext());
    }
}