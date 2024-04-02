import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager {
	private static final List<City> cities = new ArrayList<>();

	public static List<City> getCities() {
		return Collections.unmodifiableList(cities);
	}

	public static List<City> addCity(City city) {
		cities.add(city);
		return cities;
	}

	public static void setCities(List<City> cities) {
		Manager.cities.clear();
		Manager.cities.addAll(cities);
	}
}
