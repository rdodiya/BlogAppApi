package com.blogs.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.app.entity.Comment;
import com.blogs.app.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPost(Post post);

}
