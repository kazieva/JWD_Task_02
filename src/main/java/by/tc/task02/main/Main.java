package by.tc.task02.main;

import by.tc.task02.entity.Node;
import by.tc.task02.service.ServiceFactory;
import by.tc.task02.service.XMLService;

public class Main {

    public static void main(String[] args){
        Node xmlTree;
        ServiceFactory factory = ServiceFactory.getInstance();
        XMLService service = factory.getXmlService();
        xmlTree = service.parseXML();

        PrintXMLTree.print(xmlTree);
    }
}


