import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("172.188.19.218", 1604)){
            String request = "B21DCCN407;rsF5Pqi";
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            output.write(request.getBytes());
            output.flush();
            byte[] buffer = new byte[1024]; 
            int bytesRead = input.read(buffer); 
            String response = new String(buffer, 0, bytesRead); 
            System.out.println(response);
            String arr[] = response.split("\\|");
            int sum = 0;
            for(String x : arr){
                sum += Integer.parseInt(x);
            }
            System.out.println(sum);
            output.write(Integer.toString(sum).getBytes());
            output.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}