package sleepyhoon.hash;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1885 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        // 1~k까지 방문했는지 여부를 생각해야 한다.
        boolean[] visit = new boolean[k + 1];
        int answer = 1;

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            visit[arr[i]] = true;
            int count = 0;
            for (int j = 1; j < k+1; j++) {
                if (visit[j]) count++;
            }
            if (count == k) {
                answer++;
                Arrays.fill(visit, false);
            }
        }

        System.out.println(answer);
    }
}