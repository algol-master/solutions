
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <pre>
 * 문제 : 백준 2036
 * 난이도 : 골드 5
 * 풀이
 * 받은 값들에 대하여, 0 또는 0보다 작은 값들에 대한 리스트와, 0보다 큰 값들에 대한 리스트로 나누었다.
 * 이후, 작은 값들의 리스트에 대해, 가장 작은 값 2개(절대값으로 - 가장 큰 값)를 곱하는 경우 가장 큰 수가 나올테고, 
 * 0이 포함되어 있으면, 음수의 경우 곱해주면 음수에서 0이 되므로 이 역시도 최적의 경우이다.
 * 
 * 양수에 대한 리스트의 경우, 가장 큰 값 끼리 곱하여 결과에 더해주고, 가장 마지막으로 남은 숫자가 1개라면 그냥 더해준다. 
 * 다만, 1의 경우 곱하는 것 보다 더하는게 이득이므로, 모든 경우에 대해, 1인 경우 곱하는 대신 각각 더해주도록 하였다.
 * </pre>
 */
public class BJ2036 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Long> minus = new ArrayList<>();
        List<Long> plus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long tmp = Long.parseLong(br.readLine());
            if (tmp <= 0L) {
                minus.add(tmp);
            } else {
                plus.add(tmp);
            }
        }

        Collections.sort(minus);
        plus.sort(Collections.reverseOrder());

        long result = 0;

        int index = 0;
        while (index + 1 < minus.size()) {
            long a = minus.get(index++);
            long b = minus.get(index++);

            result += a * b;
        }
        if (index < minus.size()) {
            result += minus.get(index);
        }

        index = 0;
        while (index + 1 < plus.size()) {
            long a = plus.get(index++);
            long b = plus.get(index++);
            if(a != 1 && b != 1) {
                result += a * b;
            } else {
                result += a + b;
            }
        }
        if (index < plus.size()) {
            result += plus.get(index);
        }

        System.out.println(result);
    }

}
