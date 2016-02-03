package com.example.nosql.service;

import java.util.List;

import com.example.nosql.domain.Ball;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosql.repository.BallRepository;

@Component
public class BallManager {

	@Autowired
	private BallRepository ballRepository;
	
	public void addBall(Ball ball) {
		ballRepository.save(ball);
	}
	
	public void deleteBall(Ball ball) {
		ballRepository.delete(ball);
	}
	
	public void deleteAllBalls() {
		ballRepository.deleteAll();
	}
	
	public void updateBall(Ball ball) {
		ballRepository.save(ball);
	}
	
	public List<Ball> getAllBalls() {
		return ballRepository.findAll();
	}

	public List<Ball> getBallsByColor(String color) { return ballRepository.findByColor(color); }

	public List<Ball> getBallsByColorAndType(String color, String type) {
		return ballRepository.findByColorAndType(color, type);
	}
	
	public Ball getBallById(ObjectId id) {
		return ballRepository.findById(id);
	}


	public List<Ball> getBallsByColorLike(String regex) { return ballRepository.findByColorLike(regex); }
}
