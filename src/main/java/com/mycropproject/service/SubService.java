package com.mycropproject.service;

import java.util.List;

import com.mycropproject.payloads.SubDto;
import com.mycropproject.payloads.SubResponse;

public interface SubService {
	


//	create
	SubDto createSub(SubDto subDto,Integer cropId);
	
//	update
	SubDto updateSub(SubDto subDto, Integer subId);
	
//	delete
	void deleteSub(Integer subId);
	
	
//	get all 
//	List<SubDto>getAllSub();
	
//	pagination
	SubResponse getAllSubs(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

//	get single 
	SubDto getSubById(Integer subId);
	
//	get all sub by crop
	List<SubDto>getSubsByCrop(Integer cropId);
	
	
//delete all sub by crop
//	delete
	void deleteSubByCropId(Integer cropId);
	
	
//	searching
	List<SubDto>searchSubs(String keyword);
	
}
