package src.main.java.model;

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
		this.route = new ArrayList<>(Collections.nCopies(Manager.getCities().size(), null));
	}

	public List<City> getRoute() {
		return route;
	}

	public void setRoute(List<City> route) {
		this.route = route;
		this.distance = 0;
		this.fitness = 0;
	}

	public void generateIndividual() {
		IntStream.range(0, Manager.getCities().size())
				.forEach(i -> this.setCityAtIndex(Manager.getCities().get(i), i));
		Collections.shuffle(route);
	}

	public City getCityAtIndex(int index) {
		return route.get(index);
	}

	public void setCityAtIndex(City c, int index) {
		this.route.set(index, c);
		distance = 0;
		fitness = 0;
	}

	public int getTotalDistance() {
		if (distance == 0) {
			distance = IntStream.range(0, route.size())
					.mapToObj(i -> {
						City from = getCityAtIndex(i);
						City destination = (i + 1 < route.size()) ? getCityAtIndex(i + 1) : getCityAtIndex(0);
						return from.distanceToCity(destination);
					})
					.mapToInt(Double::intValue)
					.sum();
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
		route.forEach(city -> s.append(city).append("|"));
		return s.toString();
	}
}
