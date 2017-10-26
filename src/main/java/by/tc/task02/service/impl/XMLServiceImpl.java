package by.tc.task02.service.impl;
import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.XMLdao;
import by.tc.task02.entity.Node;
import by.tc.task02.service.XMLService;

public class XMLServiceImpl  implements XMLService {
    public Node parseXML() {
        DAOFactory factory = DAOFactory.getInstance();
        XMLdao xmlDAO = factory.getXmlDAO();
        Node xmlTree = xmlDAO.parseXML();
        return xmlTree;

    }
}
