package org.Mercury.ac;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Keyword implements Serializable{

    /**
     *
     */


    private Integer id;
    private Map<Integer, Integer> categoryTypeMap;
    private String word;
    private List<Integer> categories;


    private static final long serialVersionUID = 1L;

    public Keyword(){
        id = null;
        categories=null;
        categoryTypeMap=null;
        word=null;
    }

    public Keyword(String key){
        id = null;
        categories=null;
        categoryTypeMap=null;
        word=key;
    }



    public Keyword(Keyword p){
        this.categories=p.categories;
        this.categoryTypeMap=p.categoryTypeMap;
        this.id=p.id;
        this.word=p.word;
    }


    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub

        if (this == o) return true;
        if(o==null||getClass()!=o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        if(id!=null?!id.equals(keyword.id):keyword.id!=null)
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return id != null ?id.hashCode():0;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Map<Integer, Integer> getCategoryTypeMap() {
        return categoryTypeMap;
    }
    public void setCategoryTypeMap(Map<Integer, Integer> categoryTypeMap) {
        this.categoryTypeMap = categoryTypeMap;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public List<Integer> getCategories() {
        return categories;
    }
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return word;
    }
}
