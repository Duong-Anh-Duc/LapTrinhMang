import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String send1 = ";B21DCCN239;uWKK8u3W";
        DatagramPacket dp1 = new DatagramPacket(send1.getBytes(), send1.length(), InetAddress.getByName("192.168.1.3"), 2207);
        socket.send(dp1);
        byte []read = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(read,  read.length);
        socket.receive(dp2);
        String res = new String(dp2.getData(), 0, dp2.getLength());
        System.out.println(res);
        
       String arr[] = res.split("[\\s;]+");
       String id = arr[0];
       int n = Integer.parseInt(arr[1]);
       int cnt[] = new int[100001];
       for(int i = 2 ; i < arr.length;i++){
           cnt[Integer.parseInt(arr[i])] = 1;
       }
       String result = id + ";";
       for(int i = 1 ; i <= n;i++){
           if(cnt[i] == 0){
               result += Integer.toString(i);
               result += " ";
           }
       }
       result = result.substring(0, result.length() - 1);
       DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), InetAddress.getByName("192.168.1.3"), 2207);
       socket.send(dp3);
       DatagramPacket dp4 = new DatagramPacket(read,  read.length);
       socket.receive(dp4);
       String status = new String(dp4.getData(), 0, dp4.getLength());
       System.out.println(status);
        socket.close();
    }
    
}
