package com.mycropproject.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycropproject.entities.Crop;
import com.mycropproject.entities.Sub;
import com.mycropproject.entities.Warehouse;



	public interface SubRepo extends JpaRepository<Sub,Integer> {

		List<Sub> findByCrop(Crop crop);
		
		
		@Query("select a from Sub a where a.name like:key")
		List<Sub> searchByName(@Param("key")  String name);
	
//		ye dlt ke liye h
//		List<Sub> deleteSubByCropId(Integer cropId);
	

}
