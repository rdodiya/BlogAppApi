package com.blogs.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogs.app.dto.PostDto;
import com.blogs.app.dto.PostPageDto;
import com.blogs.app.entity.Post;
import com.blogs.app.exception.ResourceNotFoundException;
import com.blogs.app.repository.PostRepository;
import com.blogs.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToPost(postDto);
		return mapToDto(postRepository.save(post));
	}

	@Override
	public PostPageDto getAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {
		
		Sort sort=sortBy.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pagePosts = postRepository.findAll(pageable);
		List<Post> postList = pagePosts.getContent();
		List<PostDto> postDtos = postList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		PostPageDto postPageDto = new PostPageDto();
		postPageDto.setPostDtoList(postDtos);
		postPageDto.setPageNo(pagePosts.getNumber());
		postPageDto.setPageSize(pagePosts.getSize());
		postPageDto.setTotalElements(pagePosts.getTotalElements());
		postPageDto.setTotalPages(pagePosts.getTotalPages());
		postPageDto.setLastPage(pagePosts.isLast());
		return postPageDto;
	}

	@Override
	public PostDto getPostById(Long id) {
		return mapToDto(postRepository.getReferenceById(id));
	}

	@Override
	public PostDto updatePostById(PostDto postDto, Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());

		return mapToDto(postRepository.save(post));
	}

	@Override
	public String deletePostById(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
		postRepository.delete(post);
		if (postRepository.existsById(id)) {
			return "failed";
		} else {
			return "success";
		}
	}

	private PostDto mapToDto(Post post) {
		PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setDescription(post.getDescription());
		return dto;
	}

	private Post mapToPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}

}
