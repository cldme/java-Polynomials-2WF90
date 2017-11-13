/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Added a new comment to test the pull system !
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
    public static ArrayList<Integer> Q = new ArrayList<>();
    // Declare map that stores the field elements
    public static Map<Integer, ArrayList<Integer>> fieldElementsMap = new HashMap<>();
    // Declare array for storing prime divisors
    public static ArrayList<Integer> divisors = new ArrayList<>();
    // Declare temp polynomial to be used in various caluclations
    public static ArrayList<Integer> tempPoly = new ArrayList<>();
    // Declare temp map to be used in various calculations
    public static Map<Integer, ArrayList<Integer>> tempMap = new HashMap<>();
    
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
                    System.out.println("Polynomial Addition");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + polyAddSub(P1, P2, '+'));
                    i += 3;
                    break;
                case "Polynomial Subtraction":
                    System.out.println("Polynomial Subtraction");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + polyAddSub(P1, P2, '-'));
                    i += 3;
                    break;
                case "Polynomial Multiplication":
                    System.out.println("Polynomial Multiplication");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + polyMul(P1, P2));
                    i += 3;
                    break;
                case "Polynomial Division":
                    System.out.println("Polynomial Division");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + polyLongDiv(P1, P2));
                    i += 3;
                    break;
                case "Polynomial Scalar Multiple":
                    System.out.println("Scalar Multiple");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + polyMul(P1, P2));
                    i += 3;
                    break;
                case "Extended Euclidean Algorithm":
                    System.out.println("Extended Euclidean Algorithm");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    System.out.println("R:  " + extendedEuclid(P1, P2));
                    i += 3;
                    break;
                case "Congruent Polynomials":
                    System.out.println("Congruent Polynomials");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    Q = readPolynomial(i+4);
                    System.out.println("P1: " + P1);
                    System.out.println("P2: " + P2);
                    tempPoly = polyAddSub(P1, P2, '-');
                    tempMap = polyLongDiv(tempPoly, Q);
                    if(tempMap.get(1).get(0) == 0 && tempMap.get(1).size() == 1)
                        System.out.println("R:  polynomials X, Y are congruent mod Z");
                    else
                        System.out.println("R:  polynomials X, Y are NOT congruent mod Z");
                    i += 4;
                    break;
                case "Generate Field Elements":
                    System.out.println("Field Elements");
                    mod = readModulo(i+1);
                    Q = readPolynomial(i+2);
                    System.out.println("Q:  " + Q);
                    constructField(Q);
                    System.out.println("Elements are: " + fieldElementsMap);
                    i += 2;
                    break;
                case "Field Addition Table":
                    System.out.println("Addition Table");
                    mod = readModulo(i+1);
                    Q = readPolynomial(i+2);
                    constructField(Q);
                    getAdditionTable();
                    i += 2;
                    break;
                case "Field Multiplication Table":
                    System.out.println("Multiplication Table");
                    mod = readModulo(i+1);
                    Q = readPolynomial(i+2);
                    constructField(Q);
                    getMultiplicationTable();
                    i += 2;
                    break;
                case "Field Addition":
                    System.out.println("Field Addition");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    Q = readPolynomial(i+4);
                    //constructField(Q);
                    tempPoly = polyAddSub(P1, P2, '+');
                    // Final result has to be divided by Q (working in field)
                    tempMap = polyLongDiv(tempPoly, Q);
                    System.out.println("R: " + tempMap.get(1));
                    break;
                case "Field Multiplication":
                    System.out.println("Field Multiplication");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    Q = readPolynomial(i+4);
                    //constructField(Q);
                    tempPoly = polyMul(P1, P2);
                    // Final result has to be divided by Q (working in field)
                    tempMap = polyLongDiv(tempPoly, Q);
                    System.out.println("R: " + tempMap.get(1));
                    break;
                case "Field Quotient":
                    System.out.println("Field Quotient");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    P2 = readPolynomial(i+3);
                    Q = readPolynomial(i+4);
                    //constructField(Q);
                    tempMap = extendedEuclid(P2, Q);
                    System.out.println("b^-1: " + tempMap.get(0));
                    tempPoly = polyMul(P1, tempMap.get(0));
                    // Final result has to be divided by Q (working in field)
                    tempMap = polyLongDiv(tempPoly, Q);
                    System.out.println("R: " + tempMap.get(1));
                    break;
                case "Check primitivity":
                    System.out.println("Check primitivity");
                    mod = readModulo(i+1);
                    P1 = readPolynomial(i+2);
                    Q = readPolynomial(i+3);
                    constructField(Q);
                    getDivisors(fieldElementsMap.size() - 1);
                    System.out.println("Prime Divisors: " + divisors + " (q-1 = " + (fieldElementsMap.size() - 1) + ")");
                    System.out.println("Primitivity Check: " + checkPrimitivity(P1) + " (" + P1 +")");
                    break;
                case "Generate Primitive Elements":
                    System.out.println("Generate Primitve Elements");
                    mod = readModulo(i+1);
                    Q = readPolynomial(i+2);
                    constructField(Q);
                    getDivisors(fieldElementsMap.size() - 1);
                    System.out.println("Primitive Elements: " + getPrimitiveElements());
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
        int temp;
        
        // Variable for detecting polynomial
        String poly1 = map.get(i).substring(1, 2);
        // Trim the string from the start of polynomial
        map.put(i, map.get(i).substring(5));
        // Store the coefficients of polynomial in ArrayList X
        for(int j = 0; j < map.get(i).length(); j++) {
            if(map.get(i).charAt(j) >= '0' && map.get(i).charAt(j) <= '9') {
                // Convert coefficient from char to int
                temp = map.get(i).charAt(j) - '0';
                // Mod the coefficients by the prime modulo p
                temp = digitCheck(temp % mod);
                // Add coefficient to the ArrayList X
                X.add(temp);
            } else if(map.get(i).charAt(j) == '-') {
                // Skip the minus sign
                j += 1;
                temp = map.get(i).charAt(j) - '0';
                temp *= -1;
                temp = digitCheck(temp % mod);
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
        int sum, dif, x, y;
        
        for(int i = 0; (i < X.size() || i < Y.size()); i++) {
            
            // Get the coefficients of the two polynomials
            x = (i < X.size()) ? X.get(i) : 0;
            y = (i < Y.size()) ? Y.get(i) : 0;
            
            sum = digitCheck((x + y) % mod);
            dif = digitCheck((x - y) % mod);
            
            // Add the sum of the coefficients to the new ArrayList
            // Or subtract the two coefficients depending on the operation
            if(operation == '+')
                A.add(sum);
            if(operation == '-')
                A.add(dif);
        }
        
        // Remove unused zeros from the end of the array
        A = removeTrailingZeros(A);
        
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
                newCoef = digitCheck((X.get(i) * Y.get(j)) % mod);
                
                //A.add(newDeg, A.get(newDeg) + newCoef);
                A[newDeg] = digitCheck((A[newDeg] + newCoef) % mod);
                
                if(newDeg > maxDeg) maxDeg = newDeg;
            }
        }
        
        for(int i = 0; i <= maxDeg; i++) {
            R.add(i, A[i]);
        }
        
        // Remove unused zeros from the end of the array
        R = removeTrailingZeros(R);
        
        // Return the result from the function call
        return R;
    }
    
    // Function for calculating the long division between two polynomials
    // RETURNS map with: map.get(0) = quotient and map.get(1) = remainder
    public static Map<Integer, ArrayList<Integer>> polyLongDiv(ArrayList<Integer> X, ArrayList<Integer> Y) {
        
        // HashMap for storing the quotient and remainder
        Map<Integer, ArrayList<Integer>> A = new HashMap<>();
        // Variables for the quotient and remainder
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> tempPoly = new ArrayList<>();
        // Other variables
        int tempCoef, tempDeg;
        
        // Initialize the remainder polynomial with X
        r = X;
        
        // While deg(r) >= deg(Y) run through the while loop
        while(r.size() - 1 >= Y.size() - 1 && !(r.size() == 1 && r.get(0) == 0)) {
            
            // Store the new degree in tempDeg
            tempDeg = r.size() - Y.size();
            
            // Store the new leading coefficient in tempCoef
            if(leadingCoef(r) % leadingCoef(Y) != 0)
                // Resulting coefficient is an inverse of 
                // leading coefficient (y) times leading coefficient (r)
                tempCoef = digitCheck((getInverse(leadingCoef(Y)) * leadingCoef(r)) % mod);
            else
                // leading coefficient is just the division of the two coefficients
                tempCoef = digitCheck((leadingCoef(r) / leadingCoef(Y)) % mod);
            
            // Make the polynomial X^(deg(r) - deg(Y)) into an ArrayList
            // We do this to easily work with the functions we already have
            tempPoly = newPolyDeg(tempDeg, tempCoef);
            
            // q = q + lc(r)/lc(Y) * X^(deg(r) - deg(b))
            q = polyAddSub(q, tempPoly, '+');
            // Remove trailing zeros from the array
            q = removeTrailingZeros(q);
            
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
    
    // Function runs Extended Euclidean Algorithm
    public static Map<Integer, ArrayList<Integer>> extendedEuclid(ArrayList<Integer> A, ArrayList<Integer> B){
        
        // HashMap for storing the X and Y (returned by Euclid's Algorithm)
        Map<Integer, ArrayList<Integer>> R = new HashMap<>();
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
        Map<Integer, ArrayList<Integer>> div = new HashMap<>();
        
        // Initialize x, y, v, u
        x.add(1);
        y.add(0);
        v.add(1);
        u.add(0);
        
        while (B.size() > 0 && !(B.size() == 1 && B.get(0) == 0)) {
            
            // Divide the two polynomials
            div = polyLongDiv(A, B);
            
            // Store the quotient in the right variable
            q = div.get(0);
            
            // Follow the Extended Euclid algorithm: A = B
            A = B;
            
            // Store the remainder in the right variable
            B = div.get(1);
            
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
    
    // Function for constructing a field
    // Only needs to be run once
    public static void constructField(ArrayList<Integer> X) {
        // Get the field elements
        
        // Initialize the hashmap for the field elements (new hash map)
        fieldElementsMap.clear();
        
        // P1.size()-1 is the deg of the polynomial
        // tempPoly is just an empty polynomial of deg P1.size()-1
        tempPoly = nullPoly(X.size()-1);
        // Call function to generate the field elements
        generateFieldElements(X.size()-2, tempPoly);
    }
    
    // Function returns field elements
    public static void generateFieldElements(int deg, ArrayList<Integer> X) {
        // Generate field elements
        if(deg == -1) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0; i < X.size(); i++) {
                temp.add(X.get(i));
            }
            fieldElementsMap.put(fieldElementsMap.size(), temp);
        } else {
            for(int i = 0; i < mod; i++) {
                X.set(deg, i);
                generateFieldElements(deg - 1, X);
            }
        }
    }
    
    // Function prints to console (maybe to file) the addition table of the field
    public static void getAdditionTable() {
        // Declare variable for temp calculations 
        ArrayList<Integer> tempPoly = new ArrayList<>();
        // Generate the addition table
        
        // Get the number of elements in the field
        int length = fieldElementsMap.size();
        
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                tempPoly = polyAddSub(fieldElementsMap.get(i), fieldElementsMap.get(j), '+');
                System.out.print(tempPoly + " ");
            }
            System.out.println("");
        }
    }
    
    // Function prints to console (maybe to file) the multiplication table of the field
    public static void getMultiplicationTable() {
        // Declare variable for temp calculations 
        ArrayList<Integer> tempPoly = new ArrayList<>();
        Map<Integer, ArrayList<Integer>> divisionMap = new HashMap<>();
        // Generate the addition table
        
        // Get the number of elements in the field
        int length = fieldElementsMap.size();
        
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                tempPoly = polyMul(fieldElementsMap.get(i), fieldElementsMap.get(j));
                divisionMap = polyLongDiv(tempPoly, Q);
                // Print the residue of the division (as this is the result)
                System.out.print(divisionMap.get(1) + " ");
            }
            System.out.println("");
        }
    }
    
    // Function returns whether or not element is primitive (true/false)
    public static boolean checkPrimitivity(ArrayList<Integer> X) {
        // Declare working variables
        ArrayList<Integer> tempPoly = new ArrayList<>();
        int i = 0, q;
        // Initialize tempPoly with 1
        tempPoly.add(1);
        // Get order of field
        q = fieldElementsMap.size();
        q -= 1;
        
        for(i = 0; i < divisors.size(); i++) {
            int pow = q / divisors.get(i);
            for(int j = 0; j < pow; j++) {
                // Calculate X^divisors.get(i)
                tempPoly = polyMul(X, tempPoly);
                // Working in a field (taking mod Q)
                tempPoly = polyLongDiv(tempPoly, Q).get(1);
            }
            // X is not a primitive element
            if(tempPoly.size() == 1 && tempPoly.get(0) == 1)
                return false;
        }
        // X is a primitive element
        return true;
    }
    
    // Function for generating all primitive elements inside a field
    public static Map<Integer, ArrayList<Integer>> getPrimitiveElements() {
        // Declare variables to be used inside function
        Map<Integer, ArrayList<Integer>> tempMap = new HashMap<>();
        ArrayList<Integer> tempPoly = new ArrayList<>();
        int j = 0;
        
        for(int i = 1; i < fieldElementsMap.size(); i++) {
            tempPoly = fieldElementsMap.get(i);
            if(checkPrimitivity(tempPoly)) {
                tempMap.put(j++, tempPoly);
            }
        }
        
        return tempMap;
    }
    
    // Function returns the leading coefficient of a polynomial
    public static int leadingCoef(ArrayList<Integer> X) {
        return X.get(X.size() - 1);
    }
    
    // Function returns an array list that represents the poly (coef * X^deg)
    public static ArrayList newPolyDeg(int deg, int coef) {
        
        ArrayList<Integer> X = new ArrayList<>();
        for(int i = 0; i < deg; i++)
            X.add(0);
        
        X.add(coef);
        
        // Return the result from the function call
        // i.e: ArrayList filled with zeros, except the coefficient for X^deg
        return X;
    }
    
    // Function returns the inverse of a number (with prime modulo p)
    public static int getInverse(int x) {
        // Find the inverse
        for (int i = 0; i < mod; i++) {
            if(((x * i) % mod) == 1)
                // Returns the inverse of x from function call
                return i;
        }
        // Return -1 (should never happen since we always have an inverse)
        // IMPORTANT: p has to be a prime modulo
        return -1;
    }
    
    // Function that converts -x (mod p) to y (mod p) (so from negative to positive)
    public static int digitCheck(int x) {
        // Add the mod while x < 0
        while(x < 0) {
            x += mod;
        }
        
        // Return result from function call
        return x;
    }
    
    public static ArrayList removeTrailingZeros(ArrayList<Integer> X) {
        
        // Remove trailing zeros from the array list
        while(X.size() > 0 && X.get(X.size() - 1) == 0) {
            if(X.get(X.size() - 1) == 0)
               X.remove(X.size() - 1);
        }
        
        // If X.size() == 0 then add one zero
        if(X.size() == 0) X.add(0);
        
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
    
    // Function return a null polynomial of deg n
    public static ArrayList<Integer> nullPoly(int deg) {
        // Declare ArrayList to store the polynomial
        ArrayList<Integer> tempPoly = new ArrayList<>();
        
        for(int i = 0; i < deg; i++) {
            tempPoly.add(0);
        }
        
        // Return result from function call
        return tempPoly;
    }
    
    // Function for generating all prime divisors of X
    public static void getDivisors(int x) {
        // Calculate all prime divisors of x
        for(int i = 2; i*i <= x; i++) {
            if(x % i == 0) {
                while(x % i == 0) {
                    x /= i;
                }
                divisors.add(i);
            }
        }
        
        if(x != 1)
            divisors.add(x);
        
        // ArrayList divisors now contains all prime divisors of x
    }
}

// Branch in sync with origin/master !
