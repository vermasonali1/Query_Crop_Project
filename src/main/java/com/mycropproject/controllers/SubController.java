package com.mycropproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycropproject.entities.Sub;
import com.mycropproject.payloads.ApiResponse;

import com.mycropproject.payloads.SubDto;
import com.mycropproject.payloads.SubResponse;
import com.mycropproject.service.SubService;


@RestController
@RequestMapping("/api/")
public class SubController {


	@Autowired
	private SubService subService;
	

//	create
	
	@PostMapping("/crop/{cropId}/subs")
	public ResponseEntity<SubDto> createSub(
			@RequestBody SubDto subDto,
			@PathVariable Integer cropId)
	{
		SubDto createSub = this.subService.createSub(subDto, cropId);
	    return new ResponseEntity<SubDto>(createSub,HttpStatus.CREATED);
	}
	

//	get by crop---------------------------------------------------------------
	@GetMapping("/crop/{cropId}/subs")
	public ResponseEntity<List<SubDto>> getSubByCrop(
			@PathVariable Integer cropId)
	{
		List<SubDto> subs = this.subService.getSubsByCrop(cropId);
		return new ResponseEntity<List<SubDto>>(subs,HttpStatus.OK);
	}
	
	
////	get all authors
////	
//	@GetMapping("/subs")
//	public ResponseEntity<List<SubDto>> getAllSub(){
//			List<SubDto> allSub=this.subService.getAllSub();
//		return new ResponseEntity<List<SubDto>>(allSub,HttpStatus.OK);
//	
//	}
////	
//	 
//
//	//		get sub detail by id
//	
	@GetMapping("/subs/{subId}")
	public ResponseEntity<SubDto> getSubById(@PathVariable Integer subId){
		
		SubDto subDto=this.subService.getSubById(subId);
		return new ResponseEntity<SubDto>(subDto,HttpStatus.OK);
	}
	
	
//	delete
	@DeleteMapping("/subs/{subId}")
	public ApiResponse deleteSub(@PathVariable Integer subId)
	{
		this.subService.deleteSub(subId);
		return new ApiResponse("subcrop is successfully deleted!!!",true);
	}
	
//	update
	@PutMapping("/subs/{subId}")
	public ResponseEntity<SubDto> updateSub(@RequestBody SubDto subDto,@PathVariable Integer subId){
	
		SubDto updateSub = this.subService.updateSub(subDto, subId);
		return new ResponseEntity<SubDto>(updateSub,HttpStatus.OK);
	}
	
//	search
	@GetMapping("/subs/search/{keywords}")
	public ResponseEntity<List<SubDto>> searchSubByName(
		@PathVariable("keywords") String keywords){
			
		List<SubDto> result=this.subService.searchSubs(keywords);
		return new ResponseEntity<List<SubDto>> (result,HttpStatus.OK);
	}
	
//	delete sub by crop
	

//	@DeleteMapping("/crop/{cropId}/sub")
//	public ApiResponse deleteSubByCropId(@PathVariable Integer cropId)
//	{
//		this.subService.deleteSubByCropId(cropId);
//		return new ApiResponse("sub is successfully deleted!!!",true);
//	}
	
	//	pagination and sorting
//	this get mapping is used for pagination and searching if the user cant
//	give any keyword so all data get on postman..but else user 
//	can give keyword so get specific data on postman

	@GetMapping("/subs")
	public ResponseEntity<SubResponse> getAllSub(
			@RequestParam(value="pageNumber",defaultValue="1" ,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="4",required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="id",required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir
			){
		SubResponse subResponse=this.subService.getAllSubs(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<SubResponse>(subResponse,HttpStatus.OK);
}
	
	
}




