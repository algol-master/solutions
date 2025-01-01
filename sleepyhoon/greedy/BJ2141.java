package sleepyhoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ2141 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n][2];
        long sum = 0;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long location = Long.parseLong(st.nextToken());
            long mans = Long.parseLong(st.nextToken());

            arr[i][0] = location;
            arr[i][1] = mans;
            sum += mans;
        }

        Arrays.sort(arr, Comparator.comparingLong(a -> a[0]));
        int i = 0;
        long mid;
        if(sum%2==1)
            mid = (sum+1)/2;
        else
            mid = sum/2;
        long count = 0;
        while(count < mid) {
            count += arr[i++][1];
        }
        System.out.println(arr[i-1][0]);
    }
}
