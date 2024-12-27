package sleepyhoon.greedy;

import java.util.Scanner;

public class BJ19939 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int r = sc.nextInt();

        int sum =(r*(r+1))/2;

        if (n < sum) {
            System.out.println(-1);
        } else {
            int value = n - sum;
            if (value % r == 0) {
                System.out.println(r-1);
            } else
            {
                System.out.println(r);
            }
        }
    }
}
