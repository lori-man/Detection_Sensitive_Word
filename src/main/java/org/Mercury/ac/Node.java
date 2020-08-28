package org.Mercury.ac;


import java.util.ArrayList;
import java.util.List;

public class Node {
    int state;                    //自动机的状态，也就是节点数字
    char character = 0;           //指向当前节点的字符，也即条件
    Node failureNode;             //匹配失败时，下一个节点
    List<Keyword> keywords;       //匹配成功时，当前节点对应的关键词
    List<Node> childrenList;          //当前节点的子节点

    public Node(){
        keywords=new ArrayList<Keyword>();
        childrenList = new ArrayList<Node>();
        state = 0;
        failureNode = null;
        character = 0;
    }

    public Node (char c,Node node) {
        keywords=new ArrayList<Keyword>();
        childrenList = new ArrayList<Node>();
        state =1;
        character =c ;
        failureNode = node;
    }

    public Boolean containsChild (char c){
        for(Node childNode : childrenList) {
            if(childNode.character==c) return true;
        }
        return false;
    }

    public Node getChild (char c){
        for (Node childNode : childrenList){
            if(childNode.character==c) return childNode;
        }
        return null;
    }

    public void addKeyword(Keyword keyword){
        keywords.add(keyword);

    }

    public void addKeywords(List<Keyword> k){
        keywords.addAll(k);
    }

    public void addChild(Node child){
        childrenList.add(child);
    }

}