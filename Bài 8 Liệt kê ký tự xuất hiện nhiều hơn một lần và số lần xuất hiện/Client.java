import java.util.ArrayList;
import java.util.Collections;
// Đây là cách xử lý để đạt được còn thiếu các bước gửi request lên server và nhận response
class Pair implements Comparable<Pair>{
    protected Character x;
    protected Integer y;

    public Pair(Character x, Integer y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Pair x) {
      return this.y - x.y;
    }  
}
public class Client {
       public static void main(String[] args) {
        String tmp = "dgUOo ch2k22ldsOo";
        int cnt[] = new int[256];
        ArrayList<Pair> a = new ArrayList<>();
        for(char x : tmp.toCharArray()){
            cnt[x]++;
        }
        for(char x : tmp.toCharArray()){
            if(cnt[x] >= 2){
                a.add(new Pair(x, cnt[x]));
                cnt[x] = 0;
            }
        }
        Collections.sort(a);
        String result = "";
        for(Pair x : a){
            result += x.x + ":" + x.y + ",";
        } 
           System.out.println(result);
    }
}
