package Main;
import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        try{
            DatagramSocket socket = new DatagramSocket();
            String res = ";B21DCCN239;3pQoQBrX";
            DatagramPacket send1 = new DatagramPacket(res.getBytes(), res.getBytes().length, InetAddress.getByName("203.162.10.109"), 2209);
            socket.send(send1);
            byte[] data = new byte[1024];
            DatagramPacket receive = new DatagramPacket(data, data.length);
            socket.receive(receive);
            String Id = new String(receive.getData(), 0, 8);
            System.out.println(Id);
            ByteArrayInputStream bais = new ByteArrayInputStream(receive.getData(), 8, receive.getLength() - 8);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Product product = (Product) ois.readObject();
            ois.close();
            System.out.println(product);
            String arr[] = product.getName().split(" ");
            String name = "";
            name += arr[arr.length - 1] + " ";
            for(int i = 1; i < arr.length - 1;i++){
                name += arr[i];
                if(i != 0) name += " ";
            }
            name += arr[0];
            product.setName(name);
            StringBuilder sb = new StringBuilder(Integer.toString(product.getQuantity()));
            product.setQuantity(Integer.parseInt(sb.reverse().toString()));
            System.out.println(product);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();
            byte[] productData = baos.toByteArray();
            byte[] sendData = new byte[8 + productData.length];
            System.arraycopy(Id.getBytes(), 0, sendData, 0, 8);
            System.arraycopy(productData, 0, sendData, 8, productData.length);
            DatagramPacket send2 = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2209);
            socket.send(send2);
            socket.close();
        }
        catch(IOException e){
    }
    }
}