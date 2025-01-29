package sleepyhoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6236 {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,arr[i]);
            sum += arr[i];
        }

        int left = max;
        int right = sum;

        while (left < right) {
            int mid = (left + right) / 2;

            if (isAnswer(arr, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    private static boolean isAnswer(int[] arr, int mid) {
        int current = mid;
        int count = 1;
        for (int x : arr) {
            if (current < x) {
                current = mid;
                current -= x;
                count++;
            } else {
                current -= x;
            }
        }

        return count <= m;
    }


}
