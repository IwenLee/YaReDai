package com.huawei.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huawei.entity.Acount;


/**
 * 注意这里实现的接口是JpaRepository，也可以实现CrudRepository接口，他是JpaRepository的超类接口，，层级关系如下：
 * Repository  		接口 	（超接口）
 *   CrudRepository 	接口
 *     PagingAndSortingRepository 接口
 *       JpaRepository    接口
 *         SimpleJpaRepository 类
 * 		     QueryDslJpaRepository 类
 *         AcountDao	  接口
 *       KeyValueRepository  接口（和JpaRepository平辈关系）
 *         SimpleKeyValueRepository   类
 *           QuerydslKeyValueRepository  类
 * @author Administrator
 */
public interface AcountDao extends JpaRepository<Acount, Long> {

	public Acount findById(int id);
	
	@Query("select a from Acount a where a.userName=:userName and a.id=:id")  
    public Acount findUserByName(@Param("id") int id, @Param("userName") String userName);  
	
}
