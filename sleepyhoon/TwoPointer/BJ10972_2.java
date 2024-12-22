package sleepyhoon.TwoPointer;

import java.util.Scanner;

public class BJ10972_2 {
    static int[] arr;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        if (hasNextPermutation()) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(-1);
        }
    }
    private static boolean hasNextPermutation() {
        int pivot = -100;
        for(int i=n-1;i>=1;i--) {
            if(arr[i-1] < arr[i]) {
                pivot = i-1;
                break;
            }
        }
        if(pivot==-100)
            return false;
        else {
            int standard = arr[pivot];
            int min = Integer.MAX_VALUE;
            int minIndex = arr.length-1;
            for(int i=n-1;i>pivot;i--) {
                if(standard < arr[i] && arr[i] < min) {
                    min = arr[i];
                    minIndex = i;
                }
            }
            swap(pivot,minIndex);
            int i = pivot + 1;
            int j = arr.length - 1;
            while(i<j) swap(i++,j--);
            return true;
        }
    }

    private static void swap(int i1,int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
