import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day4 {

    static String startingLetter = "A";
    static int count = 0;
    static ArrayList<ArrayList<String>> grid = new ArrayList<>();
    static ArrayList<int[]> startingPoints = new ArrayList<>();

    public static void main(String[] args) {
        importData();
        mapStartingPoints();
        for (int[] coords: startingPoints){
            //check the Y and X (row and column)
            checkArea(coords[0], coords[1]);
        }
        System.out.println(count);
    }

    static  void importData(){
        //This will place each letter onto a grid
        String path = "day4-input.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                //Place each letter into the grid, row by row
                ArrayList<String> row = new ArrayList<String>();
                String[] letters = line.split("");
                Collections.addAll(row, letters);
                grid.add(row);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Should always be fed coordinates of a starting letter
    static void checkArea( int row, int col ){
        //Check the 4 corners around 'A' (the starting point)...Imagine a keypad
        //getGridValue will ensure we stay in bounds of grid
        String kp1 = getGridValue(row - 1, col - 1);
        String kp3 = getGridValue(row - 1, col + 1);
        String kp7 = getGridValue(row + 1, col - 1);
        String kp9 = getGridValue(row + 1, col + 1);

        if (isPatternMatch(kp1, kp3, kp7, kp9)) {
            System.out.println("Found X?: " + row + ", " + col);
            count++;
        }
        if (isPatternMatch(kp3, kp9, kp7, kp1)) {
            System.out.println("Found X?: " + row + ", " + col);
            count++;
        }
        if (isPatternMatch(kp9, kp7, kp1, kp3)) {
            System.out.println("Found X?: " + row + ", " + col);
            count++;
        }
        if (isPatternMatch(kp7, kp1, kp3, kp9)) {
            System.out.println("Found X?: " + row + ", " + col);
            count++;
        }
    }

    static String getGridValue(int row, int col) {
        if (row>= 0 && row < grid.size() && col >= 0 && col < grid.get(row).size()) {
            return grid.get(row).get(col);
        }
        // Return an empty string if out of bounds
        return "";
    }

    static boolean isPatternMatch(String corner1, String corner2, String corner3, String corner4s) {
        return (corner1.equals("S") && corner2.equals("S") && corner3.equals("M") && corner4s.equals("M"));
    }

    private static void mapStartingPoints(){
        for (ArrayList<String>row : grid){
            for(int i = 0; i< row.size(); i++){
                if ( row.get(i).equals(startingLetter)){
                    int[] coords = {grid.indexOf(row), i};
                    startingPoints.add(coords);
                }
            }
        }
    }
}
