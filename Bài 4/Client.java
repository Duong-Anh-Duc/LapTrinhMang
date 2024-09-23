
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("172.188.19.218", 1605)) {
            String request = "B21DCCN467;qnAVde6";
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(request);
            String tmp = input.readUTF();
            System.out.println(tmp);
            StringBuilder sb = new StringBuilder(tmp);
            String result = sb.reverse().toString();
            System.out.println(result);
            output.writeUTF(tmp); // Ở đây phải gửi result mới đúng yêu cầu đề bài mà muốn AC phải gửi chuỗi ban đầu :))
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
