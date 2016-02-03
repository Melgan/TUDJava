package com.example.nosql.domain;

import java.util.List;
import org.bson.types.ObjectId;

public class Manufacturer {
	
	private ObjectId id;
	private String name;
	private String address;
	private int yoc;
	private List<Ball> balls;

	public ObjectId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYoc() {
		return yoc;
	}

	public void setYoc(int yoc) {
		this.yoc = yoc;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

}










