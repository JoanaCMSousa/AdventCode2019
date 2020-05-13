package src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day3 {

    public static class Coordinates {
        private Integer x;
        private Integer y;


        public Coordinates(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Coordinates)) {
                return false;
            }
            Coordinates that = (Coordinates) o;
            return Objects.equals(getX(), that.getX()) &&
                   Objects.equals(getY(), that.getY());
        }

        @Override public int hashCode() {
            return Objects.hash(getX(), getY());
        }
    }

    public static class Wire {

        private Coordinates coordinates;
        private List<Coordinates> path;

        public Wire(Integer x, Integer y){
            coordinates = new Coordinates(x, y);
            path = new ArrayList<>();
        }

        public void readInstruction(String instruction){

            String direction = instruction.substring(0,1);
            Integer movement = Integer.parseInt(instruction.substring(1));

            switch (direction) {
                case "U":
                    coordinates.setY(coordinates.getY() + movement);
                    break;
                case "D":
                    coordinates.setY(coordinates.getY() - movement);
                    break;
                case "R":
                    coordinates.setX(coordinates.getX() + movement);
                    break;
                case "L":
                    coordinates.setX(coordinates.getX() - movement);
            }
        }

        public void readAllInstructions (List<String> instructions){

            for(int i = 0; i < instructions.size(); i++){
                readInstruction(instructions.get(i));
                path.add(new Coordinates(coordinates.getX(), coordinates.getY()));
            }

        }

        public List<Coordinates> getPath(){
            return path;
        }
    }


    public static List<Coordinates> calculateIntersections(List<Coordinates> pathOne, List<Coordinates> pathTwo){

        List<Coordinates> intersections = new ArrayList<>();

        for(int i = 0; i < pathOne.size(); i++){
            if(pathTwo.contains(pathOne.get(i)))
                intersections.add(pathOne.get(i));
        }

        return intersections;
    }

    public static List<List<String>> readCoordinates(String fileLocation) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        String line = bufferedReader.readLine();

        List<List<String>> result = new ArrayList<>();

        while(line != null){
            //System.out.println(line);
            result.add(new ArrayList<String>(Arrays.asList(line.split(",")).stream().collect(Collectors.toList())));
            line = bufferedReader.readLine();
        }

        bufferedReader.close();

        return result;
    }

    public static void main (String [] args) throws IOException {

        System.out.println("U12".substring(0,1));

//        List<String> firstInstructions = new ArrayList<>(Arrays.asList(new String[] {"R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"}));
//        List<String> secondInstructions = new ArrayList<>(Arrays.asList(new String [] {"U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"}));

        List<List<String>> wiresCoordinates = readCoordinates("/Users/joanamagalhaes/Documents/MD_Joana/Git/playground/AdventOfCode/InputFiles/WiresCoordinates.txt");

        Wire firstWire = new Wire(0, 0);
        Wire secondWire = new Wire(0, 0);

        firstWire.readAllInstructions(wiresCoordinates.get(0));
        secondWire.readAllInstructions(wiresCoordinates.get(1));

        List<Coordinates> intersections = calculateIntersections(firstWire.getPath(), secondWire.getPath());

//        System.out.println("Testing read of file");
//        System.out.println(readCoordinates("/Users/joanamagalhaes/Documents/MD_Joana/Git/playground/AdventOfCode/InputFiles/WiresCoordinates.txt"));

        System.out.println(firstWire.getPath().toString());
        System.out.println(secondWire.getPath().toString());
        System.out.println(intersections.toString());
    }

}
