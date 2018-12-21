package edu.csuft.lwz.spider;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/***
 * 抓取影片的详细信息
 * @author ASUS
 *
 */

public class SpiderFilmDetail implements Runnable{
	
	/**
	 * 影片的URL
	 */
	String url;
	
	/**
	 * 影片列表
	 */
	List<Film> list;
	
	

	public SpiderFilmDetail(String url, List<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Document doc=Jsoup.connect(url).get();
			
			Element e=doc.getElementById("content");
			
			String name=e.select("h1 span").first().text();
			String year=e.select(" .year").text();
			String diretor=e.select("#info .attrs").get(0).selectFirst(".attrs a").text();
			String script=e.select("#info .attrs").get(1).text();
			String actor=e.selectFirst(".actor .attrs").text();
			
			System.out.println(name+","+year+","+diretor+","+script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
