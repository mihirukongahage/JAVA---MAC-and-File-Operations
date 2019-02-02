import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Program
{
    public static void main(String[] args) throws IOException{

        /* Opening a file or creating a new file if it does not exists */

        File file = new File("f.txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        /* Printing and Writing to a text file; Numbers from 1 to 100 */

        for (int i=1; i<=100; i++)
        {
            System.out.println(i);
            pw.println(i);
        }

        /* Retrieving the MAC address, printing and writing to the file */

        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("MAC address : ");
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());
            pw.println("MAC address : " + sb);

        } catch (Exception e) {

            e.printStackTrace();

        }

        /* Printing and writing current system time to the text file */

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( "System Time : " + sdf.format(cal.getTime()) );
        pw.println("System Time : " + sdf.format(cal.getTime()));

        pw.close();

        /* Reopening the text file for reading; Reading the line which starts with 'S' in the file */
        BufferedReader br = null;

            br = new BufferedReader(new FileReader("f.txt"));
            String line;
            System.out.println("\n");
            System.out.println("Reading the file");

            while((line = br.readLine()) != null)
            {
                if(line.startsWith("System Time"))
                {
                    System.out.println(line);
                }
            }
        
        br.close();
       }
}