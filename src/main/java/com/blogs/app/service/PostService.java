package com.blogs.app.service;

import java.util.List;

import com.blogs.app.dto.PostDto;
import com.blogs.app.dto.PostPageDto;

public interface PostService {

	PostDto createPost(PostDto postDto);

	PostPageDto getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(Long id);

	PostDto updatePostById(PostDto postDto, Long id);

	String deletePostById(Long id);
}
