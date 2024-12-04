package sleepyhoon.backtracking;
import java.util.Scanner;

public class BJ9663 {
    static int[] arr;
    static int n;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        nQueen(0);
        System.out.println(answer);
    }

    private static void nQueen(int depth) {
        if(depth == n) {
            answer++;
        }
        else {
            for(int i=0;i<n;i++){
                arr[depth] = i;
                if(check(depth)) {
                    nQueen(depth+1);
                }
            }
        }
    }

    private static boolean check(int depth) {
        for(int i=0;i<depth;i++){
            if(arr[i] == arr[depth])
                return false;

            if(Math.abs(i-depth) == Math.abs(arr[i]-arr[depth]))
                return false;
        }
        return true;
    }

}
