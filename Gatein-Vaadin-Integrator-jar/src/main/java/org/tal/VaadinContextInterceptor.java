package org.tal;

import com.vaadin.server.Constants;
import org.gatein.pc.api.PortletInvokerException;
import org.gatein.pc.api.invocation.PortletInvocation;
import org.gatein.pc.api.invocation.response.PortletInvocationResponse;
import org.gatein.pc.api.spi.PortalContext;
import org.gatein.pc.portlet.PortletInvokerInterceptor;
import org.gatein.pc.portlet.impl.spi.AbstractPortalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VaadinContextInterceptor extends PortletInvokerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinIntegrationStarter.class);
    private static String vaadinStaticFilesPath;

    @Override
    public PortletInvocationResponse invoke(PortletInvocation invocation) throws IllegalArgumentException,
            PortletInvokerException {
        PortalContext previousContext = invocation.getPortalContext();

        Map<String, String> previousProperties = previousContext.getProperties();

        Map<String, String> newProperties = new HashMap<>(previousProperties);

        LOG.info("Intercept vaadin static files path key: '{}' -> value: '{}'", new Object[]{
                Constants.PORTAL_PARAMETER_VAADIN_RESOURCE_PATH, getVaadinStaticFilesLocation()});

        newProperties.put(Constants.PORTAL_PARAMETER_VAADIN_RESOURCE_PATH, getVaadinStaticFilesLocation());

        PortalContext newContext = new AbstractPortalContext(previousContext.getWindowStates(),
                previousContext.getModes(), Collections.unmodifiableMap(newProperties));


        invocation.setPortalContext(newContext);
        LOG.info("Call intercepted " + invocation.getClass().getName());
        return super.invoke(invocation);
    }

    private String getVaadinStaticFilesLocation() {
        if (vaadinStaticFilesPath == null) {
            vaadinStaticFilesPath = ContextLocator.getInstance().getServletContext().getContextPath() + "/resources";
        }
        return vaadinStaticFilesPath;
    }
}
