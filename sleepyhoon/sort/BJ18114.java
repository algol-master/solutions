package sleepyhoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ18114 {
    static int n;
    static int c;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == c) {
                System.out.println(1);
                return;
            }
        }
        Arrays.sort(arr);
        int i=0;
        int j=n-1;
        int weight;
        while(i<j) {
            weight = arr[i] + arr[j];
            if(weight == c) {
                System.out.println(1);
                return;
            }
            if(weight > c) {
                j--;
            }
            else {
                for(int k=i+1;k<j;k++){
                    if(arr[k] == c - weight) {
                        System.out.println(1);
                        return;
                    }
                }
                i++;
            }
        }
        System.out.println(0);
    }
}
