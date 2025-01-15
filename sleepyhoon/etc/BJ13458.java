package sleepyhoon.etc;

import java.util.Scanner;

public class BJ13458 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        int b = sc.nextInt();
        int c = sc.nextInt();

        long answer = 0;

        for(int i=0;i<n;i++){
            if(b >=arr[i])
                answer+=1;
            else {
                answer += (long) (1 + Math.ceil((double) (arr[i]-b)/c));
            }
        }

        System.out.println(answer);
    }
}
