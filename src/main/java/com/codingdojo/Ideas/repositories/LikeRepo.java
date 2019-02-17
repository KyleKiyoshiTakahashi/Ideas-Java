package com.codingdojo.Ideas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.Ideas.models.Likes;

@Repository
public interface LikeRepo extends CrudRepository<Likes, Long> {
	
}
