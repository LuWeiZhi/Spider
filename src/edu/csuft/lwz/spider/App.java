package edu.csuft.lwz.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * �������
 * @author ¬��־
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		
		//Ŀ��url
		String url="https://movie.douban.com/top250";
		
		//ʹ�� JSOUP ץȡ�ĵ�
		
		try {
			Document doc=Jsoup.connect(url).get();
			
			
//			Elements es= doc.select("img[src]");
//			System.out.println(es.size());
			
			//����һ��ӰƬ���б�
			ArrayList<Film> list=new ArrayList<>();
			
			Elements es=doc.select(".grid_view .item");
			
			for(Element e :es){
				Film f=new Film();
				f.title=e.select(".title").first().text();
				f.info=e.select(".bd p").first().text();
				f.rating=e.select(".rating_num").text();
				f.num=e.select(".star span").last().text();
				f.quote=e.select(".inq").text();
				System.out.println(f.title+f.info+f.rating+f.num+f.quote);
				
			}
			
//			Elements es2=doc.select(".grid_view .item");
//			for(Element e :es2){
//				
//				Element t=e.select(".title").first();
//				String rate=e.select(".rating_num").text();
//				String num=e.select(".star span").last().text();
//				String quote=e.select(".inq").text();
//				String info=e.select(".bd p").first().text();
//				System.out.println(i+t.text()+","+num+","+rate+quote+info);
//				
//			}
			//String title=doc.title();
			//String data=doc.data();
			
			//System.out.println(title);
			//System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
