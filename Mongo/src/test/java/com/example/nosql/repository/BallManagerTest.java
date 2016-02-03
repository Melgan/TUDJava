package com.example.nosql.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.example.nosql.domain.Ball;
import com.example.nosql.domain.Manufacturer;
import com.example.nosql.service.ManufacturerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosql.service.BallManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class BallManagerTest {
	
	@Autowired
	BallManager ballManager;

	@Autowired
	ManufacturerManager manufacturerManager;

	private final static String NAME_1 = "Pilka1";
	private final static String COLOR_1 = "Red";
	private final static String TYPE_1 = "Football";
	private final static int SIZE_1 = 5;

	private final static String NAME_2 = "Pilka2";
	private final static String COLOR_2 = "Blue";
	private final static String TYPE_2 = "Volleyball";
	private final static int SIZE_2 = 4;

	private final static String NAME_MAN_1 = "Firma1";
	private final static String ADDRESS_1 = "Adres1";
	private final static int YOC_1 = 1981;
	
	@Test
	public void checkBallAdding(){
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		int initialNrOfBalls = ballManager.getAllBalls().size();

		ballManager.addBall(ball);
		
		int finalNrOfBalls = ballManager.getAllBalls().size();

		assertEquals(initialNrOfBalls+1, finalNrOfBalls);

	}


	@Test
	public void deleteBallCheck() {

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		int initialNrOfBalls = ballManager.getAllBalls().size();

		ballManager.addBall(ball);

		int afterAddNrOfBalls = ballManager.getAllBalls().size();
		assertEquals(initialNrOfBalls + 1, afterAddNrOfBalls);

		ballManager.deleteBall(ball);

		int afterDeleteNrOfBalls = ballManager.getAllBalls().size();

		assertEquals(initialNrOfBalls, afterDeleteNrOfBalls);


		assertEquals(null, ballManager.getBallById(ball.getId()));

	}


	@Test
	public void deleteAllBallsCheck() {

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		List<Ball> ballsAfterAdd = ballManager.getAllBalls();
		assertNotNull(ballsAfterAdd);

		ballManager.deleteAllBalls();

		assertEquals(0, ballManager.getAllBalls().size());

	}


	@Test
	public void findBallByIdCheck() {

		int initialNrOfBalls = ballManager.getAllBalls().size();

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);
		assertNotNull(ballManager.getBallById(ball.getId()));

		ballManager.deleteBall(ball);
		assertNull(ballManager.getBallById(ball.getId()));

		assertEquals(initialNrOfBalls, ballManager.getAllBalls().size());

	}

	@Test
	public void findAllBallsCheck() {

		int initialNrOfBalls = ballManager.getAllBalls().size();

		assertEquals(initialNrOfBalls, ballManager.getAllBalls().size());

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		assertEquals(initialNrOfBalls+2, ballManager.getAllBalls().size());

	}

	@Test
	public void updateBallCheck() {

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		Ball newBall = ballManager.getBallById(ball2.getId());

		assertEquals(NAME_2, newBall.getName());
		assertEquals(COLOR_2, newBall.getColor());
		assertEquals(SIZE_2, newBall.getSize());
		assertEquals(TYPE_2, newBall.getType());

		newBall.setType(TYPE_1);

		ballManager.updateBall(newBall);

		Ball notChangedBall = ballManager.getBallById(ball.getId());

		assertEquals(NAME_1, notChangedBall.getName());
		assertEquals(COLOR_1, notChangedBall.getColor());
		assertEquals(SIZE_1, notChangedBall.getSize());
		assertEquals(TYPE_1, notChangedBall.getType());

		Ball newestBall = ballManager.getBallById(ball2.getId());

		assertEquals(NAME_2, newestBall.getName());
		assertEquals(COLOR_2, newestBall.getColor());
		assertEquals(SIZE_2, newestBall.getSize());
		assertEquals(TYPE_1, newestBall.getType());

	}


	@Test
	public void getBallsByColorCheck() {

		int initialNrOfBallsWithColor = ballManager.getBallsByColor(COLOR_2).size();
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		List <Ball>  balls = ballManager.getBallsByColor(COLOR_2);


		assertEquals(initialNrOfBallsWithColor+1, balls.size());

	}

	@Test
	public void getBallsByColorLikeCheck() {

		int initialNrOfBallsWithColor = ballManager.getBallsByColor(COLOR_2).size();
		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);



		List <Ball> ballsByColorLike = ballManager.getBallsByColorLike(COLOR_2.substring(1, 3));
		List <Ball> ballsByColor = ballManager.getBallsByColor(COLOR_2);

		assertEquals(ballsByColorLike.get(0).getId(), ballsByColor.get(0).getId());
		assertEquals(ballsByColorLike.size(), ballsByColor.size());


		assertEquals(initialNrOfBallsWithColor+1, ballsByColorLike.size());

	}

	@Test
	public void getBallsByColorAndTypeCheck() {

		int initialNrOfBalls = ballManager.getBallsByColorAndType(COLOR_2,TYPE_2).size();

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		List <Ball>  balls = ballManager.getBallsByColorAndType(COLOR_2,TYPE_2);

		assertEquals(initialNrOfBalls+1, balls.size());

	}


	@Test
	public void getManufacturerBallsCheck() {

		manufacturerManager.deleteAllManufacturers();

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		List<Ball> ballsForManufacturer = new ArrayList<Ball>();

		ballsForManufacturer.add(ball2);

		int initialNrOfManufacturers = manufacturerManager.getAllManufacturers().size();
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYoc(YOC_1);
		manufacturer.setBalls(ballsForManufacturer);

		manufacturerManager.addNewManufacturer(manufacturer);

		List<Manufacturer> manufacturers = manufacturerManager.getAllManufacturers();

		assertEquals(initialNrOfManufacturers+1, manufacturers.size());

		Ball manufacturerBall = manufacturer.getBalls().get(0);

		assertEquals(ball2.getName(), manufacturerBall.getName());

	}

	@Test
	public void  getManufacturerBallsWithCriteria() {

		int initialNrOfManufacturers = manufacturerManager.getAllManufacturers().size();

		Ball ball = new Ball();
		ball.setName(NAME_1);
		ball.setColor(COLOR_1);
		ball.setSize(SIZE_1);
		ball.setType(TYPE_1);

		ballManager.addBall(ball);

		Ball ball2 = new Ball();
		ball2.setName(NAME_2);
		ball2.setColor(COLOR_2);
		ball2.setSize(SIZE_2);
		ball2.setType(TYPE_2);

		ballManager.addBall(ball2);

		int initialNrOfBalls = ballManager.getAllBalls().size();

		List<Ball> ballsForManufacturer = new ArrayList<Ball>();

		ballsForManufacturer.add(ball);
		ballsForManufacturer.add(ball2);

		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(NAME_MAN_1);
		manufacturer.setAddress(ADDRESS_1);
		manufacturer.setYoc(YOC_1);
		manufacturer.setBalls(ballsForManufacturer);

		manufacturerManager.addNewManufacturer(manufacturer);

		List<Ball> manufacturerBalls = manufacturerManager.getManufacturerBalls(manufacturer);

		assertEquals(2, manufacturerBalls.size());

		List<Manufacturer> manufacturers = manufacturerManager.getAllManufacturers();

		assertEquals(initialNrOfManufacturers+1, manufacturers.size());

		manufacturerManager.deleteBallByColor(manufacturer, COLOR_2);


		List<Ball> balls = ballManager.getAllBalls();

		assertEquals(initialNrOfBalls-1, balls.size());

		Ball fromManufacturer = balls.get(0);

		assertEquals(NAME_1, fromManufacturer.getName());
		assertEquals(COLOR_1, fromManufacturer.getColor());
		assertEquals(SIZE_1, fromManufacturer.getSize());
		assertEquals(TYPE_1, fromManufacturer.getType());

	}
	
}
