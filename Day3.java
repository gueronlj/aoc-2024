import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    static String data;
    static ArrayList<String> validChunks = new ArrayList<>();

    public static void main(String[] args) {
        importData();
        parsePattern();
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

    static void parsePattern(){
        //Pattern is "mul( numbers, numbers )"
        String regex = "mul\\(\\d+,\\d+\\)";
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            validChunks.add(matcher.group());
        }
    }

    static void calculateTotal(){
        int total = 0;
        for (String chunk : validChunks){
            String[] half = new String[2];
            half = chunk.split(",");
            //sanitize nums[0] and nums[1]
            total += multiply(half);
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
        result = arr.get(0)*arr.get(1);
        return result;
    }
}


