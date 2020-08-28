package org.Mercury.sensititive_filter;

import junit.framework.TestCase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensitiveFilterTest extends TestCase {
	
	public void test() throws Exception{
		
		// 使用默认单例（加载默认词典）
		SensitiveFilter filter = SensitiveFilter.DEFAULT;
		// 向过滤器增加一个词
		filter.put("婚礼上唱春天在哪里");
		
		// 待过滤的句子
		String sentence = "然后，市长在婚礼上唱春天在哪里。";
		// 进行过滤
/*		String filted = filter.filter(sentence, '*');
		
		// 如果未过滤，则返回输入的String引用
		if(sentence != filted){
			// 句子中有敏感词
			System.out.println(filted);
		}*/
		
	}
	
	public void testLogic(){
		
		SensitiveFilter filter = new SensitiveFilter();
		
		filter.put("你好");
		filter.put("你好1");
		filter.put("你好2");
		filter.put("你好3");
		filter.put("你好4");
		
		System.out.println(filter.filter("你好3天不见", '*'));
		
	}
	
	public void testSpeed() throws Exception{

		File file = new File("E:\\workspace\\Detection_Sensitive_Word\\src\\main\\resources\\反动词库.txt");

		List<String> testSuit = new ArrayList<String>(1048576);
		long length = 0;


				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb18030"));
				for(String line = br.readLine(); line != null; line = br.readLine()){
					if(line.trim().length() > 0){
						testSuit.add(line);
						length += line.length();
					}
				}
				br.close();
		
		System.out.println(String.format("待过滤文本共 %d 行，%d 字符。", testSuit.size(), length));
		String s = "";
		
		SensitiveFilter filter = SensitiveFilter.DEFAULT;
		
		int replaced = 0;

		filter.put("习大");
		filter.put("习大大");
		filter.put("大大很伟大使我们要尊敬的人");
		filter.put("大很伟大使我们要尊敬的人");
		filter.put("很伟大使我们要尊敬的人");
		filter.put("伟大使我们要尊敬的人");
		filter.put("大使我们要尊敬的人");
		filter.put("我们要尊敬的人");
		filter.put("们要尊敬的人");
		filter.put("要尊敬的人");
		filter.put("习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持");

		filter.put("大大");
		filter.put("伟大");
		filter.put("大很伟");
		long timer = System.currentTimeMillis();
		for (int t = 0; t < 5000; t++) {
			s += "习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持";
//			String x = filter.filter("fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************fasjisbuih习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持*********************************", '*');
//			System.out.println(x);
		}
		String x = filter.filter(s, '*');

		/*filter.put("习大大");
		filter.put("习大");
		filter.put("习大大很强大");
		String s = filter.filter("习大习大大很强大佛挡杀佛", '*');
		System.out.println(s);*/

		/*for(String line: testSuit){
			filter.filter(line, '*');
		}*/
		timer = System.currentTimeMillis() - timer;
		System.out.println("DFA-filter the text and repalce it:" + timer + " ms");
//		System.out.println(String.format("过滤耗时 %1.3f 秒， 速度为 %1.1f字符/毫秒", timer * 1E-3, length / (double) timer));
		//习大 mix:1314937127 hash:644807
		//
	}

	public void testSize() throws Exception {
		PrintStream ps = new PrintStream("D:\\test\\replace.txt");

		for (int t = 0; t < 200000; t++) {
			String s = "习大大很伟大使我们要尊敬的人支持习大大很伟大使我们要尊敬的人支持";
			ps.println(s);
//			System.out.println(x);
		}
		ps.close();
	}
}