package src.main.java.ui;

import src.main.java.algorithm.GeneticAlgo;
import src.main.java.model.City;
import src.main.java.model.Manager;
import src.main.java.model.Population;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JComponent {
    static Population pop;
    static Population bestpop;
    static Main m;

    public static void main(String[] args) {
        initializeCities();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m = new Main();
        m.setPreferredSize(new Dimension(600, 800));
        frame.getContentPane().add(m, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        JButton start = new JButton("Start");
        JButton refresh = new JButton("Refresh");
        frame.pack();
        frame.setVisible(true);
        buttonsPanel.add(start);
        buttonsPanel.add(refresh);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        start.addActionListener(actionListener -> {
            bestpop = new Population(50, true);

            for (int i = 0; i < 20; i++) {
                pop = new Population(50, true);
                System.out.println("Initial distance: " + pop.getFittest().getTotalDistance());
                uiFunction();
                System.out.println("Finished");
                System.out.println("Final distance: " + pop.getFittest().getTotalDistance());
            }
            System.out.println("Solution:");
            System.out.println("Final distance: " + bestpop.getFittest().getTotalDistance());
            System.out.println(bestpop.getFittest());
        });
    }

    private static void initializeCities() {
        final City city = new City(60, 200);
        Manager.addCity(city);
        final City city2 = new City(180, 200);
        Manager.addCity(city2);
        final City city3 = new City(80, 180);
        Manager.addCity(city3);
        final City city4 = new City(140, 180);
        Manager.addCity(city4);
        final City city5 = new City(20, 160);
        Manager.addCity(city5);
        final City city6 = new City(100, 160);
        Manager.addCity(city6);
        final City city7 = new City(200, 160);
        Manager.addCity(city7);
        final City city8 = new City(140, 140);
        Manager.addCity(city8);
        final City city9 = new City(40, 120);
        Manager.addCity(city9);
        final City city10 = new City(100, 120);
        Manager.addCity(city10);
        final City city11 = new City(180, 100);
        Manager.addCity(city11);
        final City city12 = new City(60, 80);
        Manager.addCity(city12);
        final City city13 = new City(120, 80);
        Manager.addCity(city13);
        final City city14 = new City(180, 60);
        Manager.addCity(city14);
        final City city15 = new City(20, 40);
        Manager.addCity(city15);
        final City city16 = new City(100, 40);
        Manager.addCity(city16);
        final City city17 = new City(200, 40);
        Manager.addCity(city17);
        final City city18 = new City(20, 20);
        Manager.addCity(city18);
        final City city19 = new City(60, 20);
        Manager.addCity(city19);
        final City city20 = new City(160, 20);
        Manager.addCity(city20);

        for (int i = 0; i < 10; i++) {
            Manager.addCity(new City((int) (Math.random() * 600), (int) (Math.random() * 600)));
        }
    }

    private static void uiFunction() {
        for (int i = 0; i < 1000; i++) {
            int tempDist = bestpop.getFittest().getTotalDistance();
            pop = GeneticAlgo.evolvePopulation(pop);
            System.out.println("Best distance of generation " + i + " : " + pop.getFittest().getTotalDistance());
            if (pop.getFittest().getTotalDistance() < tempDist) {
                bestpop = pop;
                Graphics g = m.getGraphics();
                g.clearRect(0, 0, 800, 800);
                // Draw dots for each city
                for (City city : bestpop.getFittest().getRoute()) {
                    g.setColor(Color.RED); // Set dot color
                    // Draw a filled circle for each city
                    g.fillOval(city.getX() - 2, city.getY() - 2, 5, 5);
                }
                // Draw lines between cities
                for (int j = 0; j < bestpop.getFittest().getRoute().size() - 1; j++) {
                    int x1 = bestpop.getFittest().getRoute().get(j).getX();
                    int y1 = bestpop.getFittest().getRoute().get(j).getY();
                    int x2 = bestpop.getFittest().getRoute().get(j + 1).getX();
                    int y2 = bestpop.getFittest().getRoute().get(j + 1).getY();

                    g.setColor(Color.BLACK); // Set line color
                    // Draw a line between each pair of adjacent cities
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }

}
