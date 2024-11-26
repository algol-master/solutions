package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(combination(start, end));
        }
    }

    private static BigInteger combination(int start, int end) {
        System.out.println(fibo(end));
        System.out.println(fibo(end - start));
        System.out.println(fibo(start));
        return fibo(end).divide(fibo(end-start).multiply(fibo(start)));
    }

    // 30! 의 경우 long 타입 이상이다.
    private static BigInteger fibo(int num) {
        if(num==0 || num == 1)
            return BigInteger.ONE;
        return BigInteger.valueOf(num).multiply(fibo(num-1));
    }
}
