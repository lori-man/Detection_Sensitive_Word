package org.Mercury.words;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompareTest {

    @Test
    public void textTest() throws Exception{
        File file = new File("E:\\workspace\\Detection_Sensitive_Word\\src\\main\\resources\\反动词库.txt");

        List<String> testSuit = new ArrayList<String>();
        long length = 0;

        testSuit.add("习大");
        testSuit.add("习大大");
        testSuit.add("大大很伟大使我们要尊敬的人");
        testSuit.add("大很伟大使我们要尊敬的人");
        testSuit.add("很伟大使我们要尊敬的人");
        testSuit.add("伟大使我们要尊敬的人");
        testSuit.add("大使我们要尊敬的人");
        testSuit.add("我们要尊敬的人");
        testSuit.add("们要尊敬的人");
        testSuit.add("要尊敬的人");

        testSuit.add("大大");
        testSuit.add("伟大");
        testSuit.add("大很伟");

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
        String s;
        while ((s = br.readLine()) != null) {
            testSuit.add(s);
            length++;
        }
        testSuit.add("sb");
        long time = System.currentTimeMillis();
        StringSearch iwords = new StringSearch();
        iwords.SetKeywords(testSuit);
        time = System.currentTimeMillis() - time;
        System.out.println("init sensitive: " + time + " ms");

        time = System.nanoTime() - time;
        for (int i = 0; i < 100000; i++) {
            String filter = iwords.Replace("fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************", '*');
//            System.out.println(filter);
        }
        time = System.nanoTime() - time;
        System.out.println("filter sensitive: " + time / 1000000 + " ms");
        br.close();
    }

    @Test
    public void wordSearchtextTest() throws Exception{
        File file = new File("E:\\workspace\\Detection_Sensitive_Word\\src\\main\\resources\\反动词库.txt");

        List<String> testSuit = new ArrayList<String>();
        long length = 0;

        testSuit.add("习大");
        testSuit.add("习大大");
        testSuit.add("大大很伟大使我们要尊敬的人");
        testSuit.add("大很伟大使我们要尊敬的人");
        testSuit.add("很伟大使我们要尊敬的人");
        testSuit.add("伟大使我们要尊敬的人");
        testSuit.add("大使我们要尊敬的人");
        testSuit.add("我们要尊敬的人");
        testSuit.add("们要尊敬的人");
        testSuit.add("要尊敬的人");

        testSuit.add("大大");
        testSuit.add("伟大");
        testSuit.add("大很伟");

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
        String s;
        while ((s = br.readLine()) != null) {
            testSuit.add(s);
            length++;
        }
        long time = System.currentTimeMillis();
        WordsSearch iwords = new WordsSearch();
        iwords.SetKeywords(testSuit);
        time = System.currentTimeMillis() - time;
        System.out.println("init sensitive: " + time + " ms");

        time = System.currentTimeMillis() - time;
        for (int i = 0; i < 20000; i++) {
            s += "习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持";
//            String filter = iwords.Replace("fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************");
//            System.out.println(filter);
        }
        String x = iwords.Replace(s, '*');
        time = System.currentTimeMillis() - time;
        System.out.println("AC revision-filter the text and repalce it:" + time + " ms");
        br.close();
    }
}
