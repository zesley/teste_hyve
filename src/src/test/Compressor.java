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
        checkSub(sub, result, data);
        result.add(0);
        result.add(i);
      } else {
        sub.add(i);
      }
    }
    checkSub(sub, result, data);
    return result;
  }

  private static void checkSub(List<Integer> sub, List<Integer> result, List<Integer> data) {
    if (sub.size() > 0) {
      List<Integer> lasts = new ArrayList<>();
      while(sub.size() > 0) {
        for(int index = 0; index < sub.size(); index++) {
          if (lasts.isEmpty()) {
            lasts.add(sub.remove(index));
          } else {
            int indexLast = result.indexOf(lasts.get(0));
            if (result.get(indexLast + lasts.size()) == sub.get(index)) {
              lasts.add(sub.get(index));
            } else {
              for (Integer last : lasts)
                sub.remove(last);
              result.add(result.size() - indexLast - 1);
              result.add(lasts.size());
              lasts.clear();
              if (sub.size() > 0)
                lasts.add(sub.get(0));
            }
          }
        }
      }
      if (lasts.size() > 0) {
        int indexLast = result.indexOf(lasts.get(0));
        result.add(result.size() - indexLast);
        result.add(lasts.size());
      }
    }
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
