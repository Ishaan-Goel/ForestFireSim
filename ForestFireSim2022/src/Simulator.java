
public class Simulator {
    private static int height;
    private static  int width;

    private  int plantedTrees=0;

    private  int burntTrees=0;

    private  int stepsTakenToBurn=0;

    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;
    private int [][] forest;

    private static final int NOT_TREE = 0;
    private static final int LIVING_TREE = 1;
    private static final int BURNING_TREE= 2;
    private static final int ASH = 3;

    public Simulator(int height, int width) {
        if (width <= 0 || height <= 0) {
            System.out.println("Dimensions <= zero.");
            System.out.println("Using default values.");
            this.height = DEFAULT_HEIGHT;
            this.width = DEFAULT_WIDTH;
        }
        else {
            this.height = height;
            this.width = width;
        }
        forest = new int[height][width];
    }
    public void fullRun(){

        int ctr =0, numofloops = 0;
        ctr = spreadFire();
        stepsTakenToBurn++;
        while (ctr != 0){
            burntTrees = burntTrees + ctr;
            ctr = spreadFire();
            stepsTakenToBurn++;
        }

    }
    public void setFire(){
        int i=0, j=0;
        boolean isSetFire = false;
        while (!isSetFire) {
            i = (int) (Math.random() * height);
            j = (int) (Math.random() * width);
                forest[i][j] = BURNING_TREE;
                isSetFire = true;
        }
        burntTrees = 1;
    }
    public int spreadFire(){
        boolean isSame = false;
        int counter = 0, countlivingtrees=0;
        int [][] nextStep = makeCopyOf(forest);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (forest[i][j] == LIVING_TREE)
                    countlivingtrees++;

                if (forest[i][j] == LIVING_TREE && isNeighborOnFire(forest, i, j)){
                    counter ++;
                    nextStep[i][j] = BURNING_TREE;
                }

            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (forest[i][j] == BURNING_TREE)
                    nextStep[i][j] = ASH;
            }
        }
        forest = nextStep;
        return counter;
    }

    public int [][] makeCopyOf(int[][] arr){
        int [][] newArr = new int [height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
    public boolean isNeighborOnFire(int [][] forest, int row, int col){
       // System.out.println("isNeighborOnFire " + row + " " + col);
        if((row -1) >=0 && forest[row-1][col] == BURNING_TREE)
            return true;
        if ((row -1) >= 0 && (col+1) < width && forest[row-1][col+1] == BURNING_TREE)
            return true;
        if((row-1) >= 0 && (col-1) >= 0 && forest[row-1][col-1] == BURNING_TREE)
            return true;
        if((col+1) < width && forest[row][col+1] == BURNING_TREE)
            return true;
        if((col-1) >= 0 && forest[row][col-1] == BURNING_TREE)
            return true;
        if((row+1) < height && forest[row+1][col] == BURNING_TREE)
            return true;
        if((row+1) < height && (col+1) < width && forest[row+1][col+1] == BURNING_TREE)
            return true;
        if((row+1) < height && (col-1) > 0 && forest[row+1][col-1] == BURNING_TREE) {
            return true;
        }
        return false;
    }


    public void init(double density){
        int requiredTrees = (int) (height*width*density);
        int row =0, col=0;
        while (plantedTrees < requiredTrees){
            row = (int)(Math.random()*height);
            col = (int)(Math.random()*width);

            if (forest[row][col] != LIVING_TREE)
            {
                forest[row][col] = LIVING_TREE;
                plantedTrees++;
            }
        }
    }

    public void setTreeStatus(int status) {

    }


    public int getWidth() {
        return width;   // TODO: change this once you make a grid variable
    }

    public int getHeight() {
        return height;   // TODO: change this once you make a grid variable
    }

    public int[][] getDisplayGrid() {
        return forest;    // TODO: change this to return your grid variable
    }

    public int getPlantedTrees() {
        return plantedTrees;
    }
    public int getBurntTrees() {
        return burntTrees;
    }

    public int getStepsTakenToBurn() {
        return stepsTakenToBurn;
    }

}