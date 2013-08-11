package org.tal;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.portal.pc.ExoKernelIntegration;
import org.gatein.pc.api.PortletInvoker;
import org.gatein.pc.portlet.PortletInvokerInterceptor;
import org.gatein.pc.portlet.container.ContainerPortletInvoker;
import org.picocontainer.Startable;

/**
 * User: sam
 * Date: 2/17/13
 * Time: 11:19 AM
 */
public class VaadinIntegrationStarter implements Startable {

    private ExoContainerContext context;
    private ExoKernelIntegration pc;

    public VaadinIntegrationStarter(ExoContainerContext context, ExoKernelIntegration pc) throws Exception {
        this.context = context;
        this.pc = pc;
    }


    @Override
    public void start() {
        System.out.println("VaadinIntegrationStarter start");

        PortletInvokerInterceptor portletInvokerInterceptor = (PortletInvokerInterceptor) ExoContainerContext.getCurrentContainer().getComponentInstance(ContainerPortletInvoker.class);

        System.out.println("Return lookup " + portletInvokerInterceptor.getClass().getName());

        PortletInvoker previous = portletInvokerInterceptor.getNext();

        System.out.println(" Previous " + previous.getClass().getName());

        PortletInvokerInterceptor vaadinParamInterceptor = new VaadinContextInterceptor("static path");

        portletInvokerInterceptor.setNext(vaadinParamInterceptor);

        vaadinParamInterceptor.setNext(previous);

    }

    @Override
    public void stop() {
    }
}
