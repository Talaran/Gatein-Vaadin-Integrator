package org.tal;

import org.gatein.pc.api.PortletInvokerException;
import org.gatein.pc.api.invocation.PortletInvocation;
import org.gatein.pc.api.invocation.response.PortletInvocationResponse;
import org.gatein.pc.api.spi.PortalContext;
import org.gatein.pc.portlet.PortletInvokerInterceptor;
import org.gatein.pc.portlet.impl.spi.AbstractPortalContext;

import java.util.HashMap;

public class VaadinContextInterceptor extends PortletInvokerInterceptor {

    private static final String PORTAL_PARAMETER_VAADIN_WIDGETSET = "vaadin.widgetset";
    private static final String PORTAL_PARAMETER_VAADIN_RESOURCE_PATH = "vaadin.resources.path";
    private static final String PORTAL_PARAMETER_VAADIN_THEME = "vaadin.theme";

    private String vaadinContext;

    @Override
    public PortletInvocationResponse invoke(PortletInvocation invocation) throws IllegalArgumentException,
            PortletInvokerException {
        PortalContext  newContext = new AbstractPortalContext(new HashMap<String, String>());
        invocation.setPortalContext(null);
        return super.invoke(invocation);
    }

    public VaadinContextInterceptor(String vaadinContext) {
        this.vaadinContext = vaadinContext;
    }

    public String getVaadinContext() {
        return vaadinContext;
    }

    public void setVaadinContext(String vaadinContext) {
        this.vaadinContext = vaadinContext;
    }
}
