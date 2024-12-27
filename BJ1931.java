import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            arr[i][0] = first;
            arr[i][1] = second;
        }

        Arrays.sort(arr,(o1,o2) -> {
            if(o1[1] == o2[1])
                return o1[0] - o2[0];
            else
                return o1[1] - o2[1];
        });

        int start = arr[0][1];
        int count = 1;
        for(int i=1;i<n;i++){
            if(start <= arr[i][0]) {
                start = arr[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}
