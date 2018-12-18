package edu.csuft.lwz.spider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 程序入口
 * @author 卢威志
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		
		//线程池
		//固定大小
		ExecutorService pool=Executors.newFixedThreadPool(4);
		
		//无限（缓存）
		//pool=Executors.newCachedThreadPool();
		
		//一个线程
		//pool=Executors.newSingleThreadExecutor();
		
		for(int i=1;i<=100;i++){
			pool.submit(new Task(i));
		}
		
		//pool.submit(task);
		
		
		
		
		
		//目标url
//		String url="https://movie.douban.com/top250";
//		
//		//使用 JSOUP 抓取文档
//		
//		try {
//			Document doc=Jsoup.connect(url).get();
//			
//			
//
//			
//			//创建一个影片的列表
//			ArrayList<Film> list=new ArrayList<>();
//			
//			Elements es=doc.select(".grid_view .item");
//			
//			for(Element e :es){
//				Film f=new Film();
//				f.id=Integer.parseInt(e.select(".pic em").first().text());
//				f.poster=e.select("img").first().attr("src");
//				f.title=e.select(".title").first().text();
//				f.info=e.select(".bd p").first().text();
//				f.rating=e.select(".rating_num").text();
//				String num=e.select(".star span").last().text();
//				f.num=Integer.parseInt(num.substring(0,num.length()-3));
//				f.quote=e.select(".inq").text();
//				
//				System.out.println(f);
//				
//				list.add(f);
//				
//			}
//			
////			Elements es2=doc.select(".grid_view .item");
////			for(Element e :es2){
////				
////				Element t=e.select(".title").first();
////				String rate=e.select(".rating_num").text();
////				String num=e.select(".star span").last().text();
////				String quote=e.select(".inq").text();
////				String info=e.select(".bd p").first().text();
////				System.out.println(i+t.text()+","+num+","+rate+quote+info);
////				
////			}
//			//String title=doc.title();
//			//String data=doc.data();
//			
//			//System.out.println(title);
//			//System.out.println(data);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
