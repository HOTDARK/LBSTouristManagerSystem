package com.hd.sys.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hd.sys.test.entity.TestObj;

public class TestUnit extends BaseUnit {
	
//	private SysOrgService sysOrgService;
	
	@Before
	public void setup() {
//		sysOrgService = context.getBean(SysOrgServiceImpl.class);
	}
	
	@Test
	public void testDemo() throws Exception {
		String path="D:/data/ttttt.txt";
		InputStreamReader fr = null;
		BufferedReader br = null;
		fr = new InputStreamReader(new FileInputStream(path),"GBK");
		br = new BufferedReader(fr);
		String rec = null;// 一行
		int count=1;
		int countTemp=1;
		List<TestObj> lists = new ArrayList<TestObj>();
		try {
			// 读取一行
			while ((rec = br.readLine()) != null) {
				count++;
				TestObj obj=new TestObj();
				String[] split = rec.split(", ");
				if (split.length>1) {
					countTemp++;
					//System.out.println(Arrays.toString(split));
					String[] split0 = split[0].split("=");
					if (split0.length==1) {
						obj.setErrorcode("");
					}else{
						obj.setErrorcode(split0[1]);
					}
					
					String[] split1 = split[1].split("=");
					if (split1.length==1) {
						obj.setUsername("");
					}else{
						obj.setUsername(split1[1]);
					}
					
					String[] split2 = split[2].split("=");
					if (split2.length==1) {
						obj.setBandwith("");
					}else{
						obj.setBandwith(split2[1]);
					}
					
					String[] split3 = split[3].split("=");
					if (split3.length==1) {
						obj.setCityname("");
					}else{
						obj.setCityname(split3[1]);
					}
					
					String[] split4 = split[4].split("=");
					if (split4.length==1) {
						obj.setDownloadurl("");
					}else{
						obj.setDownloadurl(split4[1]);
					}
					lists.add(obj);
				}
		      }
			if (lists!=null&&lists.size()>0) {
//				sysOrgService.saveTest(lists);
				System.out.println("总条数："+count);
				System.out.println("有效条数："+countTemp);
				System.out.println("保存完成");
				lists.clear();
			}
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		} catch (Exception e) {
		    e.printStackTrace();
	}
 }
}
