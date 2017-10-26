package by.tc.task02.dao;

import by.tc.task02.dao.impl.XMLdaoImpl;

public final class DAOFactory{
    private static final DAOFactory instance = new DAOFactory();

    private final XMLdao xmlDAO = new XMLdaoImpl();

    private DAOFactory() {}

    public XMLdao getXmlDAO() {
        return xmlDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}