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

import com.mycropproject.entities.Crop;
import com.mycropproject.exceptions.ResourceNotFoundException;
import com.mycropproject.payloads.CropDto;
import com.mycropproject.payloads.CropResponse;
import com.mycropproject.repositories.CropRepo;
import com.mycropproject.service.CropService;

@Service
public class CropServiceimpl implements CropService{
	
	@Autowired
	private CropRepo cropRepo;

	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public CropDto createCrop(CropDto cropDto) {
				
			Crop crop =this.dtoToCrop(cropDto);
			Crop savedCrop = this.cropRepo.save(crop);
			return this.cropToDto(savedCrop);
		
	}

	@Override
	public CropDto updateCrop(CropDto crop, Integer cropId) {

		Crop crop1=this.cropRepo.findById(cropId).orElseThrow(()-> new ResourceNotFoundException("Crop"," cropId",cropId));
		
		crop1.setName(crop.getName());
			
			Crop updatedCrop=this.cropRepo.save(crop1);
			
			CropDto cropDto1=this.cropToDto(updatedCrop);
			
			return cropDto1;
		
	}

	@Override
	public CropDto getCropById(Integer cropId) {
		Crop crop=this.cropRepo.findById(cropId).orElseThrow(()-> new ResourceNotFoundException("Crop","cropId",cropId));
		return this.cropToDto(crop);
	}

//	@Override
//	public List<CropDto> getAllCrops() {
//
//		List<Crop> crops=this.cropRepo.findAll();
//		List<CropDto> cropDtos=crops.stream().map(crop->this.cropToDto(crop)).collect(Collectors.toList());
//		return cropDtos;
//	}

	@Override
	public void deleteCrop(Integer cropId) {
		Crop crop=this.cropRepo.findById(cropId).orElseThrow(()-> new ResourceNotFoundException("Crop","cropId",cropId));
		this.cropRepo.delete(crop);
	}
	
////conversion
	public Crop dtoToCrop(CropDto cropDto) {
		Crop crop=this.modelMapper.map(cropDto, Crop.class);
		
		return crop;
		
	}
	
	public CropDto cropToDto(Crop crop) {
		CropDto cropDto=this.modelMapper.map(crop,CropDto.class);
		
	    return cropDto;
	}

		
	@Override
	public CropResponse getAllCrops(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

    Sort sort=null;
    
      if(sortDir.equalsIgnoreCase("asc"))
      {
    	  sort=Sort.by(sortBy).ascending();
      }
      else
      {
    	  sort=Sort.by(sortBy).descending();
      }
      

		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Crop> pageCrop=this.cropRepo.findAll(p);
		
		List<Crop> allCrops=pageCrop.getContent();

		List<CropDto> cropDtos=allCrops.stream().map((crop)->this.modelMapper.map(crop, CropDto.class)).collect(Collectors.toList());
		
		CropResponse cropResponse=new CropResponse();
		
		
		cropResponse.setContent(cropDtos);
		cropResponse.setPageNumber(pageCrop.getNumber());
		cropResponse.setPageSize(pageCrop.getSize());
		cropResponse.setTotalElements(pageCrop.getTotalElements());
		
		cropResponse.setTotalPages(pageCrop.getTotalPages());
		cropResponse.setLastPage(pageCrop.isLast());
		
		return cropResponse;
	
	}	
	
}
