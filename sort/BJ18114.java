
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <pre>
 * 문제 : 백준 18114
 * 난이도 : 골드 5
 * 풀이
 * 처음에 고를 수 있는 물건이 2개라 생각하고 풀었는데, 문제를 자세히 읽어보니 3개였다. 그래서 다시 풀었다.
 *
 * 예외 처리가 많았다. 대충 풀고 체점해보니 98퍼에서 멈추길래 이것 저것 넣어 봤는데 2개의 물건을 고를 때, 동일한 물건이 골라지는 예외를 처리하지 않았다.
 * 
 * 대충 입력받은 값을 정렬하고, 처음부터 1개 혹은 2개를 골라, 남은 하나의 숫자에 대한 이분 탐색을 적용하여 존재하는지 여부를 확인하고, 나오는 인덱스의
 * 값이 기존에 고른 숫자와 겹치는지에 대한 확인이 선행되었다.
 * </pre>
 */
public class BJ18114 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());


        List<Integer> value = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            value.add(tmp);
        }
        Collections.sort(value);

        for(int i = 0; i < n; i++) {
            if(value.get(i) == c) {
                System.out.println(1);
                return;
            }

            int tmp = Collections.binarySearch(value, c - value.get(i));
            if(tmp >= 0 && tmp != i) {
                System.out.println(1);
                return;
            }

            for(int j = i + 1; j < n; j++) {
                int a = value.get(i);
                int b = value.get(j);
                int result = Collections.binarySearch(value, c - a - b);
                if(result >= 0 && result != i && result != j) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
        return;
    }

}
