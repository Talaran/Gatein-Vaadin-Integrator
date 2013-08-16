package org.tal;


import javax.servlet.ServletContext;

public class ContextLocator {

    private static ContextLocator instance = new ContextLocator();
    private ServletContext servletContext;

    private ContextLocator() {
    }

    public static ContextLocator getInstance() {
        return instance;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}
