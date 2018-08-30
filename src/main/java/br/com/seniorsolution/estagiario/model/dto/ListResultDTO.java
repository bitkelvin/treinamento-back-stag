package br.com.seniorsolution.estagiario.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResultDTO<T> {
	
	private List<T> content;
	
	private boolean firstPage;
	private boolean lastPage;
	private long totalPages;
	
	private int limit;
	private int offset;

	private int numberOfElements;
	private long totalElements;
    
}