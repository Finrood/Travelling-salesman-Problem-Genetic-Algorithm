import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JComponent {
	static Population pop;
	static Population bestpop;
	static Main m;

	public static void main(String[] args) {
        City city = new City(60, 200);
        Manager.getListCities().add(city);
        City city2 = new City(180, 200);
        Manager.getListCities().add(city2);
        City city3 = new City(80, 180);
        Manager.getListCities().add(city3);
        City city4 = new City(140, 180);
        Manager.getListCities().add(city4);
        City city5 = new City(20, 160);
        Manager.getListCities().add(city5);
        City city6 = new City(100, 160);
        Manager.getListCities().add(city6);
        City city7 = new City(200, 160);
        Manager.getListCities().add(city7);
        City city8 = new City(140, 140);
        Manager.getListCities().add(city8);
        City city9 = new City(40, 120);
        Manager.getListCities().add(city9);
        City city10 = new City(100, 120);
        Manager.getListCities().add(city10);
        City city11 = new City(180, 100);
        Manager.getListCities().add(city11);
        City city12 = new City(60, 80);
        Manager.getListCities().add(city12);
        City city13 = new City(120, 80);
        Manager.getListCities().add(city13);
        City city14 = new City(180, 60);
        Manager.getListCities().add(city14);
        City city15 = new City(20, 40);
        Manager.getListCities().add(city15);
        City city16 = new City(100, 40);
        Manager.getListCities().add(city16);
        City city17 = new City(200, 40);
        Manager.getListCities().add(city17);
        City city18 = new City(20, 20);
        Manager.getListCities().add(city18);
        City city19 = new City(60, 20);
        Manager.getListCities().add(city19);
        City city20 = new City(160, 20);
        Manager.getListCities().add(city20);

        for(int i=0; i<10; i++) {
        	Manager.getListCities().add(new City((int)(Math.random()*200), (int)(Math.random()*200)));
        }


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m = new Main();
        m.setPreferredSize(new Dimension(600,800));
        frame.getContentPane().add(m, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        JButton start = new JButton("Start");
        JButton refresh = new JButton("refresh");
        frame.pack();
        frame.setVisible(true);
        buttonsPanel.add(start);
        buttonsPanel.add(refresh);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        		start.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	bestpop = new Population(50, true);;

                	for(int i=0; i<20; i++) {
                		pop = new Population(50, true);
                        System.out.println("Initial distance: " + pop.getFittest().getTotalDistance());
                        //pop = GeneticAlgo.evolvePopulation(pop);
                     	uiFunction();
                      	System.out.println("Finished");
                        System.out.println("Final distance: " + pop.getFittest().getTotalDistance());
                	}
                	System.out.println("Solution:");
                	System.out.println("Final distance: " + bestpop.getFittest().getTotalDistance());
                    System.out.println(bestpop.getFittest());
                    //m.repaint();
                    }
                });
    }


	static void uiFunction() {
		for (int i = 0; i < 1000; i++) {
			int tempDist = bestpop.getFittest().getTotalDistance();
			pop = GeneticAlgo.evolvePopulation(pop);
            System.out.println("Best distance of generation " + i + " : " + pop.getFittest().getTotalDistance());
            if (pop.getFittest().getTotalDistance() < tempDist) {
            	bestpop = pop;
            	m.getGraphics().clearRect(0, 0, 800, 600);
	            for(int j=0; j<bestpop.getFittest().getRoute().size()-1; j++) {
		    		int x1 = bestpop.getFittest().getRoute().get(j).getX();
		    		int y1 = bestpop.getFittest().getRoute().get(j).getY();
		            int x2 = bestpop.getFittest().getRoute().get(j+1).getX();
		            int y2 = bestpop.getFittest().getRoute().get(j+1).getY();

		            m.getGraphics().setColor(Color.BLACK);
		            m.getGraphics().drawLine(x1, y1, x2, y2);
	            }
            }
		}
	}
}
