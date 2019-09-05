package src.test;

import java.util.ArrayList;
import java.util.List;

public class Compressor {
  public static List<Integer> compress(boolean isTrivial, List<Integer> data) {
    List<Integer> result = new ArrayList<>();
    if (isTrivial) {
      for (Integer i : data) {
        result.add(0);
        result.add(i);
      }
      return result;
    }

    List<Integer> sub = new ArrayList<>();
    for (Integer i : data) {
      if (!result.contains(i)) {
        result.add(0);
        result.add(i);
      } else {
        sub.add(i);
      }
    }
    if (sub.size() > 0) {
      List<Integer> lasts = new ArrayList<>();
      while(sub.size() > 0) {
        for(int index = 0; index < sub.size(); index++) {
          if (lasts.isEmpty()) {
            lasts.add(sub.remove(index));
          } else {
            int indexLast = result.indexOf(lasts.get(0));
            int indexActual = result.indexOf(sub.get(index));
            if (indexLast + lasts.size() == indexActual) {
              lasts.add(sub.get(index));
            } else {
              sub.removeAll(lasts);
              result.add(indexLast);
              result.add(lasts.size());
              lasts.clear();
              lasts.add(sub.get(index));
            }
          }
        }
        int indexLast = result.indexOf(lasts.get(0));
        result.add(indexLast + 1);
        result.add(lasts.size());
      }
    }
    return result;
  }

  public static List<Integer> uncompress(List<Integer> bytes) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < bytes.size(); i+=2) {
      if (bytes.get(i) == 0) {
        result.add(bytes.get(i + 1));
      } else {
        int begin = result.size() - bytes.get(i);
        int end = begin + bytes.get(i + 1);
        if (begin >= 0) {
          result.addAll(result.subList(begin, end));
        } else {
          result.add(0x3F);
        }
      }
    }
    return result;
  }
}
