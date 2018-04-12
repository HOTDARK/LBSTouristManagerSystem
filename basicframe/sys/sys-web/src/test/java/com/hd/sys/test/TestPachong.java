package com.hd.sys.test;
/*package com.hd.test;



import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

*//**
 * 
 * @author CallMeWhy
 * 
 *//*
public class TestPachong {
	private static HttpClient httpClient = HttpClients.createDefault();

	*//**
	 * @param path
	 *            目标网页的链接
	 * @return 返回布尔值，表示是否正常下载目标页面
	 * @throws Exception
	 *             读取网页流或写入本地文件流的IO异常
	 *//*
	public static boolean downloadPage(String path) throws Exception {
		// 得到 post 方法
		HttpGet getme = new HttpGet(path);
		// 执行，返回状态码
		HttpResponse statusCode = httpClient.execute(getme);
		// 针对状态码进行处理
		// 简单起见，只处理返回值为 200 的状态码
		if (statusCode.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = statusCode.getEntity();
			InputStream input = entity.getContent();
			OutputStream output = null;
			// 获得文件输出流
			output = new FileOutputStream("test.html");
			// 输出到文件
			int tempByte = -1;
			while ((tempByte = input.read()) > 0) {
				output.write(tempByte);
			}
			// 关闭输入流
			if (input != null) {
				input.close();
			}
			// 关闭输出流
			if (output != null) {
				output.close();
			}
			System.out.println("抓取完毕");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		try {
			// 抓取百度首页，输出
			TestPachong.downloadPage("https://www.baidu.com/s?wd=java&rsv_spt=1&rsv_iqid=0x9f2e9173000099ab&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=5&rsv_sug1=1&rsv_sug2=0&inputT=1135&rsv_sug4=1968");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/