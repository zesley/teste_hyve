package src.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    boolean isTrivial = false;
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet())
      if (envName.equals("USE_TRIVIAL_IMPLEMENTATION") && env.get(envName) == "1")
        isTrivial = true;

    List<Integer> bytes = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;
    try {
      while ((s = br.readLine()) != null) {
        String[] b = s.split(" ");
        for (String x : b)
          if (x != null && !x.isEmpty()) {
            bytes.add(Integer.parseInt(x, 16));
            // System.out.print(x);
          }
        if (bytes.size() > 0)
          break;
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // System.out.println("\nIN");
    // for (Integer i : bytes)
    // System.out.print(i);

    List<Integer> result = Compressor.uncompress(bytes);

    // System.out.println("\nExtract");
    // for (Integer i : result)
    // System.out.print(i);

    result = Compressor.compress(isTrivial, result);

    // System.out.println("\nCOMPRESSED");
    // for (Integer i : result)
    // System.out.print(i);

    // System.out.println("\nFinal Result");
    for (Integer i : result)
      System.out.print(String.format("%02X ", i));
  }
}
