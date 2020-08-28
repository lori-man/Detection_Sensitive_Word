package org.Mercury.words;

public class WordsSearchResult {

    public WordsSearchResult(String keyword, int start, int end, int index) {
        Keyword = keyword;
        End = end;
        Start = start;
        Index = index;
        MatchKeyword = keyword;
    }

    public WordsSearchResult(String keyword, int start, int end, int index, String matchKeyword) {
        Keyword = keyword;
        End = end;
        Start = start;
        Index = index;
        MatchKeyword = matchKeyword;
    }

    /** 开始位置 */
    public int Start;
    /** 结束位置 */
    public int End;
    /** 关键字 */
    public String Keyword;
    /** 索引 */
    public int Index;
    /** 匹配关键字 */
    public String MatchKeyword;

}