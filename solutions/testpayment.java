
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class testpayment {
    
    public class Payment {
    public static void main(String[] args){

        ArrayList<IDs> ids = new ArrayList<>();
        PriorityQueue<IDs> que = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<IDs> between = new ArrayList<>();
        long miliseconds = 1000;
        long time = System.currentTimeMillis();
        long currenttime = 0;

        try{
            Scanner in = new Scanner(new FileInputStream("C:\\Users\\Asus\\0.txt"));
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

    
}
class IDs implements Comparable<IDs>{

    private String id;
    private String id1;
    private String prio;
    private long time;

    public IDs(String x){
        String[] split = x.split(" ");
        this.id = split[0];
        this.id1 = split[1];
        this.prio = split[2];
        switch(this.prio){
            case "PLATINUM":{
                this.time = 3000;
                break;
            }
            case "GOLD":{
                this.time=2000;
                break;
            }
            case "SILVER":{
                this.time=1000;
                break;
            }
            case "BRONZE":{
                this.time=0;
                break;
            }

        }
    }

    public long getTime() {
        return time;
    }

    public void plusTime(long x) {
        this.time += x;
    }

    @Override
    public String toString() {
        return "("+id1+" "+prio+") --> ";
    }

    @Override
    public int compareTo(IDs o) {
        if(this.time>o.time){
            return 1;
        }else if(this.time < o.time){
            return -1;
        }else
        return 0;
    }

    public void print(){
        System.out.print(this.id1+" ");
    }
}
