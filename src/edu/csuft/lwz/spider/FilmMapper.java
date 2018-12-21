package edu.csuft.lwz.spider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//注解，装饰器
@Mapper
public interface FilmMapper {
	
	//映射 ORM mapping
	@Insert("insert into film(id,title,num,rating,url,info) values(#{id},#{title},#{num},#{rating},#{url},#{info})")
	void save (Film film);
	
	@Select("select *from film where id=#{id}")
	Film load(int id);
}
