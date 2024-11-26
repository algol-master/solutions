package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10972 {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if(hasNextPermutation()) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            return;
        }
        System.out.println(-1);
    }

    private static boolean hasNextPermutation() {
        int pivot = 0;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i-1] < nums[i]) {
                pivot = i;
                break;
            }
        }
        if(pivot==0) return false;
        int standard = pivot - 1;

        int changeIndex = 0;
        int min = 10001;
        for(int i=nums.length-1;i>standard;i--){
            if(nums[standard] < nums[i] && nums[i] < min) {
                min = nums[i];
                changeIndex = i;
            }
        }
        swap(changeIndex,standard);

        int i = pivot;
        int j = nums.length - 1;

        while(i<j) {
            swap(i++,j--);
        }
        return true;
    }

    private static void swap(int idx1,int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }
}
