package sleepyhoon.binarySearch;

import java.util.Scanner;

public class BJ22968 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int last1 = 2;
            int last2 = 1;
            int n = sc.nextInt();
            if(n<3) {
                sb.append(n).append("\n");
            } else {
                int current = 1 + last1 + last2;
                int height = 3;
                while(current <= n) {
                    last2 = last1;
                    last1 = current;
                    current = 1 + last1 + last2;
                    height++;
                }
                sb.append(height-1).append("\n");
            }
        }
        System.out.println(sb);
    }
}
