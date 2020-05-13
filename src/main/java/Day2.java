package src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day2 {

    public static List<Integer> processOpcode (List<Integer> opcode, List<Integer> originalList){

        int firstCommand = opcode.get(0);

        switch (firstCommand) {
            case 1:
                originalList.set(opcode.get(3), originalList.get(opcode.get(1)) + originalList.get(opcode.get(2)));
                break;

            case 2:
                originalList.set(opcode.get(3), originalList.get(opcode.get(1)) * originalList.get(opcode.get(2)));
                break;

            default:
                break;
        }

        return originalList;
    }

    public static List<Integer> createOpcode (int indexToStartFrom, List<Integer> originalList){

        if(indexToStartFrom >= originalList.size()){
            return Collections.emptyList();
        }

        else if(originalList.size() - indexToStartFrom <= 4){
            return originalList.subList(indexToStartFrom, originalList.size() - 1);
        }

        else {
            return originalList.subList(indexToStartFrom, indexToStartFrom + 4);
        }

    }

    public static List<Integer> intCode (List<Integer> listOfValues){

        int nextIndexForOpcode = 0;
        List<Integer> opcode = createOpcode(nextIndexForOpcode, listOfValues);

        while(!opcode.isEmpty()){
            processOpcode(opcode, listOfValues);
            nextIndexForOpcode += 4;
            opcode = createOpcode(nextIndexForOpcode, listOfValues);
        }

        return listOfValues;
    }

    public static List<Integer> readInputFile(String fileLocation) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));

        String line = bufferedReader.readLine();

        bufferedReader.close();

        return new ArrayList<>(Arrays.asList(line.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }

    public static void main (String [] args) throws IOException {

        System.out.println("Let's do some testing with only one opcode");

        //List<Integer> originalList = Arrays.asList(new Integer[] { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 });
        List<Integer> originalList = Arrays.asList(new Integer[] {1,1,1,4,99,5,6,0,99});
        //List<Integer> firstOpcode = Arrays.asList(new Integer[] {1, 9, 10, 3});

        //System.out.println(String.format("Here is the result: %s", processOpcode(firstOpcode, originalList)));

        System.out.println(String.format("Here is the final result: %s", intCode(originalList)));

        System.out.println("Calculating");

        List<Integer> inputFileValues = readInputFile("/Users/joanamagalhaes/Documents/MD_Joana/Git/playground/AdventOfCode/InputFiles/OpcodeInput.txt");

        List<Integer> finalResult = intCode(inputFileValues);

        System.out.println(String.format("Here is the final result: %s", finalResult.toString()));

    }

}
