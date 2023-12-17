package com.blogs.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPageDto {

	private List<PostDto> postDtoList;
	private int pageNo;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private boolean isLastPage;
	
}
