import java.util.ArrayList;
import java.util.List;

public class Manager {
	private static List<City> listCities = new ArrayList<City>();

	public static List<City> getListCities() {
		return listCities;
	}

	public static void setListCities(List<City> listCities) {
		Manager.listCities = listCities;
	}
}
