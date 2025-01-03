package sleepyhoon.dp;

import java.util.Scanner;

public class BJ11054 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            dp1[i] = 1;
            dp2[i] = 1;
        }

        for(int i=0;i<n;i++){
            int pivot = arr[i];
            for(int j=0;j<=i;j++){
                if(arr[j] < pivot) {
                    dp1[i] = Math.max(dp1[j]+1,dp1[i]);
                }
            }
        }

        for(int i=n-1;i>=0;i--){
            int pivot = arr[i];
            for(int j=n-1;j>=i;j--){
                if(arr[j] < pivot) {
                    dp2[i] = Math.max(dp2[j]+1,dp2[i]);
                }
            }
        }
        int max = 0;
        for(int i=0;i<n;i++){
            max = Math.max(max,dp1[i] + dp2[i]);
        }
        System.out.println(max-1);
    }
}
