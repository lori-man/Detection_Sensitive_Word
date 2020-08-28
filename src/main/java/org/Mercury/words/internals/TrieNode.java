package org.Mercury.words.internals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode implements Comparable<TrieNode> {

    public int Index;   //在allNode的下标
    public int Layer;  //位于树的第几层
    public boolean End; //是否是末尾
    public char Char;
    public List<Integer> Results;  //敏感词数组的位置,为什么是list,因为failure所指的node可能为敏感词,
    // 那么this肯定包括敏感词,已最后字符作为敏感词的判断
    public HashMap<Character, TrieNode> m_values;
    public TrieNode Failure;
    public TrieNode Parent;
    public boolean IsWildcard;
    public int WildcardLayer;
    public boolean HasWildcard;


    public TrieNode() {
        m_values = new HashMap<Character, TrieNode>();
        Results = new ArrayList<Integer>();
    }

    public TrieNode Add(final Character c) {
        if (m_values.containsKey(c)) {
            return m_values.get(c);
        }
        final TrieNode node = new TrieNode();
        node.Parent = this;
        node.Char = c;
        m_values.put(c, node);
        return node;
    }

    public void SetResults(Integer index) {
        if (End == false) {
            End = true;
        }
        if (Results.contains(index) == false) {
            Results.add(index);
        }
    }

    @Override
    public int compareTo(TrieNode o) {
        return this.Layer - o.Layer  ;
    }
}
