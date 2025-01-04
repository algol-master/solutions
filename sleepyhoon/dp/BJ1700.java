package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1700 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[] arr = new int[k+1];
        Set<Integer> cache = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<k;i++){
            if(cache.contains(arr[i])) continue;

            if(cache.size() < n)
                cache.add(arr[i]);
            else {
                int[] tmp = new int[k+1];
                for(int j=i+1;j<k;j++){
                    if(cache.contains(arr[j]))
                        tmp[arr[j]]++;
                }
                int min = Integer.MAX_VALUE;
                int minValue = 0;
                for (Integer integer : cache) {
                    if(min > tmp[integer]) {
                        min = tmp[integer];
                        minValue = integer;
                    }
                }

                cache.remove(minValue);
                cache.add(arr[i]);
                answer++;
            }
        }

        System.out.println(answer);

    }
}
