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
    public static int mod;
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
            
            // Variable for detecting the operation
            String operation = map.get(i);
            
            switch(operation) {
                case "Polynomial Addition":
                    System.out.println("Addition");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Polynomial Subtraction":
                    System.out.println("Subtraction");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Polynomial Multiplication":
                    System.out.println("Multiplication");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Polynomial Division":
                    System.out.println("Division");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Polynomial Scalar Multiple":
                    System.out.println("Scalar Multiple");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Extended Euclidean Algorithm":
                    System.out.println("Extended Euclidean Algorithm");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 3;
                    break;
                case "Congruent Polynomials":
                    System.out.println("Congruent Polynomials");
                    P1 = readPolynomial(i+1);
                    P2 = readPolynomial(i+2);
                    mod = readModulo(i+4);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    i += 4;
                    break;
            }
        }
    }
    
    // Function that reads the input file (line by line)
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
    
    // Function that reads a polynomial (given an input line i)
    public static ArrayList<Integer> readPolynomial(int i) {
        
        ArrayList<Integer> X = new ArrayList<>();
        
        // Variable for detecting polynomial
        String poly1 = map.get(i).substring(1, 2);
        // Trim the string from the start of polynomial
        map.put(i, map.get(i).substring(5));
        // Store the coefficients of polynomial in ArrayList X
        for(int j = 0; j < map.get(i).length(); j++) {
            if(map.get(i).charAt(j) >= '0' && map.get(i).charAt(j) <= '9') {
                // Convert coefficient from char to int
                int temp = map.get(i).charAt(j) - '0';
                // Add coefficient to the ArrayList X
                X.add(temp);
            } else if(map.get(i).charAt(j) == '-') {
                // Skip the minus sign
                j += 1;
                int temp = map.get(i).charAt(j) - '0';
                // Add coefficient to the ArrayList X
                X.add(temp);
            }
        }
        
        // Return result from function call
        return X;
    }
    
    // Function that reads the modulo
    public static int readModulo(int i) {
        // Variable for storing the modulo
        int p;
        // Variable for detecting the [P]:
        String modName = map.get(i).substring(1, 2);
        // Trim the string from the start of the modulus
        map.put(i, map.get(i).substring(5));
        p = Integer.parseInt(map.get(i));
        
        // Return result from function call
        return p;
    }
    
    // Function for calculating the addition / subtraction of two polynomials
    public static ArrayList<Integer> polyAddSub(ArrayList<Integer> X, ArrayList<Integer> Y, char operation) {
        
        // Declare variables to be used by the function
        ArrayList<Integer> A = new ArrayList<>(); 
        int x, y;
        
        for(int i = 0; (i < X.size() || i < Y.size()); i++) {
            
            // Get the coefficients of the two polynomials
            x = (i < X.size()) ? X.get(i) : 0;
            y = (i < Y.size()) ? Y.get(i) : 0;
            
            // Add the sum of the coefficients to the new ArrayList
            // Or subtract the two coefficients depending on the operation
            if(operation == '+')
                A.add(x+y);
            if(operation == '-')
                A.add(x-y);
        }
        
        // Return the result from the function call
        return A;
    }
    
    // Function for calculating the multiplication of two polynomials
    public static ArrayList<Integer> polyMul(ArrayList<Integer> X, ArrayList<Integer> Y) {
        
        int A[] = new int[1000];
        ArrayList<Integer> R = new ArrayList<>();
        int newDeg, newCoef, maxDeg = 0;
        
        for(int i = 0; i < X.size(); i++) {
            for(int j = 0; j < Y.size(); j++) {
                newDeg = i + j;
                newCoef = X.get(i) * Y.get(j);
                
                //A.add(newDeg, A.get(newDeg) + newCoef);
                A[newDeg] = A[newDeg] + newCoef;
                
                if(newDeg > maxDeg) maxDeg = newDeg;
            }
        }
        
        for(int i = 0; i <= maxDeg; i++) {
            R.add(i, A[i]);
        }
        
        // Return the result from the function call
        return R;
    }
    
    // Function for calculating the long division between two polynomials
    // RETURNS map with: map.get(0) = quotient and map.get(1) = remainder
    public static Map<Integer, ArrayList<Integer>> longDiv(ArrayList<Integer> X, ArrayList<Integer> Y) {
        
        // HashMap for storing the quotient and remainder
        Map<Integer, ArrayList<Integer>> A = new HashMap<Integer, ArrayList<Integer>>();
        // Variables for the quotient and remainder
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> tempPoly = new ArrayList<>();
        // Other variables
        int tempCoef, tempDeg;
        
        // Initialize the remainder polynomial with X
        r = X;
        
        // While deg(r) >= deg(Y) run through the while loop
        while(r.size() - 1 >= Y.size() - 1) {
            // Store the new leading coefficient in tempCoef
            tempCoef = leadingCoef(r) / leadingCoef(Y);
            // Store the new degree in tempDeg
            tempDeg = r.size() - Y.size();
            
            // The resulting coefficient is in Q, long division not possible !?
            if(tempCoef == 0) {
                return nullHashMap();
            }
            
            // Make the polynomial X^(deg(r) - deg(Y)) into an ArrayList
            // We do this to easily work with the functions we already have
            tempPoly = newPolyDeg(tempDeg, tempCoef);
            
            // q = q + lc(r)/lc(Y) * X^(deg(r) - deg(b))
            q = polyAddSub(q, tempPoly, '+');
            
            // r = r - lc(r)/lc(Y) * X^(deg(r) - deg(b)) * Y
            r = polyAddSub(r, polyMul(tempPoly, Y) , '-');
            
            // Remove trailing zeros from the array
            r = removeTrailingZeros(r);
        }
        
        // Result from this function is saved in the hashMap
        A.put(0, q);
        A.put(1, r);
        
        // Return the result from the function call
        return A;
    }
    
    // Function that return the leading coefficient of a polynomial
    public static int leadingCoef(ArrayList<Integer> X) {
        return X.get(X.size() - 1);
    }
    
    // Function return an array list that represents the poly (coef * X^deg)
    public static ArrayList newPolyDeg(int deg, int coef) {
        
        ArrayList<Integer> X = new ArrayList<>();
        for(int i = 0; i < deg; i++)
            X.add(0);
        
        X.add(coef);
        
        // Return the result from the function call
        // i.e: ArrayList filled with zeros, except the coefficient for X^deg
        return X;
    }
    
    public static ArrayList removeTrailingZeros(ArrayList<Integer> X) {
        
        // Remove trailing zeros from the array list
        while(X.size() > 0 && X.get(X.size() - 1) == 0) {
            if(X.get(X.size() - 1) == 0)
               X.remove(X.size() - 1);
        }
        
        // Return the result from the function call
        return X;
    }
    
    // Function that returns the coefficients of a polynomial mod a prime
    public static ArrayList polyMod(ArrayList<Integer> X, int p) {
        
        // Calculate the polynomial modulo a prime
        for(int i = 0; i < X.size(); i++) {
            X.set(i, X.get(i) % p);
        }
        
        // Return the result from the function call
        return X;
    }
    
    public static Map<Integer, ArrayList<Integer>> extendedEuclid(ArrayList<Integer> A, ArrayList<Integer> B){
        
        // HashMap for storing the X and Y (returned by Euclid's Algorithm)
        Map<Integer, ArrayList<Integer>> R = new HashMap<Integer, ArrayList<Integer>>();
        // Declare the arrays used for the function
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<Integer> x1 = new ArrayList<>();
        ArrayList<Integer> y1 = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();
        ArrayList<Integer> u = new ArrayList<>();
        
        // Declare arrays for storing the quotient and remainder after a long division
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        // Declare map for storing the result of the long division
        Map<Integer, ArrayList<Integer>> div = new HashMap<Integer, ArrayList<Integer>>();
        
        // Initialize x, y, v, u
        x.add(1);
        y.add(0);
        v.add(1);
        u.add(0);
        
        while (B.size() > 0) {
            
            // Divide the two polynomials
            div = longDiv(A, B);
            
            // Store the quotient in the right variable
            q = div.get(0);
            q = removeTrailingZeros(q);
            
            // Follow the Extended Euclid algorithm: A = B
            A = B;
            
            // Store the remainder in the right variable
            B = div.get(1);
            B = removeTrailingZeros(B);
            
            // Should div.get(0) = -1 and div.get(1) = -1 then long division failed
            if(q.get(0) == -1 && B.get(0) == -1) {
                return nullHashMap();
            }
            
            // Store values for x and y
            x1 = x;
            y1 = y;
            
            // Store new values for x and y
            x = u;
            y = v;
            
            // Calculate new values for u and v
            u = polyAddSub(x1, polyMul(q, u), '-');
            v = polyAddSub(y1, polyMul(q, v), '-');
        }
        
        // Result from this function is saved in the hashMap
        R.put(0, x);
        R.put(1, y);
        
        // Return the result from the function call
        // The map stores both X and Y (can be accessed by indices 0 and 1)
        return R;
    }
    
    // Function that returns a HashMap with -1,-1
    // Used when long division fails
    public static Map<Integer, ArrayList<Integer>> nullHashMap() {
        // Declare used variables and arrays
        Map<Integer, ArrayList<Integer>> NULL = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> tempPoly = new ArrayList<>();
        // Configure values that will be returned in case long division fails
        tempPoly.add(-1);
        NULL.put(0, tempPoly);
        NULL.put(1, tempPoly);
        
        // Return value from function call
        return NULL;
    }
    
    /*
    
    public static ArrayList<Integer> fieldAdd(ArrayList<Integer> A, ArrayList<Integer> B,ArrayList<Integer> mod,int prime){
        ArrayList<Integer> result = new ArrayList<>();
        result = polyAddSub(A,B,'+');
        result = polyMod(result,p);
        //to be done
        //result = fieldDivision(result,mod,p);
        return result;
    }
    
    public static ArrayList<Integer> fieldMul(ArrayList<Integer> A, ArrayList<Integer> B,ArrayList<Integer> mod,int prime){
        ArrayList<Integer> result = new ArrayList<>();
        result = polyMul(A,B);
        result = polyMod(result,p);
        //to be done
        //result = fieldDivision(result,mod,p);
        return result;
    }

    */
}

// NOW there should only be one branch in this repo !
// Now, Netbeans is tracking master/origin