import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static int ucln(int a, int b){
        if(b == 0) return a;
        return ucln(b, a % b);
    }
    public static int bcnn(int a, int b){
        return a / ucln(a, b) * b;
    }
    public static void main(String[] args){
        try(Socket socket = new Socket("172.188.19.218", 1605)){
            String request = "B21DCCN791;vcKcJdN";

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(request);
            output.flush();
            int a = input.readInt();
            int b = input.readInt();
            System.out.println(a + " " + b);
            System.out.println(ucln(a, b));
            System.out.println(bcnn(a, b));
            output.writeInt(ucln(a, b));
            output.writeInt(bcnn(a, b));
            output.flush();
            input.close();
            output.close();
            socket.close();
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
          
    }
}
