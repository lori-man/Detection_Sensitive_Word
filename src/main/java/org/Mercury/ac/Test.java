package org.Mercury.ac;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String []args) throws Exception{


        List<Keyword> keywords = new ArrayList<Keyword>();
        List<Keyword> result = new ArrayList<Keyword> ();

        keywords.add(new Keyword("习大"));
        keywords.add(new Keyword("习大大"));
        keywords.add(new Keyword("大大很伟大使我们要尊敬的人"));
        keywords.add(new Keyword("大很伟大使我们要尊敬的人"));
        keywords.add(new Keyword("很伟大使我们要尊敬的人"));
        keywords.add(new Keyword("伟大使我们要尊敬的人"));
        keywords.add(new Keyword("大使我们要尊敬的人"));
        keywords.add(new Keyword("我们要尊敬的人"));
        keywords.add(new Keyword("们要尊敬的人"));
        keywords.add(new Keyword("要尊敬的人"));
//        sensitiveWords.add("习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持");


        keywords.add(new Keyword("大大"));
        keywords.add(new Keyword("伟大"));
        keywords.add(new Keyword("大很伟"));

        BufferedReader reader = new BufferedReader(new
                InputStreamReader(new FileInputStream("E:\\workspace\\Detection_Sensitive_Word\\src\\main\\resources\\反动词库.txt")));
        Long time = System.currentTimeMillis();
        String s = "";
        int length = 0;
        while ((s = reader.readLine() )!= null) {
            length++;
            keywords.add(new Keyword(s));
        }
        time = System.currentTimeMillis() - time;
        System.out.println("AC-Initializes the sensitive thesaurus:" + time + " ms");

        time = System.currentTimeMillis() - time;
        Patterns patterns=new Patterns(keywords);
        for (int i = 0; i < 20000; i++) {
//            result = patterns.searchKeyword("fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************", null);
//            result=patterns.searchKeyword("fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持", null);
//            System.out.println(result);
            s += "习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持";
        }
        String x = patterns.filter(s, null);
        time = System.currentTimeMillis() - time;
        System.out.println("AC-filter the text and repalce it:" + time + " ms");
        reader.close();
    }

}
