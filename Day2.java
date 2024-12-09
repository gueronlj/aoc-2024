import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Day2 {
    private static ArrayList<ArrayList<Integer>> reports= new ArrayList<>();
    private static int fixedCounter = 0;

    public static void main(String[] args) {
        importData();
        int count = 0;
        for(ArrayList<Integer> report : reports){
            if(checkReport(report)){
                count++;
            }
        }
        for(ArrayList<Integer> report : reports){
            if(modifyReport(report)){
                fixedCounter ++;
            }
        }
        System.out.println("Fixed: "+fixedCounter);
    }
    public static void importData(){
        String path = "input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null) {
                getReports(line);
            }
        } catch (IOException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void getReports(String line){
        String[] split = line.split(" ");
        ArrayList<Integer> intReport = new ArrayList<>();
        for(String s : split){
            intReport.add(parseInt(s));
        }
        reports.add(intReport);
    }

    private static boolean checkReport(ArrayList<Integer> report){
        if(isAscending(report)||isDescending(report)){
            for(int i = 0; i < report.size() - 1; i++){
                int absDiff = Math.abs(report.get(i)-report.get(i+1));
                if(absDiff<1||absDiff>3){
                    System.out.println("Bad index: "+ i + 1 + ", "+report.get(i+1));
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private static boolean isAscending(ArrayList<Integer> report){
        for (int i = 0; i < report.size()-1; i++){
            if(report.get(i)>report.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private static boolean isDescending(ArrayList<Integer> report){
        for (int i = 0; i < report.size()-1; i++){
            if(report.get(i)<report.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private static boolean modifyReport(ArrayList<Integer> report){
        for(int i=0; i<report.size(); i++){
            ArrayList<Integer> copy = new ArrayList<>(report);
            copy.remove(i);
            if(checkReport(copy)){
                return true;
            }
        }
        return false;
    }
}
