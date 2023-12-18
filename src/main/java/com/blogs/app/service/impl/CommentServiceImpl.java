package com.blogs.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blogs.app.dto.CommentDto;
import com.blogs.app.entity.Comment;
import com.blogs.app.entity.Post;
import com.blogs.app.exception.BlogApiException;
import com.blogs.app.exception.ResourceNotFoundException;
import com.blogs.app.repository.CommentRepository;
import com.blogs.app.repository.PostRepository;
import com.blogs.app.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Comment comment = mapToComment(commentDto);
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		comment.setPost(post);
		return mapToDto(commentRepository.save(comment));
	}

	@Override
	public List<CommentDto> getAllComment(long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		List<Comment> commentList = commentRepository.findByPost(post);
		List<CommentDto> commentDtoList = new ArrayList<>();
		for (Comment comment : commentList) {
			commentDtoList.add(mapToDto(comment));
		}
		return commentDtoList;
	}

	@Override
	public CommentDto getCommentByCommentId(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not below to Post");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateCommentById(long postId, CommentDto commentDto, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not below to Post");
		}

		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		return mapToDto(commentRepository.save(comment));
	}

	@Override
	public String deleteCommentById(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not below to Post");
		}
		commentRepository.delete(comment);
		if (commentRepository.existsById(commentId)) {
			return "failed";
		} else {
			return "success";
		}
	}

	private CommentDto mapToDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

	private Comment mapToComment(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}

}
