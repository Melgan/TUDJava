package com.example.nosql.repository;

import java.util.List;

import com.example.nosql.domain.Ball;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallRepository extends CrudRepository<Ball, ObjectId>{

	Ball findById(ObjectId id);

	List<Ball> findByColor(String color);

	List<Ball> findByColorLike(String regex);

	List<Ball> findAll();

	@Query(value = "{ 'color' : ?0, 'type' : ?1 }")
	List<Ball> findByColorAndType(String color, String type);

}

