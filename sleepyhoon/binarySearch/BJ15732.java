package sleepyhoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15732 {
    static int[][] arr;
    static int n, k;
    static long d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[k][3];

        int left = Integer.MAX_VALUE;
        int right = 0;
        int index = 0;
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            arr[index][0] = from;
            arr[index][1] = to;
            arr[index++][2] = dist;

            left = Math.min(left, from);
            right = Math.max(right, to);
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if (dotori(mid) < d) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    private static long dotori(int mid) {
        long count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (mid >= arr[i][1]) {
                count += 1 + (arr[i][1] - arr[i][0]) / arr[i][2];
            } else if(arr[i][0] <= mid) {
                count += (long) (1 + (double) ((mid - arr[i][0]) / arr[i][2]));
            }
        }

        return count;
    }
}
