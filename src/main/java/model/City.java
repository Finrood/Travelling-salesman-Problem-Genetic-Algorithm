package src.main.java.model;

import java.util.Random;

public class City {
	private int x, y;

	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public City() {
		final Random random = new Random();
		this.x = random.nextInt(200);
		this.y = random.nextInt(200);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double distanceToCity(City c) {
		return Math.hypot(this.getX() - c.getX(), this.getY() - c.getY());
	}

	@Override
	public String toString() {
		return this.getX() + "," + this.getY();
	}
}
