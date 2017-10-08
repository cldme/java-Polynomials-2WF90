/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomials;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author s168309
 */
public class Polynomials {

    /**
     * @param args the command line arguments
     */
    
    // Variable for the prime modulus
    public static int p;
    // Array lists for the polynomials
    public static ArrayList<Integer> P1 = new ArrayList<>();
    public static ArrayList<Integer> P2 = new ArrayList<>();
    
    // Variable for storing each line of text from the input file
    // Will be using a hash map (line number, string)
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Read input file and process the file
        parseInput();
        
        for(int i = 0; i < map.size(); i++) {
            
            // Variable for detecting polynomial 1
            String poly1 = map.get(i).substring(0, 10);
            // Trim the string from the start of polynomial 1
            map.put(i, map.get(i).substring(14));
            // Degree of polynomial is length of the string coefficients / 2
            // Variable for storing degree of polynomial 1
            int deg1 = map.get(i).length() / 2;
            // Store the coefficients of polynomial 1 in ArrayList P1
            for(int j = 0; j < map.get(i).length(); j++) {
                if(map.get(i).charAt(j) >= '0' && map.get(i).charAt(j) <= '9') {
                    // Convert coefficient from char to int
                    int temp = map.get(i).charAt(j) - '0';
                    // Add coefficient to the ArrayList P1
                    P1.add(temp);
                }
            }
            
            // Variable for detecting polynomial 2
            String poly2 = map.get(i+1).substring(0, 10);
            // Trim the string from the start of polynomial 2
            map.put(i+1, map.get(i+1).substring(14));
            // Degree of polynomial is length of the string coefficients / 2
            // Variable for storing degree of polynomial 2
            int deg2 = map.get(i+1).length() / 2;
            // Store the coefficients of polynomial 2 in ArrayList P2
            for(int j = 0; j < map.get(i+1).length(); j++) {
                if(map.get(i+1).charAt(j) >= '0' && map.get(i+1).charAt(j) <= '9') {
                    // Convert coefficient from char to int
                    int temp = map.get(i+1).charAt(j) - '0';
                    // Add coefficient to the ArrayList P1
                    P2.add(temp);
                }
            }
            
            // polyAddition(P1, P2)
            // polySubtraction(P1, P2)
            // polyMultiply(P1, P2)
            // polyScalarMultiple(P1, P2)
            
            System.out.println("poly1: " + poly1);
            System.out.println("coefficients 1: " + map.get(i));
            System.out.println("deg1: " + deg1);
            System.out.println("ArrayList P1: " + P1);
            
            System.out.println("poly2: " + poly2);
            System.out.println("coefficients 2: " + map.get(i+1));
            System.out.println("deg2: " + deg2);
            System.out.println("ArrayList P2: " + P2);
            
            System.out.println();
            System.out.println("polyAddition: " + polyAddition(P1, P2));
            
            // Skip to the next operation that needs to be computed
            i += 3;
        }
    }
    
    public static void parseInput() {
        
        // Name of the input file i.e: "example.txt"
        String inputFile = "input.txt";
        
        // Used for reading one line at a time from the buffer
        String line = "";
        
        // Variable for storing the index of the lines in the input file
        int i = 0;
        
        try {
            
            // Declare a new FileReader for the inputFile
            FileReader fileReader = new FileReader(inputFile);

            // We wrap the FileReader in a BufferedReader for later reading line by line
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Cycle through all the lines in the inputFile
            while((line = bufferedReader.readLine()) != null) {

                // Used for debugging to print the input file to the console
                //System.out.println(line);

                // Trim extra whitespaces from the beginning/end of the string
                line = line.trim();

                // Logic for reading from the input file
                // Depends on the format of the file (might change in the future)
                // IMPORTANT: Stores all the lines that are not comments (do not start with # symbol)
                if(line.length() > 0 && line.charAt(0) != '#') {    
                    
                    // Add the new line to the map
                    map.put(i, line);
                    
                    // Increse the value of i (to keep track of the number of lines)
                    i += 1;
                }
                
            }

            // Close the inputFile
            bufferedReader.close();

        } catch(FileNotFoundException e) {

            // Catch the FileNotFoundException and inform user
            System.out.println("File " + inputFile + " was not found.");

            // Give the user a small hint as to what might have gone wrong
            System.out.println("Check if the input file is placed in the same directory and please name it example.txt");
            System.exit(0);

        } catch(IOException e) {

            // Catch the IOException and inform user
            System.out.println("There was an error while reading the " + inputFile + " file.");
        }
    }
    
    // Function for calculating the sum of two polynomials
    public static ArrayList<Integer> polyAddition(ArrayList<Integer> X, ArrayList<Integer> Y) {
        
        // Declare variables to be used by the function
        ArrayList<Integer> A = new ArrayList<>(); 
        int i, j, x, y;
        
        // Variable to store the index for X
        i = X.size() - 1;
        // Variable to store the index for Y
        j = Y.size() - 1;
        
        for(int k = 0; (k < X.size() || k < Y.size()); k++) {
            
            // Get the coefficients of the two polynomials
            x = (i >= 0) ? X.get(i) : 0;
            y = (j >= 0) ? Y.get(j) : 0;
            
            // Add the sum of the coefficients to the new ArrayList
            // This will be the result of the function (but in reverse order)
            A.add(x+y);
            
            // Update the two pointers i, j
            i -= 1;
            j -= 1;
        }
        
        // Reverse the order of the coefficients in A
        Collections.reverse(A);
        // Return the result from the function call
        return A;
    }
}
