package javaapplication6;
import UDP.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
public class Client {
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        // gửi dữ msv và mã 
        DatagramSocket socket = new DatagramSocket();
        String send1 = ";B21DCCN239;3pQoQBrX";
        DatagramPacket dp1 = new DatagramPacket(send1.getBytes(), send1.length(), InetAddress.getByName("203.162.10.109"), 2209);
        socket.send(dp1);
        // Đọc dữ liệu từ server gửi về 
        byte read[] = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(read, read.length);
        socket.receive(dp2);
        String id = new String(dp2.getData(), 0, 8);
        System.out.println(id);
        ByteArrayInputStream bais = new ByteArrayInputStream(dp2.getData(), 8, dp2.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product product = (Product) ois.readObject();
        System.out.println(product);
        // Xử lý java CO
        String arr[] = product.getName().split(" ");
        String name = "";
        name += arr[arr.length - 1] + " ";
        for(int i = 1 ; i < arr.length - 1;i++){
            name += arr[i] + " ";
        }
        name += arr[0];
        product.setName(name);
        StringBuilder sb = new StringBuilder(Integer.toString(product.getQuantity()));
        product.setQuantity(Integer.parseInt(sb.reverse().toString()));
        // Xử lý dữ liệu để gửi lên server
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(product);
        oos.flush();
        byte Id[] = id.getBytes();
        byte productData[] = baos.toByteArray();
        byte sendData[] = new byte[Id.length + productData.length];
        System.arraycopy(Id, 0, sendData, 0, Id.length);
        System.arraycopy(productData, 0, sendData, Id.length, productData.length);
        DatagramPacket dp3 = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("203.162.10.109"), 2209);
        socket.send(dp3);
        socket.close();
    }
}
