package com.mycropproject.service;
import java.util.List;

import com.mycropproject.payloads.WarehouseDto;
import com.mycropproject.payloads.WarehouseResponse;

public interface WarehouseService {
	
		WarehouseDto createWarehouse(WarehouseDto warehouse);
				
		WarehouseDto updateWarehouse(WarehouseDto warehouse,Integer warehouseId);
		
		WarehouseDto getWarehouseById(Integer warehouseId);
		
		List<WarehouseDto> getAllWarehouses();
	
		void deleteWarehouse(Integer warehouseId);
		
		
//		searching normal
		WarehouseResponse searchwarehouss(String keyword,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

//
//		List<WarehouseDto>searchWarehouses(String keyword);
//		
}
