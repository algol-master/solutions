package sleepyhoon.twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2531 {
    static int n, d, k, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] count = new int[d+1];
        int distinct = 0;
        int answer = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        for (int i = 0; i < k; i++) {
            if (count[arr[i]] == 0) {
                distinct++;
            }
            count[arr[i]]++;
        }

        if (count[c] == 0) {
            answer = distinct + 1;
        } else {
            answer = distinct;
        }

        for (int i = 0; i < n; i++) {
            int left = arr[i];
            count[left]--;
            if (count[left] == 0) {
                distinct--;
            }

            int right = arr[(i + k) % n];
            if (count[right] == 0) {
                distinct++;
            }
            count[right]++;

            int tmp;
            if (count[c] == 0) {
                tmp = distinct + 1;
            } else {
                tmp = distinct;
            }
            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);
    }
}
