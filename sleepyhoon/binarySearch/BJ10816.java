package sleepyhoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10816 {
    static int[] a,b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);

        for(int i=0;i<m;i++) {
            sb.append(upperBound(b[i]) - lowerBound(b[i])).append(" ");
        }
        System.out.println(sb);
    }

    private static int lowerBound(int target) {
        int left = 0;
        int right = a.length;

        while(left < right) {
            int mid = left + ((right - left) / 2);

            if(a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int upperBound(int target) {
        int left = 0;
        int right = a.length;

        while(left < right) {
            int mid = left + ((right - left) / 2);

            if(a[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
