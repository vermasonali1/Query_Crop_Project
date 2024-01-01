package com.mycropproject.service;

import java.util.List;

import com.mycropproject.payloads.CropDto;
import com.mycropproject.payloads.CropResponse;

public interface CropService {
	CropDto createCrop(CropDto crop);
			
	CropDto updateCrop(CropDto crop,Integer cropId);
	
	CropDto getCropById(Integer cropId);
	
	//get all crops pagination and sorting
	CropResponse getAllCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

//	List<CropDto> getAllCrops();
	
	void deleteCrop(Integer cropId);
	
	
}




