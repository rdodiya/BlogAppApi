package com.blogs.app.controller;

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

import com.blogs.app.dto.PostDto;
import com.blogs.app.dto.PostPageDto;
import com.blogs.app.service.PostService;
import com.blogs.app.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	PostService postService;

	// create blog post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	@GetMapping
	public PostPageDto getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir) {
		return postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
		return new ResponseEntity<PostDto>(postService.getPostById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") long id, @RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(postService.updatePostById(postDto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> putMethodName(@PathVariable(name = "id") long id) {
		return new ResponseEntity<String>(postService.deletePostById(id), HttpStatus.OK);
	}

}
