//Submission for the Trash Bins problem (Round F)

//Imports for testing
import java.lang.reflect.Array;
import java.util.Arrays;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class TrashBins {
    public static void main(String[] args) {
        Scanner input = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int numOfHouses;
        String houseVals;
        int testCases = input.nextInt();
        int sumWalkDistance;
        int[] houseArr;

        for (int i = 1; i <= testCases; i++) {
            sumWalkDistance = 0;
            numOfHouses = input.nextInt();
            String dummyStr = input.nextLine(); //takes in extra \n char
            houseVals = input.nextLine();
            houseArr = getHouseArr(houseVals);

            //For each house
            for (int j = 0; j < houseVals.length(); j++) {
                if (houseVals.charAt(j) == '0') {
                    sumWalkDistance += findClosestTrashDist(houseArr, j);
                }
            }

            System.out.println("Case #" + i + ": " + sumWalkDistance); //end of code
        }


    }

    public static int[] getHouseArr(String housesStr) {
        int[] houseArr = new int[housesStr.length()];

        for (int i = 0; i < housesStr.length(); i++) {
            if (housesStr.charAt(i) == '1') {
                houseArr[i] = 1;
            } else {
                houseArr[i] = 0;
            }
        }

        return houseArr;
    }

    public static int findClosestTrashDist(int[] houses, int houseNum) {
        int lengthL = houseNum - 1;
        int lengthR = houses.length - (houseNum + 1);
        int[] housesToLeft = new int[lengthL];
        int[] housesToRight = new int[lengthR];
        //Gets houses to the left
        int iterationL = 0;

        if (housesToLeft.length != 0) {
            for (int i = houseNum--; iterationL < housesToLeft.length; i--) {
                housesToLeft[iterationL] = houses[i];
                iterationL++;
            }
        }


        //Gets houses to the right
        int iterationR = 0;

        if (housesToRight.length != 0) {
            for (int j = houseNum + 1; iterationR < housesToRight.length; j++) {
                housesToRight[iterationR] = houses[j];
                iterationR++;
            }
        }

        //finds houses with trash bins to the left
        boolean foundL = false;
        int iterationFL = 0;
        int houseToLeftIdx = 0;
        while (!foundL && iterationFL < housesToLeft.length) {
            if (housesToLeft[iterationFL] == 1) {
                houseToLeftIdx = housesToLeft.length - iterationFL--;
                foundL = true;
            }
            iterationFL++;
        }

        //finds houses with trash bins to the right
        boolean foundR = false;
        int iterationFR = 0;
        int houseToRightIdx = 0;
        while (!foundR && iterationFR < housesToRight.length) {
            if (housesToRight[iterationFR] == 1) {
                houseToRightIdx = iterationFR + 1 + houseNum;
                foundR = true;
            }
            iterationFR++;
        }

        //find closest trash
        int leftTDist = Math.abs(houseNum - houseToLeftIdx);
        int rightTDist = Math.abs(houseToRightIdx - houseNum);

        if (leftTDist > rightTDist) {
            return rightTDist;
        } else if (leftTDist < rightTDist) {
            return leftTDist;
        } else {
            return leftTDist;
        }
    }
}

    /* Dead code
    public int[] housesWithTrash(int[] arr) {
        int[] housesWithTrash = {};
        for (int i = 0; i < housesStr.length(); i++) {

        }
        return housesWithTrash;
    }

    public int[] housesNoTrash(int[] arr) {
        int[] housesNoTrash = {};
        for (int i = 0; i < housesStr.length(); i++) {

        }
        return housesNoTrash;
    }
    */