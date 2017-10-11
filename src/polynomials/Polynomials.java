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
            System.out.println("polyAddition: " + polyAddSub(P1, P2, '+'));
            
            System.out.println();
            System.out.println("polyMul: " + polyMul(P1, P2));
            
            System.out.println();
            // HashMap for storing the quotient and remainder
            // The name mapDivision can be changed to whatever variable name suits better
            Map<Integer, ArrayList<Integer>> mapDivision = new HashMap<Integer, ArrayList<Integer>>();
            // Calculate the long division and store the results inside the hash map
            mapDivision = longDiv(P1, P2);
            // Now,the quotient and remainder can be accessed individually
            System.out.println("longDiv Quotient: " + mapDivision.get(0));
            System.out.println("longDiv Remainder: " + mapDivision.get(1));
            
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
            
            // Make the polynomial X^(deg(r) - deg(Y)) into an ArrayList
            // We do this to easily work with the functions we already have
            tempPoly = newPolyDeg(tempDeg, tempCoef);
            
            // q = q + lc(r)/lc(Y) * X^(deg(r) - deg(b))
            q = polyAddSub(q, tempPoly, '+');
            
            // r = r - lc(r)/lc(Y) * X^(deg(r) - deg(b)) * Y
            r = polyAddSub(r, polyMul(tempPoly, Y) , '-');
            
            while(r.get(r.size() - 1) == 0) {
                r.remove(r.size() - 1);
            }
        }
        
        // Result from this function is saved in the hashMap A
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
    
    public static ArrayList<Integer> polyMod(ArrayList<Integer> X,int x){
        
        for(int coefficient:X){
            coefficient = coefficient%x;
        }
        return X;
        
    }
    
    public static ArrayList<Integer> euclid(ArrayList<Integer> A, ArrayList<Integer> B){
        
        ArrayList<Integer> remainder =  new ArrayList<>();
        
        while(B.get(0)!=0){
            remainder = longDiv(A,B).get(1);
            A = (ArrayList<Integer>) B.clone();
            B = (ArrayList<Integer>) remainder.clone();
        }
        
        return A;
    }
    
    public static ArrayList<ArrayList<Integer>> extendedEuclid(ArrayList<Integer> A, ArrayList<Integer> B){
        
        ArrayList<Integer> x =  new ArrayList<>();
        ArrayList<Integer> y =  new ArrayList<>();
        ArrayList<Integer> u =  new ArrayList<>();
        ArrayList<Integer> v =  new ArrayList<>();
        ArrayList<Integer> q =  new ArrayList<>();
        ArrayList<Integer> r =  new ArrayList<>();
        ArrayList<Integer> x2 =  new ArrayList<>();
        ArrayList<Integer> y2 =  new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while(B.get(0)!=0){
            q = longDiv(A,B).get(0);
            r = longDiv(A,B).get(1);
            A = (ArrayList<Integer>) B.clone();
            B = (ArrayList<Integer>) r.clone();
            x2 = (ArrayList<Integer>) x.clone();
            y2 = (ArrayList<Integer>) y.clone();
            x = (ArrayList<Integer>) u.clone();
            y = (ArrayList<Integer>) v.clone();
            u = polyAddSub(x2,polyMul(q,u),'-');
            v = polyAddSub(y2,polyMul(q,v),'+');
            
        }
        result.add(x);
        result.add(y);
        return result;
    }
    
    public static boolean equalityCheck(ArrayList<Integer> A, ArrayList<Integer> B,ArrayList<Integer> mod,int prime){
        
        return polyMod(longDiv(A,mod).get(1),p).equals(polyMod(longDiv(B,mod).get(0),p));
    }
    
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
    
}
