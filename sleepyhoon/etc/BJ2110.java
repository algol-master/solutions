package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        // 가질 수 있는 최소 거리와 최대 거리를 구해놓는다.
        int min = 1;
        int max = arr[arr.length-1] - arr[0] + 1;

        while(min < max) {
            int mid = (min + max) / 2;

            if(canInstall(mid) < c) {
                max = mid;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(min - 1);
    }

    private static int canInstall(int distance) {
        int count = 1;
        int lastLocate = arr[0];

        for(int i=1;i<arr.length;i++){
            int locate = arr[i];

            if(locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
