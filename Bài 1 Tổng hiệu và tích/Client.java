import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("172.188.19.218", 1605)) {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
            String request = "B21DCCN239;M8rAT4P";  
            output.writeUTF(request);  
            output.flush();
            int a = input.readInt();  
            int b = input.readInt();    
            output.writeInt(a + b);
            output.writeInt(a - b);
            output.writeInt(a * b);  
            output.flush();
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Lỗi kết nối hoặc I/O: " + e.getMessage());
        }
    }
}
