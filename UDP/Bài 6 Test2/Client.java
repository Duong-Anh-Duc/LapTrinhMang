
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        String send1 = ";B21DCCN467;tmczgSLy";
        DatagramPacket dp1 = new DatagramPacket(send1.getBytes(), send1.length(), InetAddress.getByName("192.168.1.3"), 2208);
        socket.send(dp1);
        byte[] read = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(read,read.length);
        socket.receive(dp2);
        String res = new String(dp2.getData(), 0, dp2.getLength());
        String arr[] = res.split(";");
        String result = arr[0] + ";";
        int cnt[] = new int[256];
        int max_pos = -1;
        char values = '0';
        for(char x : arr[1].toCharArray()){
            cnt[x]++;
            if(cnt[x] > max_pos){
                max_pos = cnt[x];
            }
        }
        for(char x : arr[1].toCharArray()){
            if(cnt[x] == max_pos){
                values = x;
                break;
            }
        }
        result += values +  ":";
        for(int i = 0 ; i < arr[1].length();i++){
            if(arr[1].charAt(i) == values){
                result += Integer.toString(i + 1);
                result += ",";
            }
        }
        result = result.substring(0, result.length() - 1);
        DatagramPacket dp3 = new DatagramPacket(result.getBytes(), result.length(), InetAddress.getByName("192.168.1.3"), 2208);
        socket.send(dp3);
        DatagramPacket dp4 = new DatagramPacket(read,read.length);
        socket.receive(dp4);
        String status = new String(dp4.getData(), 0, dp4.getLength());
        System.out.println(status);
        socket.close();
    }
}