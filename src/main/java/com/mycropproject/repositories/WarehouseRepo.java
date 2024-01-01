package com.mycropproject.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mycropproject.entities.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse,Integer> {
	
	@Query("SELECT w FROM Warehouse w where w.tehsil like %?1%")
	Page<Warehouse> gettehsilbylike( String tehsil,Pageable pageable);
	

//	this is for gett all query 
	@Query("select w from Warehouse w")
	List<Warehouse> getAlla();
	
//	get by id
	@Query("select w from Warehouse w where w.id =:id")
	Warehouse getbyyid(Integer id);
	
//	this for delete
//	@Query("delete from warehouse where id =:id")
//	void deletebyid(Integer id);
	
	
}


