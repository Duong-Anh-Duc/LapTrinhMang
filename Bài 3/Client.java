
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("172.188.19.218", 1606)) {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = "B21DCCN239;TzbXxBH";
            output.write(request);
            output.newLine();
            output.flush();
            String response = input.readLine().toLowerCase();
            System.out.println(response);
            String help = "ueoai";
            String result = "";
            for(int i = 0 ; i < response.length();i++){
                boolean check = false;
                for(int j = 0 ; j < help.length();j++){
                    if(response.charAt(i) == help.charAt(j)){
                        check = true;
                        break;
                    }
                }
                if(!check) result += response.charAt(i);
               
            }
            System.out.println(result);
            output.write(result);
            output.newLine();
            output.flush();
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
