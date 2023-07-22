package com.home.andmark.bookkeeping;

import com.home.andmark.bookkeeping.config.SpringConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    // services and data sources
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // controller, view resolver, handler mapping
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
