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

import com.mycropproject.payloads.ApiResponse;
import com.mycropproject.payloads.CropDto;
import com.mycropproject.payloads.CropResponse;
import com.mycropproject.service.CropService;

@RestController
@RequestMapping("/api/crops")
public class CropController {

	@Autowired
	private CropService cropService;
	
	//post data-create user
	@PostMapping("/")
	public ResponseEntity<CropDto> createCrop(@RequestBody CropDto cropDto){
		CropDto createCropDto=this.cropService.createCrop(cropDto);
		return new ResponseEntity<>(createCropDto,HttpStatus.CREATED);
	}
	
	
	//put data-update data
	@PutMapping("/{cropId}")
	public ResponseEntity<CropDto> updateCrop( @RequestBody CropDto cropDto,@PathVariable Integer cropId) {
		CropDto updatedCrop = this.cropService.updateCrop(cropDto, cropId);
		return ResponseEntity.ok(updatedCrop);
		
		
			
	}
	
    //get data-read data
//	getalluser=====================
//	
//	@GetMapping("/")
//	public ResponseEntity<List<CropDto>> getAllCrops(){
//		return ResponseEntity.ok(this.cropService.getAllCrops());
//	}
	
	
  //	get single crop ==============
	@GetMapping("/{cropId}")
	public ResponseEntity<CropDto> getSingleCrops(@PathVariable Integer cropId){
		return ResponseEntity.ok(this.cropService.getCropById(cropId));
	}
		
	
	//delete data-delete
	@DeleteMapping("/{cropId}")
	public ResponseEntity<ApiResponse> deleteCrop(@PathVariable Integer cropId){
	 this.cropService.deleteCrop(cropId);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("Delete successfully", true),HttpStatus.OK);
	}
	
	
//	pagination and sorting
	@GetMapping("/")
	public ResponseEntity<CropResponse> getAllCrop(
			@RequestParam(value="pageNumber",defaultValue="1" ,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="4",required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="id",required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir
			){
		CropResponse cropResponse=this.cropService.getAllCrops(pageNumber, pageSize, sortBy, sortDir);
//				
		return new ResponseEntity<CropResponse>(cropResponse,HttpStatus.OK);
}
}

