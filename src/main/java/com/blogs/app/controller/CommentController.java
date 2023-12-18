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
import org.springframework.web.bind.annotation.RestController;

import com.blogs.app.dto.CommentDto;
import com.blogs.app.service.CommentService;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/{postId}/comment")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
		return commentService.getAllComment(postId);
	}

	@GetMapping("/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId) {
		return new ResponseEntity<CommentDto>(commentService.getCommentByCommentId(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/{postId}/comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(commentService.updateCommentById(postId, commentDto, commentId),
				HttpStatus.OK);
	}

	@DeleteMapping("/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId) {
		return new ResponseEntity<String>(commentService.deleteCommentById(postId, commentId), HttpStatus.OK);
	}

}
