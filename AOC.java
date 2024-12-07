import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;

public class AOC {
    private static ArrayList<Integer> left = new ArrayList<>();
    private static ArrayList<Integer> right = new ArrayList<>();
    private static ArrayList<String[]> pairs = new ArrayList<>();
    private static int total = 0;
    private static int simScore = 0;

    public static void main(String[] args) {
        importData();
        separatePairs();
        //getDiff();
        countOcurances();
    }

    private static void importData(){
        String path = "input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null) {
               pairs.add(line.split("\\s+"));
            }
        } catch (IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void separatePairs(){
        for (String[] pair : pairs){
            left.add(parseInt(pair[0]));
            right.add(parseInt(pair[1]));
        }
        Collections.sort(left);
        Collections.sort(right);
    }

    private static void getDiff(){
        for(int x = 0; x < left.size(); x++){
            System.out.println(left.get(x)+" "+right.get(x));
            int diff = Math.abs(left.get(x)-right.get(x));
            System.out.println("Diff: "+diff);
            total = total + diff;
        }
        System.out.println(total);
    }

    private static void countOcurances(){
        for (int num : left){
            int count = 0;
            for (int num2 : right){
                if(num2 == num){
                    count++;
                }
            }
            simScore += num * count;
        }
        System.out.println(simScore);
    }
}
