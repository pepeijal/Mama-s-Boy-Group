/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDs;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Ideapad 3
 */
public class Payment {
    public static void main(String[] args){

        ArrayList<IDs> ids = new ArrayList<>();
        PriorityQueue<IDs> que = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<IDs> between = new ArrayList<>();
        long miliseconds = 1000;
        long time = System.currentTimeMillis();
        long currenttime = 0;

        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\Ideapad 3\\Downloads\\DS-Assignment-2022-main\\DS-Assignment-2022-main\\tasks\\payment\\cases\\1.txt"));
            while(in.hasNextLine()){
                long noww = System.currentTimeMillis();
                String line = in.nextLine();
                if(line.equals("CLEAR")){
                    String out = in.nextLine();
                    String[] split = out.split(" ");
                    System.out.print("\n\nOUTPUT CLEAR :");
                    print10(split);
                    System.out.println("\n\n");
                    System.out.print("QUE "+que);

                    que.clear();
                    System.out.println(que);
                }else if(line.equals("END")){
                    long end = System.currentTimeMillis();
                    long total = end-time;
                    System.out.println(total+" "+ids.get(0).getTime());
                    return;
                }
                else if(currenttime >= miliseconds){
                    miliseconds += 1000;
                    System.out.print("\nOUTPUT : ");
                    int numb = 0;
                    for(IDs x : between){
                        if(numb%5==0 && numb !=0)
                            System.out.println();
                        x.print();
                        numb++;
                    }
                    between.clear();

                    System.out.println("\n\nQUE: "+que);
                }

                else {
                    long before = System.currentTimeMillis();
                    Thread.sleep(10);
                    long after = System.currentTimeMillis();
                    long timebet = after-before;
                    currenttime += timebet + (after - noww);
                    addTime(ids,timebet);
                    between.add(new IDs(line));
                    ids.add(new IDs(line));
                    que.add(new IDs(line));
                    System.out.println("INPUT  :" + line);
                }
            }

            in.close();

        }catch (Exception e){
            System.out.println("Not Found");
        }
    }

    public static void print10(String[] x){
        for(int i = 0;i < x.length;i++){
            if(i%9==0 && i!=0){
                System.out.println();
            }
            System.out.print(x[i]+" ");
        }
    }

    public static void addTime(ArrayList<IDs> x,long z){
        for(IDs y : x){
            y.plusTime(z);
        }
    }
}
