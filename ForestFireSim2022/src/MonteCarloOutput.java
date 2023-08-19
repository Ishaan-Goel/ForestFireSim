import java.util.SortedMap;

public class MonteCarloOutput {

    public static Simulator sim;
    public static GUI gui;

    public static final int NUM_TRIES = 50;

    public static void main(String[] args) {
        double density;
        double [][] table = new double[20][4];

        System.out.println(" density    BurntTrees     PlantedTrees     NumOfSteps     Pct Burnt \n");
        density = 0.05;
        for (int i=0; i<20; i++)
        {
            table[i][1] = 0.0;
            table[i][2] = 0.0;
            table[i][3] = 0.0;
        }
        for (int i=0; i<20; i++) {
            for (int j = 0; j < NUM_TRIES; j++) {
                sim = new Simulator(100, 100);
                sim.init(density);
                sim.setFire();
                sim.fullRun();
                table[i][1] = table[i][1] + sim.getBurntTrees();
                table[i][2] = table[i][2] +  sim.getPlantedTrees();
                table[i][3] = table[i][3] + sim.getStepsTakenToBurn(); //multiply by delay will give time
            }
            table[i][0] = density;
            table[i][1] = (double)table[i][1]/NUM_TRIES;
            table[i][2] = (double)table[i][2]/NUM_TRIES;
            table[i][3] = (double)table[i][3]/NUM_TRIES;
            density = density + 0.05;
        }
        for (int i = 0; i< 20; i++) {
           System.out.printf("%.2f    %.0f      %.0f     %.0f    %.2f\n", table[i][0], table[i][1], table[i][2],
                    table[i][3], (double)table[i][1]*100/table[i][2]);
        }

        // A non-graphical runner for doing a lot
        // of simulations and displaying the results

        // You'd run this if you wanted to run, say 1000 trials for
        // a set of initial conditions and see the results.
    }
}
