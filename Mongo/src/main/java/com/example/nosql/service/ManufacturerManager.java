package com.example.nosql.service;

import java.util.List;

import com.example.nosql.domain.Ball;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosql.domain.Manufacturer;
import com.example.nosql.repository.BallRepository;
import com.example.nosql.repository.ManufacturerRepository;

@Component
public class ManufacturerManager {

	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Autowired
	private BallRepository ballRepository;
	
	public void addNewManufacturer(Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
	}
	
	public void deleteManufacturer(Manufacturer manufacturer) {
		for (Ball ball : manufacturer.getBalls()) {
			ballRepository.delete(ball);
		}
		manufacturerRepository.delete(manufacturer);
	}
	
	public void deleteAllManufacturers() {
		manufacturerRepository.deleteAll();
	}
	
	public void updateManufacturer(Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
	}
	
	public List<Manufacturer> getAllManufacturers() {

		return manufacturerRepository.findAll();
	}

	public List<Manufacturer> getManufacturerByNameAndYoc(String name, int yoc) {
		return manufacturerRepository.findByNameAndYoc(name, yoc);
	}
	
	public Manufacturer getManufacturerById(ObjectId id) {
		return manufacturerRepository.findById(id);
	}
	
	public List<Ball> getManufacturerBalls(Manufacturer manufacturer) {
		List<Ball> balls = manufacturer.getBalls();
		return balls;
	}


	public void deleteBallByColor(Manufacturer manufacturer, String color) {
		for (Ball ball : manufacturer.getBalls()) {
			if(ball.getColor() == color){
				ballRepository.delete(ball);
			}
		}
	}
	
	
}
