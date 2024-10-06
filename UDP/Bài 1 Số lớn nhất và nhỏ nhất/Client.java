 import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String message = ";" + "B21DCCN239" + ";" + "H1zoBJMK";
            socket.send(new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName("203.162.10.109"), 2207));
            byte[] data = new byte[1024];
            DatagramPacket res = new DatagramPacket(data, data.length);
            socket.receive(res);
            String ans = new String(res.getData(), 0, res.getLength());
            String arr[] = ans.split(";");
          
            ArrayList<Integer> a = new ArrayList<>();
            String result = arr[0] + ";";
            String ok[] = arr[1].split(",");
            for(int i = 1 ; i < ok.length;i++){
                a.add(Integer.parseInt(ok[i]));
            }
            Collections.sort(a);
            result += Integer.toString(a.get(a.size() - 2));
            result += ",";
            result += Integer.toString(a.get(1));
            System.out.println(result);
            socket.send(new DatagramPacket(result.getBytes(), result.getBytes().length, InetAddress.getByName("203.162.10.109"), 2207));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
