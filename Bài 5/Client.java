import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("172.188.19.218", 1606)){
            String request = "B21DCCN467;mHBlrUe";
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.write(request);
            output.newLine();
            output.flush();
            String tmp[] = input.readLine().split(",\\s*");
            for(int i = 0 ; i < tmp.length;i++){
               System.out.println(tmp[i]);
            }
            System.out.println();
            ArrayList<String> a = new ArrayList<>();
            for(int i = 0 ; i < tmp.length;i++){
                if(tmp[i].endsWith(".edu")){
                    a.add(tmp[i]);
                }
            }
            String result = ""; // dùng cách hơi dài dòng có thể sử dụng join chuỗi lại
            for(int i = 0 ; i < a.size();i++){
                if(i != a.size() - 1){
                    result += a.get(i);
                    result += ", ";
                }
                else{
                    result += a.get(i);
                }
            }
            System.out.println(result);
            if(a.size() > 0) {
            output.write(result);
            output.newLine();
            output.flush();
            }
            socket.close();
        } catch (IOException e) {
        }
    }
}
