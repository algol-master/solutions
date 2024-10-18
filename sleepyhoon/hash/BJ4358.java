package sleepyhoon.hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ4358 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        double count = 0;
        String s;
        while (true) {
            s = br.readLine();
            if(s == null || s.isEmpty()) break;
            map.put(s,map.getOrDefault(s,0) + 1);
            if(!list.contains(s)) list.add(s);
            count++;
        }

        // 사전순으로 나열해야 한다.
        Collections.sort(list);

        for(String a:list) {
            double value = (double) map.get(a) / count * 100;
            System.out.println(a + " " + String.format("%.4f",value));
        }
    }
}
