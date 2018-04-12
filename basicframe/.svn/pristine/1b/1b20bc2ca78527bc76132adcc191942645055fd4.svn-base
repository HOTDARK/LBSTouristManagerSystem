package com.hd.sys.test;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestPachong2 {
	public static void main(String[] args) throws IOException {
		Document docs = Jsoup
//				.connect("http://www.oschina.net/news/68980/upupw-k-1-8")
				.connect("http://news.baidu.com")
				.header("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
				.get();
//		Elements els = docs.getElementsByTag("P");
//		StringBuffer sf = new StringBuffer();
//		for (Element el : els) {
//			String xx = el.html().replace("<br>", "\\n");
//			String vals = el.html(xx).text();
//			sf.append(vals);
//		}
//		System.out.println(sf.toString());
		Elements els = docs.getElementsByAttributeValue("target", "_blank");
		for (Element element : els) {
			Elements tags = element.getElementsByTag("a");
			for (Element el2 : tags) {
				String txt = el2.text();
				if (!StringUtil.isBlank(txt)) {
					System.out.println(txt + el2.attr("href"));
				}
			}
		}
	}
}
