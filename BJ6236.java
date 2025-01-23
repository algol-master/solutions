import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ6236 {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = arr[arr.length - 1];
        int right = 10000;

        while (left < right) {
            int mid = (left + right) / 2;

            if (isAnswer(arr, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    private static boolean isAnswer(int[] arr, int mid) {
        int current = mid;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr.length - i <= m) {
                count++;
                continue;
            }
            if (current < arr[i]) {
                current = mid;
                count++;
            } else {
                current -= arr[i];
            }
        }

        if(count == m)
            return true;
        else
            return false;
    }


}
