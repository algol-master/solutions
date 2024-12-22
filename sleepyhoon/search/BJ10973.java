package sleepyhoon.search;

import java.util.Scanner;

public class BJ10973 {
    static int[] arr;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        if (hasPreviousPermutation()) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(-1);
        }
    }
    private static boolean hasPreviousPermutation() {
        int pivot = -1;

        for(int i=n-2;i>=0;i--){
            if(arr[i] > arr[i+1]) {
                pivot = i;
                break;
            }
        }

        if(pivot == -1)
            return false;

        int standard = arr[pivot];
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;

        for(int i=pivot+1;i<n;i++){
            if(arr[i] < standard && arr[i] > max) {
                maxIndex = i;
                max = arr[i];
            }
        }
        swap(maxIndex,pivot);
        int i = pivot + 1;
        int j = arr.length - 1;
        while(i<j) swap(i++,j--);
        return true;
    }

    private static void swap(int i1,int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
