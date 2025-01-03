package sleepyhoon.greedy;

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

        int[] arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = getAnswer(k, arr, n);
        System.out.println(answer);
    }

    private static int getAnswer(int k, int[] arr, int n) {
        // 원소 값
        Set<Integer> cache = new HashSet<>();
        int answer = 0;
        for(int i=0;i<k;i++){
            if(cache.contains(arr[i])) continue;

            if(cache.size() < n) {
                cache.add(arr[i]);
            } else {
                // 인덱스와 값을 따로 처리해야 한다. map을 사용하지 않고 인덱스를 사용해버렸다.
                int changeIndex = -1;
                int changeValue = -1;
                for(int value : cache) {
                    int index = 101;
                    for(int j=i+1;j<k;j++){
                        if(arr[j] == value) {
                            index = j;
                            break;
                        }
                    }

                    if(changeIndex < index) {
                        changeIndex = index;
                        changeValue = value;
                    }
                }
                cache.remove(changeValue);
                cache.add(arr[i]);
                answer++;
            }
        }
        return answer;
    }
}
