package sleepyhoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1431 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] list = new String[n];

        for(int i=0;i<n;i++){
            list[i] = br.readLine();
        }

        Arrays.sort(list,(o1,o2) -> {
            if(o1.length() != o2.length())
                return o1.length() - o2.length();
            if(addDigit(o1) != addDigit(o2))
                return addDigit(o1) - addDigit(o2);
            return o1.compareTo(o2);
        });

        for (String s : list) {
            System.out.println(s);
        }
    }

    private static int addDigit(String s) {
        char[] list = s.toCharArray();
        int answer = 0;
        for (char c : list) {
            if(c >= 48 && c<= 57) answer += c -'0';
        }
        return answer;
    }
}
