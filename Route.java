import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Route {
	private List<City> route = new ArrayList<>();
	private int distance = 0;
	private double fitness = 0;

	public Route(List<City> route) {
		this.route = route;
	}

	public Route() {
		IntStream.range(0, Manager.getListCities().size())
				.forEach(i -> route.add(null));
	}

	public List<City> getRoute() {
		return route;
	}

	public void setRoute(ArrayList<City> route) {
		this.route = route;
	}

	public void generateIndividual() {
		IntStream.range(0, Manager.getListCities().size())
				.forEach(i -> this.setCityAtindex(Manager.getListCities().get(i), i));
		Collections.shuffle(route);
	}

	public City getCityAt(int index) {
		return route.get(index);
	}

	public void setCityAtindex(City c, int index) {
		this.getRoute().set(index, c);
		distance = 0;
		fitness = 0;
	}

	public int getTotalDistance() {
		if (distance == 0) {
			int totalDistance = 0;

			for (int i = 0; i < route.size(); i++) {
				City from = getCityAt(i);
				City destination = (i + 1 < route.size()) ? getCityAt(i + 1) : getCityAt(0);
				totalDistance += from.distanceToCity(destination);
			}
			distance = totalDistance;
		}
		return distance;
	}

	public double getFitness() {
		if (fitness == 0) {
			fitness = 1.0 / getTotalDistance();
		}
		return fitness;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("|");
		getRoute().forEach(city -> s.append(city).append("|"));
		return s.toString();
	}
}