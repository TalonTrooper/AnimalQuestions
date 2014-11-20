import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalQuestionsGame
{
    private static BufferedReader inputStream;
    private static PrintWriter outputStream;
    private static Scanner mainscan;
    
    
    public static void main(String[] args) throws IOException
    {
        inputStream = new BufferedReader(new FileReader("Animals.txt"));
        outputStream = new PrintWriter(new FileWriter("Animals.txt", true));
        HashMap<Double,String> map = new HashMap<Double,String>();
        ArrayList<Double> array = new ArrayList<Double>();
        mainscan = new Scanner(System.in);
        
        String l = "";
        Scanner scan = new Scanner(l).useDelimiter("  ");
        while ((l = inputStream.readLine()) != null) {
            String n = scan.next();
            double i = Double.parseDouble(scan.next());
            System.out.println(n + ": " + i);
            map.put(i,n);
            array.add(i);
        }
        inputStream.close();
        
        for (Double dee:array) System.out.println(dee);
            
        int low = 0; int high = array.size()-1; int mid = (low+high)/2;
        array = BinarySort.sortDouble(array);
        boolean answered = false;
        while(true) {
            int i = ask(array, map, low, mid, high);
            if (i == 3) {
                System.out.println("FLAGRANT ERROR. TERMINATING. ERROR CODE: MONKEY");
                return;
            }
            if (i == 0) {
                answered = true;
                break;
            }
            if (i == 1) {
                if (mid == high) break;
                low = mid+1;
            }
            if (i == -1) {
                if (mid == low) break;
                high = mid;
            }
            if (i == 2) high--;
            if (i == -2) low++;
            mid = (low+high)/2;
            if (low > high) break;
        }
        if (answered) System.out.println("Ha! I knew it! It's a " + map.get(array.get(mid)) + "!");
        else {
            System.out.print("Oh man! I have no idea what sort of animal this was! Can you tell me?    ");
            String animal = mainscan.next();
            System.out.print("Oh, is that what it is? Well, can you tell me how much this animal usually weighs (in kilograms)?    ");
            double animalWeight = 0;
            boolean numberGot = false;
            while(!numberGot) {
                try {
                    animalWeight = Double.parseDouble(mainscan.next());
                    numberGot = true;
                } catch (NumberFormatException e) {
                    System.out.print("I'm sorry, I don't understand. I need you to tell me the weight of this animal, in kilograms.    ");
                } catch (NullPointerException e) {
                    System.out.print("You didn't type anything! I need to know the weight of this animal - in kilograms.    ");
                }
            }
            System.out.println("Ah, so this animal is a " + animal +", and it weighs " + animalWeight + " kilograms!");
            outputStream.println(animal + "  " + animalWeight);
        }
        outputStream.close();
        System.out.println("That was a fun game! I hope you'll play again some time!");
    }
    
    /*
     * Returns:
     * 1 if the player says the animal is bigger than mid
     * -1 if the player says it is smaller
     * 0 if the player says the animal is mid
     * 2 if the player says the animal is not low
     * -2 if the player says the animal is not high
     */
    private static int ask(ArrayList<Double> array, HashMap<Double, String> map, int low, int mid, int high)
    {
        //If the array is over a certain size, ask if the anaimal is bigger than a certain animal. Bisect array based on this.
        System.out.println(low + " " + mid + " " + high);
        if (high - low > 2) {
            System.out.print("Is your animal bigger than a " +  map.get(array.get(mid)) + "?(y/n)    ");
            while (true) {
                String s = mainscan.next();
                if (s.equals("y")) {return 1;}
                else if (s.equals("n")) {return -1;}
                else System.out.print("I'm sorry, I don't understand. Type 'y' if your animal is bigger than a " + map.get(array.get(mid)) + ", or 'n' if it isn't");
            }
        }
        //If it is small enough, start guessing animals.
        else if (high-low == 2 || high-low == 0) {
            System.out.print("Is your animal a " +  map.get(array.get(low)) + "?(y/n)    ");
            while (true) {
                 String s = mainscan.next();
                 if (s.equals("y")) {return 0;}
                 else if (s.equals("n")) {return 2;}
                 else System.out.print("I'm sorry, I don't understand. Type 'y' if your animal is a " + map.get(array.get(low)) + ", or 'n' if it isn't");
                }
            }
            else if (high-low == 1) {
                System.out.print("Is your animal a " +  map.get(array.get(high)) + "?(y/n)    ");
                while (true) {
                    String s = mainscan.next();
                    if (s.equals("y")) {return 0;}
                    else if (s.equals("n")) {return -2;}
                    else System.out.print("I'm sorry, I don't understand. Type 'y' if your animal is a " + map.get(array.get(high)) + ", or 'n' if it isn't");
                }
            }
        //YOU SHOULD NEVER GET HERE - MONKEY:
        return 3;
    }
}
