package edu.csuft.lwz.spider;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 程序入口
 * @author ־卢威志
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		
		//线程池
		//固定大小
		ExecutorService pool=Executors.newFixedThreadPool(4);
		
		//无限
		pool=Executors.newCachedThreadPool();
		
		//一个线程
		//pool=Executors.newSingleThreadExecutor();
		
		ArrayList<Film> list=new ArrayList<>();
		String url="https://movie.douban.com/top250?start=";
		for(int i=0;i<10;i++){
			String path = String.format("%s%d", url,i*25);
			pool.submit(new Spider(path,list));
		}
		
		pool.shutdown();
		System.out.println(pool.isTerminated());
		
		while(!pool.isTerminated()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println(list.size());
		
		
		//导入数据库
		//myBatis/iBatis
		try {
			SqlSessionFactory factory=new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsStream("config.xml"));
			
			//打开会话
			SqlSession session=factory.openSession();
			
			//动态管理
			FilmMapper mapper=(FilmMapper) session.getMapper(FilmMapper.class);
			
			for(Film f:list){
				mapper.save(f);
			}
			
			session.commit();
			session.close();
			
			System.out.println("存储完成");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
//		ExecutorService pool2=Executors.newFixedThreadPool(4);
//		
//		for(Film film:list){
////			System.out.println(film);
//			pool2.submit(new SpiderFilmDetail(film.url, list));
//		}
		
		
		//写入文件 电子表格兼容模式
		
//		String file="d:/film.csv";	//绝对路径
//		 file="film.csv";			//相对路径	
//		 
//		 //排序
//		 Collections.sort(list,null);
//		 
//		 try(FileWriter out =new FileWriter(file)) {
//			 //写数据
//			 for(Film film:list){
//				 out.write(film.toCSV());
//			 }
//			System.out.println("ok");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		//pool.submit(task);
		 
	
		 
		 
			
	}

}
