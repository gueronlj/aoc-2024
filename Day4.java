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
        System.out.println("Total: " + count);
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

        //spread out in all directions, collecting letters in that direction
        for (int i = 0; i < targetWord.length(); i++) {
            addLetter(leftWord, row, col - i);
            addLetter(rightWord, row, col + i);
            addLetter(upWord, row - i, col);
            addLetter(downWord, row + i, col);
            addLetter(nwWord, row - i, col - i);
            addLetter(neWord, row - i, col + i);
            addLetter(swWord, row + i, col + i);
            addLetter(seWord, row + i, col - i);
        }

        //check each word found in each direction for match with target word.
        checkForMatch(upWord);
        checkForMatch(neWord);
        checkForMatch(rightWord);
        checkForMatch(seWord);
        checkForMatch(downWord);
        checkForMatch(swWord);
        checkForMatch(leftWord);
        checkForMatch(nwWord);
    }

    static void addLetter(ArrayList<String> word, int row, int col){
        if(isInBounds(row, col)){
            word.add(grid.get(row).get(col));
        }
    }
    static boolean isInBounds(int row, int col) {
        //coords must be inside of grid
        return row >= 0 && row < grid.size() && col >= 0 && col < grid.get(row).size();
    }

    private static void checkForMatch(ArrayList<String> word){
        String string = String.join("", word);
        if(string.equals(targetWord)){
            count++;
            System.out.println(word);
        }
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
