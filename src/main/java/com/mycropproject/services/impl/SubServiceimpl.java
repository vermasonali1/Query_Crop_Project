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
import com.mycropproject.entities.Sub;
import com.mycropproject.exceptions.ResourceNotFoundException;

import com.mycropproject.payloads.SubDto;
import com.mycropproject.payloads.SubResponse;
import com.mycropproject.repositories.CropRepo;
import com.mycropproject.repositories.SubRepo;
import com.mycropproject.service.SubService;

@Service
public class SubServiceimpl implements SubService {

	@Autowired
	private SubRepo subRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CropRepo cropRepo;

	@Override
	public SubDto createSub(SubDto subDto, Integer cropId) {
		Crop crop=this.cropRepo.findById(cropId).orElseThrow(()-> new ResourceNotFoundException("Crop","CropId",cropId));
		
		Sub sub=this.modelMapper.map(subDto, Sub.class);
		
		sub.setCrop(crop);
		
		
		Sub newSub=this.subRepo.save(sub);
		
		return this.modelMapper.map(newSub, SubDto.class);
	
	}
		

	@Override
	public SubDto updateSub(SubDto subDto, Integer subId) {
		Sub sub=this.subRepo.findById(subId).orElseThrow(()->new ResourceNotFoundException("Sub","subId",subId));	
		sub.setName(subDto.getName());
		
		Sub updatedSub=this.subRepo.save(sub);
		return this.modelMapper.map(updatedSub, SubDto.class);

	}

	@Override
	public void deleteSub(Integer subId) {
		Sub sub=this.subRepo.findById(subId).orElseThrow(()->new ResourceNotFoundException("Sub","subId",subId));	
		this.subRepo.delete(sub);
		

		
	}

//	@Override
//	public List<SubDto> getAllSub() {
//		List<Sub> allsubs=this.subRepo.findAll();
//		
//				List<SubDto> subDtos=allsubs.stream().map((sub)->this.modelMapper.map(sub, SubDto.class)).collect(Collectors.toList());
//										
//		return subDtos;
//	
//	}

	@Override
	public SubDto getSubById(Integer subId) {
		Sub sub=this.subRepo.findById(subId).orElseThrow(()->new ResourceNotFoundException("Sub","SubId",subId));
		
		return this.modelMapper.map(sub,SubDto.class);
			
	}

	@Override
	public List<SubDto> searchSubs(String keyword) {

		List<Sub> subs=this.subRepo.searchByName("%"+keyword+"%");
//				
		List<SubDto> subDtos=subs.stream().map((sub)->this.modelMapper.map(sub, SubDto.class)).collect(Collectors.toList());
		return subDtos;
	

	}




	@Override
	public void deleteSubByCropId(Integer cropId) {
		Crop crop=this.cropRepo.findById(cropId).orElseThrow(()->new ResourceNotFoundException("Crop","cropId",cropId));	
		List<Sub> subs=this.subRepo.findByCrop(crop);
	
		this.subRepo.deleteAll(subs);
		this.cropRepo.delete(crop);
		
			
	}




	@Override
	public List<SubDto> getSubsByCrop(Integer cropId) {


		Crop crop=this.cropRepo.findById(cropId).orElseThrow(()->new ResourceNotFoundException("Crop","CropId",cropId));
		
		List<Sub> subs = this.subRepo.findByCrop(crop); 
		List<SubDto> subDtos =subs.stream().map((sub)->this.modelMapper.map(sub, SubDto.class)).collect(Collectors.toList());
		return subDtos;
		
	}

//pagination
	@Override
	public SubResponse getAllSubs(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
	    Sort sort=null;
	      if(sortDir.equalsIgnoreCase("asc"))
	      {
	    	  sort=Sort.by(sortBy).ascending();
	      }
	      else
	      {
	    	  sort=Sort.by(sortBy).descending();
	      }
	      
		Pageable s=PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Sub> pageSub=this.subRepo.findAll(s);
		
		List<Sub> allSubs=pageSub.getContent();

		List<SubDto> cropDtos=allSubs.stream().map((sub)->this.modelMapper.map(sub, SubDto.class)).collect(Collectors.toList());
		
		SubResponse subResponse=new SubResponse();
		
		subResponse.setContent(cropDtos);
		subResponse.setPageNumber(pageSub.getNumber());
		subResponse.setPageSize(pageSub.getSize());
		subResponse.setTotalElements(pageSub.getTotalElements());
		
		subResponse.setTotalPages(pageSub.getTotalPages());
		subResponse.setLastPage(pageSub.isLast());
		
		return subResponse;
	
	}
	
}
