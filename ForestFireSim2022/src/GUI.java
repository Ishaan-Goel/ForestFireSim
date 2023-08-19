import processing.core.*;

public class GUI extends PApplet {
    private static Simulator sim;
    private static int setupRun = 0;
    DisplayWindow display;

    public void settings() {
        size(640, 550); // set the size of the screen.
    }

    public void setup(Double density) {
        // Create a simulator
        sim = new Simulator(100, 100);

        sim.init(density);

        // Create the display
        // parameters: (10,10) is upper left of display
        // (620, 530) is the width and height
        display = new DisplayWindow(this, 10, 10, 620, 530);

        display.setNumCols(sim.getWidth());		// NOTE:  these must match your simulator!!
        display.setNumRows(sim.getHeight());

        // Set different grid values to different colors
        int red = color(255, 0, 0);          // pattern:  color(redAmount, greenAmount, blueAmount)
        int ashBrown = color(73, 63, 47);
        int green = color(0, 255, 0);
        int white = color(255, 255, 255);
        display.setColor(1, green);          // 1 displays as green
        display.setColor(2, red);            // 2 displays as red
        display.setColor(3, ashBrown);           // 3 displays as grey
        display.setColor(0, white);          // 0 displays as white

        sim.setFire();
        frameRate(5);
    }

   @Override
    public void draw() {
        background(200);
        if (setupRun == 0) {
            setup(0.45);
            setupRun = 1;
        }


         sim.spreadFire();

        display.drawGrid(sim.getDisplayGrid()); // display the game
    }

    public static void main(String[] args) {
        PApplet.main("GUI");
    }
}