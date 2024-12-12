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
        ArrayList<String> nwWord = new ArrayList<>();
        ArrayList<String> neWord = new ArrayList<>();
        ArrayList<String> seWord = new ArrayList<>();
        ArrayList<String> swWord = new ArrayList<>();

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
        //set NE diag limits
            //if y is in bounds but x is out
            if( row - i >= 0 && col + i > grid.size()){
                nextNE = grid.get(row).get(col);
            }
            //if y is in bounds and x is in bounds
            if( row - i >= 0 && col + i < grid.size()){
                nextNE = grid.get(row - i).get(col + i);
            }
            //if y is out of bounds and x is in.
            if( row - i <= 0 && col + i < grid.size()){
                nextNE= grid.get(row).get(col);
            }
            //if y is out and x is out of bounds
            if( row - i <= 0 && col + i >= grid.size()){
                nextNE = grid.get(row).get(col);
            }
       //set NW Diag limits
            //if y is in and x is out
            if( row - i >= 0 && col - i < 0 ){
                nextNW = grid.get(row).get(col);
            }
            //if y is in and x is in
            if ( row - i >= 0 && col - i >= 0){
                nextNW = grid.get(row - i).get(col - i);
            }
            //if y is out and x is in
            if ( row - i < 0 && col - i >= 0){
                nextNW = grid.get(row).get(col);
            }
            //if y is out and x is out
            if ( row - i < 0 && col - i < 0){
                nextNW = grid.get(row).get(col);
            }
        //set SW Diag Limits
            //if y is in and x is out
            if ( row + i < grid.size() && col - i < 0){
                nextSW = grid.get(row).get(col);
            }
            //if y is in and x is in
            if ( row + i < grid.size() && col - i >= 0){
                nextSW = grid.get(row + i).get(col - i);
            }
            //if y is out and x is in
            if ( row + i >= grid.size() && col - i >= 0){
                nextSW = grid.get(row).get(col);
            }
            //if y is out and x is out
            if ( row + i >= grid.size() && col - i < 0){
                nextSW = grid.get(row).get(col);
            }
        //set SE Diag Limits
            //if y is in and x is out
            if( row + i < grid.size() && col + i >= grid.get(row).size()){
                nextSE = grid.get(row).get(col);
            }
            //if y is in and x is in
            if( row + i < grid.size() && col + i < grid.get(row).size()){
                nextSE = grid.get(row + i).get(col + i);
            }
            //if y is out and x is in
            if( row + i >= grid.size() && col + i < grid.get(row).size()){
                nextSE = grid.get(row).get(col);
            }
            //if y is out and x is out
            if( row + i >= grid.size() && col + i >= grid.get(row).size()){
                nextSE = grid.get(row).get(col);
            }
           neWord.add(nextNE);
           nwWord.add(nextNW);
           seWord.add(nextSE);
           swWord.add(nextSW);
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
        if (isMatch(neWord)){
            System.out.println("Found ne: " + neWord);
        }
        if (isMatch(seWord)){
            System.out.println("Found se: " + seWord);
        }
        if (isMatch(swWord)){
            System.out.println("Found sw: " + swWord);
        }
        if (isMatch(neWord)){
            System.out.println("Found ne: " + neWord);
        }
    }

    private static boolean isMatch(ArrayList<String> word){
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
