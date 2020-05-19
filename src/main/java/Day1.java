import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static int requiredFuel(int mass){
        return (int) (Math.floor((mass / 3)) - 2);
    }

    public static List<Integer> readMassValues(String fileLocation) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        String line = bufferedReader.readLine();

        List<Integer> result = new ArrayList<>();

        while(line != null){
            //System.out.println(line);
            result.add(Integer.valueOf(line));
            line = bufferedReader.readLine();
        }

        bufferedReader.close();

        return result;
    }

    public static int calculateFuelRequestedFuel(int massValue){

        int calculateValue = requiredFuel(massValue);
        int nextFuel = requiredFuel(calculateValue);

        while(nextFuel > 0){
            calculateValue += nextFuel;
            nextFuel = requiredFuel(nextFuel);
        }

        return calculateValue;
    }

    public static void main (String [] args) throws IOException {
        System.out.println("Testing the required Fuel");

        int mass = 100756;
        int fuel = requiredFuel(mass);

        System.out.println(String.format("Required fuel is: %s", fuel));

        System.out.println("Testing the read of the file");

        List<Integer> massValues = readMassValues("src\\main\\resources\\MassValues.txt");
        System.out.println(String.format("Size of the list is: %s", massValues.size()));

        System.out.println("Time to calculate the sum");

        List<Integer> resultCalculations = massValues.stream().map(massValue -> requiredFuel(massValue)).collect(Collectors.toList());

        System.out.println(String.format("Here is the final result: %s", resultCalculations.stream().mapToInt(Integer::intValue).sum()));

        System.out.println("Testing the method calculateFuelRequestedFuel");

        System.out.println(String.format("Here is the result: %s", calculateFuelRequestedFuel(mass)));

        List<Integer> finalResult = massValues.stream().map(value -> calculateFuelRequestedFuel(value)).collect(Collectors.toList());

        System.out.println(String.format("Here are the same calculations done on the list: %s", finalResult.toString()));

        System.out.println(String.format("Here is the final result, the sum of all values of the list: %s", finalResult.stream().mapToInt(Integer::intValue).sum()));

    }
}
