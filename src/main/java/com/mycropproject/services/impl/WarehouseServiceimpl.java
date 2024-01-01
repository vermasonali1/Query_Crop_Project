package com.mycropproject.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mycropproject.entities.Warehouse;
import com.mycropproject.exceptions.ResourceNotFoundException;

import com.mycropproject.payloads.WarehouseDto;
import com.mycropproject.payloads.WarehouseResponse;
import com.mycropproject.repositories.WarehouseRepo;
import com.mycropproject.service.WarehouseService;

@Service
public class WarehouseServiceimpl implements WarehouseService {

	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public WarehouseDto createWarehouse(WarehouseDto warehouseDto) {

		Warehouse warehouse =this.dtoToWarehouse(warehouseDto);
		Warehouse savedWarehouse = this.warehouseRepo.save(warehouse);
		return this.warehouseToDto(savedWarehouse);
//	
		
	}

	@Override
	public WarehouseDto updateWarehouse(WarehouseDto warehouse, Integer warehouseId) {
		Warehouse warehouse1=this.warehouseRepo.findById(warehouseId).orElseThrow(()->new ResourceNotFoundException("warehouse","warehouse id",warehouseId));	
		warehouse1.setTehsil(warehouse.getTehsil());
		warehouse1.setName(warehouse.getName());
		
		Warehouse updatedWarehouse=this.warehouseRepo.save(warehouse1);
		WarehouseDto warehouseDto1=this.warehouseToDto(updatedWarehouse);
		return warehouseDto1;
//		
	}

//	without query
//	@Override
//	public WarehouseDto getWarehouseById(Integer warehouseId) {
//		Warehouse warehouse=this.warehouseRepo.getbyyid(warehouseId).orElseThrow(()-> new ResourceNotFoundException("warehouse","Id",warehouseId));
//		return this.warehouseToDto(warehouse);
//	}
	
//	help of query
	@Override
	public WarehouseDto getWarehouseById(Integer warehouseId) {
		Warehouse warehouse=this.warehouseRepo.getbyyid(warehouseId);
		return this.warehouseToDto(warehouse);
	}

	@Override
	public List<WarehouseDto> getAllWarehouses() {
		
			List<Warehouse> warehouses=this.warehouseRepo.getAlla();
			List<WarehouseDto> warehouseDtos=warehouses.stream().map(warehouse->this.warehouseToDto(warehouse)).collect(Collectors.toList());
			return warehouseDtos;
			
	}

// searching sorting and paginaton
	
	@Override
	public WarehouseResponse searchwarehouss(String keyword,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
	      if(sortDir.equalsIgnoreCase("asc"))
	      {
	    	  sort=Sort.by(sortBy).ascending();
	      }
	      else
	      {
	    	  sort=Sort.by(sortBy).descending();
	      }
	      
	      
		Pageable s= PageRequest.of(pageNumber, pageSize,sort);
		Page<Warehouse> pageWarehouse= (Page<Warehouse>) this.warehouseRepo.gettehsilbylike(keyword, s);

		     List<Warehouse> allWarehouses=pageWarehouse.getContent();

			List<WarehouseDto> warehouseDtos=allWarehouses.stream().map((warehouse)->this.modelMapper.map(warehouse, WarehouseDto.class)).collect(Collectors.toList());

			WarehouseResponse wareResponse=new WarehouseResponse();
			
			wareResponse.setContent(warehouseDtos);
			wareResponse.setPageNumber(pageWarehouse.getNumber());
			wareResponse.setPageSize(pageWarehouse.getSize());
			wareResponse.setTotalElements(pageWarehouse.getTotalElements());
			
			wareResponse.setTotalPages(pageWarehouse.getTotalPages());
			wareResponse.setLastPage(pageWarehouse.isLast());
			
			return wareResponse;
					}

//without query
	@Override
	public void deleteWarehouse(Integer warehouseId) {
		
			Warehouse warehouse=this.warehouseRepo.findById(warehouseId).orElseThrow(()-> new ResourceNotFoundException("warehouse","Id",warehouseId));
			this.warehouseRepo.delete(warehouse);
//		
	}
	
//	@Override
//	public void deleteWarehouse(Integer warehouseId) {
//		
//			Warehouse warehouse=this.warehouseRepo.deletebyid(warehouseId);warehouseId).orElseThrow(()-> new ResourceNotFoundException("warehouse","Id",warehouseId));
//			this.warehouseRepo.delete(warehouse);
////		
//	}
	
//////conversion
	public Warehouse dtoToWarehouse(WarehouseDto warehouseDto) {
		Warehouse warehouse=this.modelMapper.map(warehouseDto, Warehouse.class);
		
		return warehouse;
		
	}
	
	public WarehouseDto warehouseToDto(Warehouse warehouse) {
		WarehouseDto warehouseDto=this.modelMapper.map(warehouse,WarehouseDto.class);
		
	    return warehouseDto;
	}
}
