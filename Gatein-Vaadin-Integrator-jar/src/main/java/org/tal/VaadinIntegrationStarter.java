package org.tal;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.pc.ExoKernelIntegration;
import org.gatein.pc.api.PortletInvoker;
import org.gatein.pc.portlet.PortletInvokerInterceptor;
import org.gatein.pc.portlet.container.ContainerPortletInvoker;
import org.picocontainer.Startable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaadinIntegrationStarter implements Startable {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinIntegrationStarter.class);
    private ExoContainerContext context;
    private ExoKernelIntegration pc;

    public VaadinIntegrationStarter(ExoContainerContext context, ExoKernelIntegration pc) throws Exception {
        this.context = context;
        this.pc = pc;
    }

    @Override
    public void start() {
        LOG.info("VaadinIntegrationStarter start");

        PortletInvokerInterceptor portletInvokerInterceptor = (PortletInvokerInterceptor) ExoContainerContext.getCurrentContainer().getComponentInstance(ContainerPortletInvoker.class);

        LOG.info("Return lookup " + portletInvokerInterceptor.getClass().getName());

        PortletInvoker previous = portletInvokerInterceptor.getNext();

        LOG.info(" Previous " + previous.getClass().getName());

        PortletInvokerInterceptor vaadinParamInterceptor = new VaadinContextInterceptor();

        portletInvokerInterceptor.setNext(vaadinParamInterceptor);

        vaadinParamInterceptor.setNext(previous);

    }

    @Override
    public void stop() {
    }
}
