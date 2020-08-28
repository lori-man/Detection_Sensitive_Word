package org.Mercury.ac;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Patterns {
    private final Node root = new Node();


    private List<Node> tree;

    public Patterns(List<Keyword> keywords){
        tree = new ArrayList<Node> ();
        root.failureNode=root;
        tree.add(root);
        for(Keyword keyword : keywords){
            addKeyword(keyword);
        }
        setFailNode();
    }

    private  void setFailNode() {
        // TODO Auto-generated method stub

        Queue<Node> queue = new LinkedList<Node>();
        Node node =root;
        for (Node d1 : node.childrenList){
            queue.offer(d1);
        }
        while (!queue.isEmpty()){
            node = queue.poll();
            if (node.childrenList!=null){
                for (Node curNode : node.childrenList) {
                    queue.offer(curNode);
                    Node failNode = node.failureNode;
                    while(!failNode.containsChild(curNode.character)){
                        failNode = failNode.failureNode;
                        if(failNode==null||failNode.state==0) break;
                    }
                    if(failNode!=null&&failNode.containsChild(curNode.character)) {
                        curNode.failureNode = failNode.getChild(curNode.character);
                        curNode.addKeywords(curNode.failureNode.keywords);

                    }

                }
            }
        }
    }

    private  void addKeyword(Keyword keyword) {
        // TODO Auto-generated method stub

        char [] wordCharArr = keyword.getWord().toCharArray();
        Node current = root;
        for(char currentChar : wordCharArr){
            if(current.containsChild(currentChar)){
                current = current.getChild(currentChar);
            }
            else{
                Node node = new Node (currentChar,root);
                current.addChild(node);
                current=node;
                tree.add(node);
            }
        }
        current.addKeyword(keyword);

    }

    public List<Keyword> searchKeyword(String data,Integer category) {
        List<Keyword> matchResult = new ArrayList<Keyword>();
        Node node = root;
        char[] chs = data.toCharArray();
        for (int i=0;i<chs.length;i++){
            while(node!=null&&!node.containsChild(chs[i])){
                //	if(node.state==0) break;
                node = node.failureNode;
                if (node == null || node.state == 0) {
                    break;
                }
            }

            if (node != null && node.containsChild(chs[i])) {
                node = node.getChild(chs[i]);
                if (node.keywords != null) {
                    for (Keyword pattern : node.keywords) {
                        if (category == null) {
                            //						System.out.println(pattern.getWord());
                            matchResult.add(new Keyword(pattern.getWord()));
                        } else {
                            if (pattern.getCategories().contains(category)) {
                                matchResult.add(pattern);
                            }
                        }
                    }
                }
            }
        }
        return matchResult;
    }

    public String filter(String data,Integer category) {
        Node node = root;
        char[] chs = data.toCharArray();
        for (int i=0;i<chs.length;i++){
            while(node!=null&&!node.containsChild(chs[i])){
                //	if(node.state==0) break;
                node = node.failureNode;
                if (node == null || node.state == 0) {
                    break;
                }
            }

            if (node != null && node.containsChild(chs[i])) {
                node = node.getChild(chs[i]);
                if (node.keywords != null) {
                    for (Keyword pattern : node.keywords) {
                        if (category == null) {
                            //						System.out.println(pattern.getWord());
//                            getReplaceChars(data,i-pattern.getWord().length(),i)
                            for (int j = (i - pattern.getWord().length()); j <= i; j++) {
                                chs[j] = '*';
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * 获取替换字符串
     */
    private static String getReplaceChars(char replaceChar, int length) {
        StringBuilder resultReplace = new StringBuilder(String.valueOf(replaceChar));
        for (int i = 1; i < length; i++) {
            resultReplace.append(replaceChar);
        }
        return resultReplace.toString();
    }
}
