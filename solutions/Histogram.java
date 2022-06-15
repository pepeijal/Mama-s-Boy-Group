/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.util.ArrayList;
public class Histogram {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        for (int a = 0 ; a < cases ; a++){

            int amount = in.nextInt();
            int intervals = in.nextInt();
            int max = 0;
            int min = 0;


            ArrayList<Integer> temp = new ArrayList<>(amount);
            for (int i = 0 ; i < amount; i++){
                int value = in.nextInt();
                temp.add(value);

                if (i == 0) {
                    max = min = value;
                }

                if (value > max) {
                    max =  value;
                }
                if (temp.get(i) < min) {
                    min = value;
                }
            }
            ArrayList<Integer>[] lists = new ArrayList[intervals];
            for (int i = 0; i < intervals; i++) {
                lists[i] = new ArrayList<>();
            }


            ArrayList<Integer> cutoffs = new ArrayList<>();
            int middleValueOfCutOffs = (max - min) / intervals;
            for (int i = 0; i <= intervals; i++) {
                int cutOffValue = min + middleValueOfCutOffs * i;
                cutoffs.add(cutOffValue);

            }



            for(int i = 0; i < temp.size(); i++){
                int index = 0;
                int indexIdentifier = binarySearch(cutoffs, temp.get(i) );
                if(indexIdentifier < 0 ){
                    index = Math.abs(indexIdentifier) - 1;
                }
                else if(indexIdentifier >= intervals) {
                    index = indexIdentifier - 1;
                }else{
                    index = indexIdentifier;
                }

                lists[index].add(temp.get(i));
            }
            


            for (int i = 0; i <= intervals; i++) {
                System.out.print(cutoffs.get(i) + " ");

            }
            System.out.println();

            for (int i = 0; i < intervals; i++) {
                System.out.print(lists[i].size() + " ");

            }
            System.out.println();





        }
    }
    /** Use binary search to find the key in the list */
    public static int binarySearch(ArrayList<Integer> list, int key) {
        int low = 0;
        int high = list.size() - 1;

        while (high >= low) {
            int mid = (low + high) / 2;
            if (key < list.get(mid))
                high = mid - 1;
            else if (key == list.get(mid))
                return mid;
            else
                low = mid + 1;
        }

        return -low; // Now high < low
    }

}