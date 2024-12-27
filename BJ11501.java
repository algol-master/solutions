import java.util.Scanner;

public class BJ11501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0;i<n;i++){
            int loop = sc.nextInt();
            int[] arr = new int[loop];
            long answer = 0;
            int max = 0;
            int maxIndex = 0;
            for(int j=0;j<loop;j++){
                arr[j] = sc.nextInt();
                if(max < arr[j]) {
                    max = arr[j];
                    maxIndex = j;
                }
            }

            if (maxIndex == 0) {
                System.out.println(0);
                continue;
            }

            int start = 0;

            while(start < loop) {
                if(maxIndex == start) {
                    start++;
                    max = -1;
                    maxIndex = -1;
                    // 새로 maxIndex 구해야한다.
                    for(int k=start;k<loop;k++){
                        if(max < arr[k]) {
                            max = arr[k];
                            maxIndex = k;
                        }
                    }
                    continue;
                }
                answer += arr[maxIndex] - arr[start++];
            }

            System.out.println(answer);
        }
    }
}
