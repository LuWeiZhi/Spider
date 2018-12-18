package edu.csuft.lwz.spider;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



/**
 * ץȡҳ����ӰƬ��Ϣ
 * @author ¬��־
 *
 */
public class Spider implements Runnable{
	
	/**
	 * ҳ���·����URL��
	 */
	
	String url;
	
	/**
	 * �洢ץȡ������
	 */
	ArrayList<Film> list;

	/**
	 * ��������
	 * @param url	ҳ���·��
	 * @param list	��ȡ���ݵ��б�
	 */
	public Spider(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//���ִ�и�������̵߳�����
		String name=Thread.currentThread().getName();
		System.out.println(name+"�̣߳�����"+url);
		
		//JSOUP
		try {
			Document doc=  Jsoup.connect(url).get();
			
			//���ĵ��в��ҽ��
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
			System.out.println(name+"�̣߳���ɣ�"+url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
