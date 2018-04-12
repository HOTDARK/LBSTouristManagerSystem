package com.hd.sys.test;



import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import com.hd.sys.entity.SysOrg;
import com.hd.sys.service.SysOrgService;
import com.hd.sys.service.impl.SysOrgServiceImpl;

public class PaAreaJW  extends BaseUnit  {
	private SysOrgService sysOrgService;
	@Before
	public void setup() {
		sysOrgService = context.getBean(SysOrgServiceImpl.class);
	}
	private static List<String> urlList= new ArrayList<String>();
	
	@Test
	public  void test() throws Exception {
		Document docs = Jsoup
				.connect("http://jingwei.supfree.net/mengzi.asp?id=2212")
				.header("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
				.get();
		Elements els = docs.getElementsByAttributeValue("class", "oul");
		for (Element element : els) {
			Elements tags = element.getElementsByTag("a");
			for (Element el2 : tags) {
				String txt = el2.text();
				if (!StringUtil.isBlank(txt)) {
					urlList.add(el2.attr("href"));
				}
			}
		}
		for (String url : urlList) {
			GetDocument(url,sysOrgService);
		}
	}

	private static void GetDocument(String url,SysOrgService sysOrgService) throws Exception {
		Document doc2 = Jsoup
				.connect("http://jingwei.supfree.net/"+url)
				.header("User-Agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2")
				.get();
		Elements els = doc2.getElementsByAttributeValue("class", "cdiv");
		for (Element el1 : els) {
			Elements areaEl = el1.getElementsByTag("strong");
			if(null != areaEl){
				String area = areaEl.text();
				String[] jd = el1.getElementsByAttributeValue("class", "bred botitle18").text().split(" ");
				if(jd.length<2){
					continue;
				}
				String fullName="";
//				Double jing=Double.parseDouble(jd[0]);
//				Double wei=Double.parseDouble(jd[1]);
				if(area.equals("重庆市")){
					fullName=area;
				}else{
					fullName=area.replace("重庆市 ", "");
				}
				System.out.println(fullName);
				SysOrg sysOrg=new SysOrg();
				sysOrg.setOrgFullName(fullName);
				List<SysOrg> orgData = sysOrgService.getOrgData(sysOrg);
				if (orgData!=null&&orgData.size()>0) {
					sysOrg=orgData.get(0);
				}
				if (sysOrg!=null) {
					//sysOrg.setJingdu(jing);
					//sysOrg.setWeidu(wei);
					//sysOrgService.update(sysOrg);
				}
			}
		}
	}
}
