package src.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Main {
  public static void main (String [] args) {
    try {
      File fIn = new File("/media/dados/Downloads/input.bin");
      byte[] bytes = new byte[(int) fIn.length()];
      DataInputStream dis = new DataInputStream(new FileInputStream(fIn));
      dis.readFully(bytes);
      dis.close();
      byte[] out = new byte[bytes.length];
      for (int i = 0; i < bytes.length; i++) {
        out[i] = (byte)(bytes[i] + 1);
        System.out.println(String.format("%02X %02X", bytes[i], out[i]));
      }
      File fOut = new File("/media/dados/Downloads/output.bin");
      if (fOut.exists())
        fOut.delete();
      DataOutputStream dos = new DataOutputStream(new FileOutputStream(fOut));
      dos.write(out, 0, bytes.length);
      dos.flush();
      dos.close();

      File fCheck = new File("/media/dados/Downloads/output.bin");
      bytes = new byte[(int) fCheck.length()];
      dis = new DataInputStream(new FileInputStream(fCheck));
      dis.readFully(bytes);
      dis.close();
      for (int i = 0; i < bytes.length; i++) {
        System.out.println(String.format("%02X", bytes[i]));
      }

      Scanner s = new Scanner(System.in);
      //while (s.hasNextByte()) {
      while (s.hasNext()) {
        //byte b = s.nextByte();
        System.out.println(String.format("%02X", Byte.decode(s.next())));
      }
      s.close();
      System.out.println("finished");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
      System.exit(-1);
    }
  }
}
