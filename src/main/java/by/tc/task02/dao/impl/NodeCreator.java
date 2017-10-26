package by.tc.task02.dao.impl;

import by.tc.task02.entity.Node;
import java.util.List;

public class NodeCreator {

    public static final String attributePathRegex = "=";
    public static final String spaceRegex = " ";
    public static final int indexOfTagName=0;
    public static final int indexOfFirstAttribute=1;
    public static final int keyIndex=0;
    public static final int valueIndex=1;
    public static final int startQuotesIndex=1;


    public static Node createNode(String openTag){
        Node simpleNode=new Node();
        String [] openTagContent = openTag.split(spaceRegex);
        simpleNode.setName(openTagContent[indexOfTagName]);
                //parse attributes
        for(int i =indexOfFirstAttribute;i<openTagContent.length;i++) {
            String[] attribute = openTagContent[i].split(attributePathRegex);
            simpleNode.setAttrinute(attribute[keyIndex], attribute[valueIndex].substring(startQuotesIndex, attribute[valueIndex].length() - 1));
        }
        return simpleNode;
    }

    public static Node createSimpleNode(String openTag, String content){
        Node simpleNode;
        simpleNode=createNode(openTag);
        simpleNode.setContent(content);
        return simpleNode;
    }
    public static Node createParentNode(String openTag, List<Node> child){
        Node parentNode;
        parentNode=createNode(openTag);
        parentNode.setChilds(child);
        return parentNode;
    }
}
