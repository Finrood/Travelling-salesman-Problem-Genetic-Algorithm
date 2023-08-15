public class City {
	private int x, y;
	
	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public City () {
		x = (int) (Math.random() * 200);
		y = (int) (Math.random() * 200);
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
	
	public double distanceToCity (City c) {
		int distanceX = Math.abs(this.getX() - c.getX());
		int distanceY = Math.abs(this.getY() - c.getY());

        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
	}
	
	@Override
	public String toString() {
		return (this.getX() + "," + this.getY());
	}
}
