package com.blogs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.app.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
	