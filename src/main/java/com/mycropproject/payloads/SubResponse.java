package com.mycropproject.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class SubResponse {
	private List<SubDto> content;
	
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	
	
	private boolean lastPage;
}
