import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String studentCode = "B21DCCN467";
        String qCode = "cBYXAPm";
        try(  Socket socket = new Socket("172.188.19.218", 1604)) {
            OutputStream outputStream = socket.getOutputStream();
            String request = studentCode + ";" + qCode;
            outputStream.write(request.getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024]; 
            int bytesRead = inputStream.read(buffer); 
            String response = new String(buffer, 0, bytesRead); 
            System.out.println("Dữ liệu nhận từ server: " + response);
            String[] parts = response.split("\\|");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            long result = (long) Math.pow(a, b);
            System.out.println("Kết quả a^b: " + result);
            outputStream.write(Long.toString(result).getBytes());
            outputStream.flush();
            socket.close();
            System.out.println("Đã gửi kết quả và đóng kết nối.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
