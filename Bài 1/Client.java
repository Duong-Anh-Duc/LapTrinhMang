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
            System.out.println("Đã gửi mã sinh viên và mã bài tập tới server.");
            int a = input.readInt();  
            int b = input.readInt(); 
            System.out.println("Nhận 2 số từ server là: a = " + a + ", b = " + b);
            int sum = a + b;  
            int product = a * b;  
            output.writeInt(sum);  
            output.writeInt(product);  
            System.out.println("Gửi tổng và tích trở lại server: sum = " + sum + ", product = " + product);
            input.close();
            output.close();
            socket.close();
            System.out.println("Đã đóng kết nối đến server.");
        } catch (IOException e) {
            System.err.println("Lỗi kết nối hoặc I/O: " + e.getMessage());
        }
    }
}
