package com.blogs.app.service;

import java.util.List;

import com.blogs.app.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId,CommentDto CommentDto);

	List<CommentDto> getAllComment(long postId);

	CommentDto getCommentByCommentId(long postId,long commentId);

	CommentDto updateCommentById(long postId,CommentDto CommentDto, long commentId);

	String deleteCommentById(long postId,long commentId);
}
