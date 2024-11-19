package sleepyhoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        long answer = 0;
        for(int i=0;i<n;i++){
            if(arr[i] > 0) break;
            int left = i+1;
            int right = arr.length - 1;
            while(left<right) {
                int current = arr[i] + arr[left] + arr[right];
                if(current == 0) {
                    if(arr[left] == arr[right]) {
                        answer += combination(right-left+1);
                        break;
                    }
                    int l = 1;
                    int r = 1;
                    while(arr[left] == arr[left+1]) {
                        l++;
                        left++;
                    }
                    while(arr[right] == arr[right-1]) {
                        r++;
                        right--;
                    }
                    answer += (long) l *r;
                }
                if(current > 0) right--;
                else left++;
            }
        }

        System.out.println(answer);
    }

    // nC2
    private static long combination(int n) {
        return (long) n * (n-1) / 2;
    }
}
