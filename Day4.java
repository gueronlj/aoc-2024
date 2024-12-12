import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day4 {

    static String targetWord = "XMAS";
    static int count = 0;
    static ArrayList<ArrayList<String>> grid = new ArrayList<>();
    static ArrayList<int[]> startingPoints = new ArrayList<>();


    public static void main(String[] args) {
        importData();
        mapStartingPoints();
        for (int[] coords: startingPoints){
            //check y, and x coords
            System.out.println(coords[0] +", "+ coords[1]);
            checkArea(coords[0], coords[1]);
        }
        System.out.println(count);
    }

    static  void importData(){
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

    //Should always be fed starting coordinates of an 'X'
    static void checkArea( int row, int col ){
        ArrayList<String> leftWord = new ArrayList<>();
        ArrayList<String> rightWord = new ArrayList<>();
        ArrayList<String> upWord = new ArrayList<>();
        ArrayList<String> downWord = new ArrayList<>();

        for (int i = 0; i < targetWord.length(); i++) {
            String nextLeft = "";
            String nextRight = "";
            String nextUp = "";
            String nextDown = "";
            String nextNE = "";
            String nextNW = "";
            String nextSE = "";
            String nextSW = "";

            //set left limit
            if ( col - i >= 0 ){
                nextLeft = grid.get(row).get(col - i);
                leftWord.add(nextLeft);
            }
            //set right limit
            if (col + i <= grid.get(row).size()-1){
                nextRight = grid.get(row).get(col + i);
                rightWord.add(nextRight);
            }
            //set upward limit
            if (row - i >= 0 ){
                nextUp = grid.get(row - i).get(col);
                upWord.add(nextUp);
            }
            //set downward limit
            if (row + i <= grid.size()-1){
                nextDown = grid.get(row + i).get(col);
                downWord.add(nextDown);
            }
            //set NE diag
//            nextNE = grid.get(row - i).get(col+i);
//            nextNW = grid.get(row + i).get(col-i);
//            nextSE = grid.get(row + i).get(col+i);
//            nextSW = grid.get(row + i).get(col-i);
        }

        if ( isMatch(leftWord) ){
            System.out.println("Found left: " + leftWord);
        }
        if (isMatch(rightWord)){
            System.out.println("Found right: " + rightWord);
        }
        if (isMatch(upWord)){
            System.out.println("Found up: " + upWord);
        }
        if (isMatch(downWord)){
            System.out.println("Found down: " + downWord);
        }
    }

    private static boolean isMatch(ArrayList<String> word){
        System.out.println(word);
        String string = String.join("", word);
        String reversed = new StringBuilder(targetWord).reverse().toString();

        if ( string.equals(targetWord) || string.equals(reversed)){
            count++;
            return true;
        }
        return false;

    }

    private static void mapStartingPoints(){
        String startingLetter = "X";
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
