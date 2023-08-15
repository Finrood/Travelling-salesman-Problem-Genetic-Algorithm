import java.util.Arrays;

public class Population {
    private Route[] routes;

    public Population(int popSize, boolean init) {
        routes = new Route[popSize];

        if (init) {
            Arrays.setAll(routes, i -> {
                Route newRoute = new Route();
                newRoute.generateIndividual();
                return newRoute;
            });
        }
    }

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    public void saveRoute(int index, Route r) {
        routes[index] = r;
    }

    public Route getRoute(int index) {
        return routes[index];
    }

    public Route getFittest() {
        return Arrays.stream(routes)
                .max((route1, route2) -> Double.compare(route1.getFitness(), route2.getFitness()))
                .orElse(null);
    }
}