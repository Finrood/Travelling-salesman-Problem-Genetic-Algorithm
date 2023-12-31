public class GeneticAlgo {
	/* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 2;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.getRoutes().length, false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveRoute(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.getRoutes().length; i++) {
            // Select parents
            Route parent1 = tournamentSelection(pop);
            Route parent2 = tournamentSelection(pop);
            // Crossover parents
            Route child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveRoute(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.getRoutes().length; i++) {
            mutate(newPopulation.getRoute(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Route crossover(Route parent1, Route parent2) {
        // Create new child tour
        Route child = new Route();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.getRoute().size());
        int endPos = (int) (Math.random() * parent1.getRoute().size());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.getRoute().size(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCityAtindex(parent1.getCityAt(i), i);
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCityAtindex(parent1.getCityAt(i), i);
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.getRoute().size(); i++) {
            // If child doesn't have the city add it
            if (!child.getRoute().contains(parent2.getCityAt(i))) {
                // Loop to find a spare position in the child's tour
                for (int j = 0; j < child.getRoute().size(); j++) {
                    // Spare position found, add city
                    if (child.getCityAt(j) == null) {
                        child.setCityAtindex(parent2.getCityAt(i), j);
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Route tour) {
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.getRoute().size(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.getRoute().size() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCityAt(tourPos1);
                City city2 = tour.getCityAt(tourPos2);

                // Swap them around
                tour.setCityAtindex(city1, tourPos2);
                tour.setCityAtindex(city2, tourPos1);
            }
        }
    }

    // Selects candidate tour for crossover
    private static Route tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getRoutes().length);
            tournament.saveRoute(i, pop.getRoute(randomId));
        }
        // Get the fittest tour
        Route fittest = tournament.getFittest();
        return fittest;
    }
}
