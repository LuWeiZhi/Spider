package edu.csuft.lwz.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * 抓取页面中影片信息
 * @author 卢威志
 *
 */
public class Spider implements Runnable{
	
	/**
	 * 页面的路径（URL）
	 */
	
	String url;
	
	/**
	 * 存储抓取的数据
	 */
	ArrayList<Film> list;

	/**
	 * 创建爬虫
	 * @param url	页面的路径
	 * @param list	存取数据的列表
	 */
	public Spider(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//获得执行该任务的线程的名称
		String name=Thread.currentThread().getName();
		System.out.println(name+"线程，处理："+url);
		
		//JSOUP
		try {
			Document doc=  Jsoup.connect(url).get();
			
			//从文档中查找结点
			Elements es=doc.select(".grid_view .item");
			
			for(Element e :es){
				Film f=new Film();
				f.id=Integer.parseInt(e.select(".pic em").first().text());
				f.poster=e.select("img").first().attr("src");
				f.title=e.select(".title").first().text();
				f.info=e.select(".bd p").first().text();
				f.rating=Double.parseDouble(e.select(".rating_num").text());
				String num=e.select(".star span").last().text();
				f.num=Integer.parseInt(num.substring(0,num.length()-3));
				f.quote=e.select(".inq").text();
				
				//System.out.println(name+":"+f);
				
				list.add(f);
				
			}
			System.out.println(name+"线程，完成："+url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
