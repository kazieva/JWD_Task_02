package by.tc.task02.service;

import by.tc.task02.service.impl.XMLServiceImpl;


public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final XMLService xmlService = new XMLServiceImpl();

    private ServiceFactory() {}

    public XMLService getXmlService() {

        return xmlService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}