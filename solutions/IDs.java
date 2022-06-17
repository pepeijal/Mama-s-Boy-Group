/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectDs;

/**
 *
 * @author Ideapad 3
 */
public class IDs implements Comparable<IDs>{

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
