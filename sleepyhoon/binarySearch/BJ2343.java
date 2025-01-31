package sleepyhoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2343 {
    static int[] arr;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int left = max;
        int right = sum;

        while (left < right) {
            int mid = (left + right) / 2;

            if (getBlueRay(mid) <= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    private static int getBlueRay(int mid) {
        int count = 1;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > mid) {
                sum = arr[i];
                count++;
            }
        }
        return count;
    }
}
