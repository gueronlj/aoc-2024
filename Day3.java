import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class Day3 {

    static String data;
    static HashMap<Integer, String> mulMap = new HashMap<>();
    static HashMap<Integer, String> prefixMap = new HashMap<>();
    static ArrayList<String> validMuls = new ArrayList<>();

    public static void main(String[] args) {
        importData();
        mapMuls();
        mapPrefixes();
        //We need to use fancy shmancy Iterator because we were trying to remove entries from a map
        // while looping through said map.. this is bad...
        Iterator<Map.Entry<Integer, String>> iterator = mulMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            int mulStart = entry.getKey();
            //Check backwards from the mul starting point (i), until we hit the closest prefix.
            for (int i = mulStart; i >= 0; i--) {
                if (prefixMap.containsKey(i)) {
                    //remove if closest prefix is "don't()"
                    if (prefixMap.get(i).equals("don't()")) {
                        // Safe removal using iterator
                        iterator.remove();
                    } break;
                }
            }
        }
        calculateTotal();
    }

    static void importData(){
        try {
            String path = "day3-input.txt";
            data = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void mapPrefixes(){
        String a = "don't\\(\\)";
        String b = "do\\(\\)";
        Pattern dont = Pattern.compile(a);
        Pattern doIt = Pattern.compile(b);
        Matcher allDonts = dont.matcher(data);
        Matcher allDos = doIt.matcher(data);

        while(allDos.find()){
            prefixMap.put(allDos.start(), allDos.group());
        }
        while(allDonts.find()){
            prefixMap.put(allDonts.start(), allDonts.group());
        }
    }

    static void mapMuls(){
        //Pattern is "mul( numbers, numbers )"
        String regex = "mul\\(\\d+,\\d+\\)";
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            mulMap.put(matcher.start(), matcher.group());
        }
    }

    static void calculateTotal(){
        int total = 0;
        for (String pair : mulMap.values()) {
            String[] pairOfNumbers = pair.split(",");
            total += multiply(pairOfNumbers);
        }
        System.out.println(total);
    }

    static int multiply(String[] pair){
        ArrayList<Integer> arr = new ArrayList<>();
        int result;
        for (String half : pair){
            //remove all non numbers from string
            String regex = "[^0-9]";
            String number = half.replaceAll(regex, "");
            arr.add(Integer.parseInt(number));
        }
        result = arr.get(0) * arr.get(1);
        return result;
    }
}


