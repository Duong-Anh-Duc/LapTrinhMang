import java.util.ArrayList;
import java.util.Collections;
public class Client {   
       public static void main(String[] args) {
        String input = "2,15,4,3,6,8,10,7,1";
        ArrayList<Integer> chan = new ArrayList<>();
        ArrayList<Integer> le = new ArrayList<>();
        String tmp[] = input.split(",");
        for(String x : tmp){
            int y = Integer.parseInt(x);
            if(y % 2 == 0){
                chan.add(y);
            }
            else{
                le.add(y);
            }
        }
           Collections.sort(chan);
           Collections.sort(le);
           String x1 = "[";
         for(int i = 0 ; i < chan.size();i++){
             if(i != chan.size() - 1){
                 x1 += Integer.toString(chan.get(i)) + ", ";
             }
             else{
                 x1 += Integer.toString(chan.get(i)) + "]";
             }
         }
        x1 += ";[";
        for(int i = 0 ; i < le.size();i++){
            if(i != le.size() - 1){
                 x1 += Integer.toString(le.get(i)) + ", ";
             }
             else{
                 x1 += Integer.toString(le.get(i)) + "]";
             }
        }
           System.out.println(x1);
    }
}
