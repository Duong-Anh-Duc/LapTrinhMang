import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String req = ";B21DCCN239;eIOoH6po";
            socket.send(new DatagramPacket(req.getBytes(), req.getBytes().length, InetAddress.getByName("203.162.10.109"),2208));
            byte []read = new byte[1024];
            DatagramPacket res = new DatagramPacket(read,read.length);
            socket.receive(res);
            String ans = new String(res.getData(), 0, res.getLength());
            String tmp[] = ans.split(";");
            String id = tmp[0];
            String arr[] = tmp[1].split(" ");
            String result = id + ";";
            for(int i = 0 ; i < arr.length;i++){
                result += arr[i].toUpperCase().substring(0, 1) + arr[i].toLowerCase().substring(1);
                if(i != arr.length - 1) result += " ";
            }
            System.out.println(result);
            socket.send(new DatagramPacket(result.getBytes(), result.getBytes().length, InetAddress.getByName("203.162.10.109"),2208));
            socket.close();
        } catch (IOException e) {

        }
    }
}
