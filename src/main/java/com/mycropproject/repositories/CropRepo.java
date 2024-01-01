package com.mycropproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycropproject.entities.Crop;

public interface CropRepo extends JpaRepository<Crop,Integer> {
//
//	@Query("select c from Crop c")
//	List<Crop> getAll();
	
}
