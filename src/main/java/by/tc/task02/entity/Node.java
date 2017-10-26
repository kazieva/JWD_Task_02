package by.tc.task02.entity;

import java.util.*;

public class Node {
    private String name;
    private String content;
    private Map attribute= new HashMap();
    private List<Node> childs= new ArrayList<Node>();

    public Node() {
        this.name = "";
        this.content = "";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map getAttribute() {
        return attribute;
    }

    public Object getAttributeValue(String key){
        return attribute.get(key);
    }

    public void setAttribute(Map attribute) {
        this.attribute = attribute;
    }

    public  void setAttrinute(String key, String value){
        attribute.put(key,value);
    }

    public List<Node> getChilds() {
        return childs;
    }

    public void setChilds(List<Node> childs) {
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (!name.equals(node.name)) return false;
        if (content != null ? !content.equals(node.content) : node.content != null) return false;
        if (attribute != null ? !attribute.equals(node.attribute) : node.attribute != null) return false;
        return childs != null ? childs.equals(node.childs) : node.childs == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        result = 31 * result + (childs != null ? childs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return attributeToString() + " " + childToString() + content +'\n';
    }
    public String attributeToString(){

        String attributes="";
        if(attribute.isEmpty()){
            return "";
        }
        else {
            Set<Map.Entry<String, String >> setAttribute = attribute.entrySet();
            for (Map.Entry<String, String> nodeAttribute : setAttribute) {
                 attributes+=nodeAttribute.getValue();
            }
            return attributes;
        }
    }
    public String childToString(){

        String child="";
        if(childs.isEmpty()){}
        else {
            for (Node node : childs) {
               child+= node.toString();
            }
            return child;
        }
        return child;
    }
}
