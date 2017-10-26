package by.tc.task02.dao.impl;

import by.tc.task02.dao.XMLdao;
import by.tc.task02.entity.Node;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class XMLdaoImpl implements XMLdao {
    private Map tagLevel = new HashMap<String, Integer>();
    public static final String openTagSymbol = "<";
    public static final String closeTagSymbol = ">";
    public static final String spaceRegex = " ";
    public static final char closeTagFlag = '/';
    public static final char ignorSymbolTag ='?';
    public static final int indexBginningOfLine = 0;
    public static final int offsetInTag =1;
    public static final int offsetInCloseTag =2;
    public static final int indexOfTagName=0;

    public Node parseXML(){

        int startSearch = indexBginningOfLine;
        int endSearch = indexBginningOfLine;
        int startOpenTag, startCloseTag;
        int endOpenTag, endCloseTag;
        String openTag;
        String closeTag;

        Stack<String> tags = new Stack<String>();
        Stack<Node> nodes=new Stack<Node>();

        Node node= new Node();
        String path = "./src/main/resources/task02.xml";
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                //declaration of local variables
                String line = scanner.nextLine();
                line=line.trim();

                startSearch=indexBginningOfLine;
                endSearch=indexBginningOfLine;
                while (endSearch != line.length()) {
                    //Search opening symbol
                    if (line.contains(openTagSymbol)) {
                        startSearch = line.indexOf(openTagSymbol, startSearch);
                        // check for a "?"
                        if (line.charAt(startSearch + offsetInTag) == ignorSymbolTag) {
                            //tag with "?"    search the next tag
                            startSearch = line.indexOf(closeTagSymbol, startSearch);
                            endSearch=startSearch+offsetInTag;
                        }

                        else{
                            //check the tag is closing or opening
                            if (line.charAt(startSearch + offsetInTag) != closeTagFlag) {
                                // tag is opening
                                startOpenTag = startSearch;
                                endOpenTag = line.indexOf(closeTagSymbol, startOpenTag);
                                openTag = line.substring(startSearch + offsetInTag, endOpenTag);
                                String tagName = getTagName(openTag);
                                this.checkContainsOfTagLevel(tagName);
                                tags.push(openTag);

                                startSearch = endOpenTag;
                                endSearch = endOpenTag+offsetInTag;


                            }
                            else {
                                //tag is closing tag
                                startCloseTag = startSearch;
                                endCloseTag = line.indexOf(closeTagSymbol, startCloseTag);
                                closeTag = line.substring(startCloseTag + offsetInCloseTag, endCloseTag);
                                String stackLine = tags.peek();
                                if (stackLine.contains(closeTag)) {
                                    String tagForObj = tags.pop();
                                    if (startSearch != indexBginningOfLine) {
                                        String tagContent= getContent(line,startCloseTag);

                                        Node simpleNode= NodeCreator.createSimpleNode(tagForObj, tagContent);
                                        nodes.push(simpleNode);
                                    }
                                    else{
                                        //close tag at the stars of the string
                                        int tagLevelClose=this.getTagLevel(closeTag);
                                        List<Node> childNode= new ArrayList<Node>();
                                        while (!(nodes.isEmpty())&&(tagLevelClose<this.getTagLevel((nodes.peek()).getName()))){
                                            childNode.add(nodes.pop());
                                        }
                                        Node parentNode=NodeCreator.createParentNode(tagForObj,childNode);
                                        nodes.push(parentNode);
                                        node=parentNode;

                                    }
                                }
                                startSearch = endCloseTag;
                                endSearch = endCloseTag+offsetInTag;
                            }
                        }
                    }

                }

            }

        }
        catch (IOException e){
            System.out.println("file note found");
        }
        finally {
            scanner.close();
        }
        return node;

    }

    public Map getTagLevel() {
        return tagLevel;
    }

    public void setTagLevel(Map level) {
        this.tagLevel = level;
    }
    public Boolean containsTag(String key){
        if(tagLevel.containsKey(key)){
            return true;
        }
        else {
            return false;
        }
    }
    public int getTagLevel(String key){
        return (Integer)(tagLevel.get(key));
    }
    public void setTagLevel(String key, int value){
        tagLevel.put(key,value);
    }
    public  void checkContainsOfTagLevel(String tagName){
        if (tagLevel.containsKey(tagName)) {
        } else {
            setTagLevel(tagName, tagLevel.size() + offsetInTag);
        }

    }
    public static String getContent(String line, int startClose){
        int indePreviosTag = line.lastIndexOf(closeTagSymbol, startClose);
        String tagContent = line.substring(indePreviosTag + offsetInTag, startClose);
        return tagContent;

    }
    public static String getTagName(String openTag){
        String[] openTagContent = openTag.split(spaceRegex);
        String tagName = openTagContent[indexOfTagName];
        return tagName;
    }

}

