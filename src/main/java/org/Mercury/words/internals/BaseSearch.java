package org.Mercury.words.internals;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *核心思想：与AC机制不同，本实现将关注点放置末尾，末尾代表结果
 */
public class BaseSearch {
    protected TrieNode2[] _first = new TrieNode2[Character.MAX_VALUE + 1];
    protected String[] _keywords;


    /**
     * 设置关键字
     * 
     * @param keywords 关键字列表
     */
    public void SetKeywords(List<String> keywords) {
        _keywords = new String[keywords.size()];
        _keywords = keywords.toArray(_keywords);
        SetKeywords();
    }

    protected void SetKeywords() {
        TrieNode root = new TrieNode();
        //层次遍历node，并记录在map集合中，key为层数，value为node集合
        Map<Integer, List<TrieNode>> allNodeLayers = new Hashtable<Integer, List<TrieNode>>();
        for (int i = 0; i < _keywords.length; i++) {
            String p = _keywords[i];
            TrieNode nd = root;
            for (int j = 0; j < p.length(); j++) {
                nd = nd.Add(p.charAt(j));  //下一个字符的TrieNode
                if (nd.Layer == 0) {
                    nd.Layer = j + 1;
                    if (allNodeLayers.containsKey(nd.Layer) == false) {
                        List<TrieNode> nodes = new ArrayList<TrieNode>();
                        nodes.add(nd);
                        allNodeLayers.put(nd.Layer, nodes);
                    } else {
                        allNodeLayers.get(nd.Layer).add(nd);
                    }
                }
            }
            nd.SetResults(i);//最后一个敏感字符储存其整条敏感词在数组的位置,方便直接读取数据
        }

        //node的所有字符集合，为后面的failure做准备
        List<TrieNode> allNode = new ArrayList<TrieNode>();
        allNode.add(root);
        for (int i = 0; i < allNodeLayers.size(); i++) { // 注意 这里不能用 keySet()
            List<TrieNode> nodes = allNodeLayers.get(i + 1);
            for (int j = 0; j < nodes.size(); j++) {
                allNode.add(nodes.get(j));
            }
        }
        allNodeLayers.clear();
        allNodeLayers = null;

        //failure指针初始化
        for (int i = 1; i < allNode.size(); i++) {
            TrieNode nd = allNode.get(i);
            nd.Index = i;
            TrieNode r = nd.Parent.Failure;
            Character c = nd.Char;
            while (r != null && !r.m_values.containsKey(c))
                r = r.Failure;
            if (r == null)
                nd.Failure = root;  //如果没有ac后缀,直接指向root
            else {
                nd.Failure = r.m_values.get(c);
                for (Integer result : nd.Failure.Results) {
                    nd.SetResults(result);   //failure所指的node就是nd的后缀,那么failure所指的node是否含有敏感词,那nd同样拥有
                }
            }
        }
        root.Failure = root;

        //移植到TrieNode2中
        List<TrieNode2> allNode2 = new ArrayList<TrieNode2>();
        for (int i = 0; i < allNode.size(); i++) {
            allNode2.add(new TrieNode2());
        }
        for (int i = 0; i < allNode2.size(); i++) {
            TrieNode oldNode = allNode.get(i);
            TrieNode2 newNode = allNode2.get(i);

            for (Character key : oldNode.m_values.keySet()) {
                TrieNode nd = oldNode.m_values.get(key);
                newNode.Add(key, allNode2.get(nd.Index));//java的指针封装，指向allNode2的node（代表下一个字符的node）
            }
            oldNode.Results.forEach(item -> {
                newNode.SetResults(item);
            });

            //failure对照
            oldNode = oldNode.Failure;
            while (oldNode != root) {   //为root代表结束
                for (Character key : oldNode.m_values.keySet()) {  //进行压缩，利用AC机制的failure，将node的后缀与failure所致的前缀合并，并更新result
                    TrieNode nd = oldNode.m_values.get(key);
                    if (newNode.HasKey(key) == false) {
                        newNode.Add(key, allNode2.get(nd.Index));
                    }
                }
                oldNode.Results.forEach(item -> {
                    newNode.SetResults(item);
                });
                oldNode = oldNode.Failure;
            }
        }
        allNode.clear();
        allNode = null;
        root = null;

        //将敏感词首字符提取出来，作为索引放入first数组
        TrieNode2[] first = new TrieNode2[Character.MAX_VALUE + 1];
        TrieNode2 root2 = allNode2.get(0);
        for (Character key : root2.m_values.keySet()) {
            TrieNode2 nd = root2.m_values.get(key);
            first[(int) key] = nd;
        }
        _first = first;
    }


}