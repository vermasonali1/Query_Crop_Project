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

import com.mycropproject.payloads.WarehouseDto;
import com.mycropproject.payloads.WarehouseResponse;
import com.mycropproject.service.WarehouseService;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

		@Autowired
		private WarehouseService warehouseService;
		
		//post data-create 
		@PostMapping("/")
		public ResponseEntity<WarehouseDto> createWarehouse(@RequestBody WarehouseDto warehouseDto){
			WarehouseDto createWarehouseDto=this.warehouseService.createWarehouse(warehouseDto);
			return new ResponseEntity<>(createWarehouseDto,HttpStatus.CREATED);
		}
		
		
		//put data-update data
		@PutMapping("/{warehouseId}")
		public ResponseEntity<WarehouseDto> updateWarehouse( @RequestBody WarehouseDto warehouseDto,@PathVariable("warehouseId")Integer wid) {
			WarehouseDto updatedWarehouse = this.warehouseService.updateWarehouse(warehouseDto, wid);
			return ResponseEntity.ok(updatedWarehouse);
		}
		
	    //get data-read data
//		getallwarehouse=====================
	//	
		@GetMapping("/")
		public ResponseEntity<List<WarehouseDto>> getAllWarehouses(){
			return ResponseEntity.ok(this.warehouseService.getAllWarehouses());
		}
		
		
	  //	get single warehouse ==============
		@GetMapping("/{warehouseId}")
		public ResponseEntity<WarehouseDto> getSingleWarehouses(@PathVariable Integer warehouseId){
			return ResponseEntity.ok(this.warehouseService.getWarehouseById(warehouseId));
		}
		
		
		
		//delete data-delete
		@DeleteMapping("/{warehouseId}")
		public ResponseEntity<ApiResponse> deleteCrop(@PathVariable("warehouseId")Integer wid){
		 this.warehouseService.deleteWarehouse(wid);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Delete successfully", true),HttpStatus.OK);
		}
		
		
//		normal searching
//		search
		@GetMapping("/warehouse")
		public ResponseEntity<WarehouseResponse> findByTehsil(
				@RequestParam(value="pageNumber",defaultValue="1" ,required=false)Integer pageNumber,
				@RequestParam(value="pageSize",defaultValue="4",required=false)Integer pageSize,
				@RequestParam(value="sortBy",defaultValue="id",required=false)String sortBy,
				@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir,
				@RequestParam(value="keywords",required=false) String keywords){
				
			WarehouseResponse result=this.warehouseService.searchwarehouss(keywords, pageNumber, pageSize, sortBy,sortDir);
			return new ResponseEntity<WarehouseResponse> (result,HttpStatus.OK);

			}
		}