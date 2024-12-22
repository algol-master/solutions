package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 : BJ11723
 * 문제 링크 : https://www.acmicpc.net/problem/11723
 * 난이도 : 실버 5
 * 걸린 시간 : 10분
 * 알고리즘 : 비트마스킹
 *
 * 풀이
 * 일반적인 비트마스크 문제. 전반적인 비트 연산자를 사용하여 풀 수 있다.
 * </pre>
 */
public class BJ11723 {

    static int N;
    static int R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        R = 0;

        while(N --> 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("all")) {
                R = (1 << 21) - 1;
            } else if(command.equals("empty")) {
                R = 0;
            } else if(command.equals("add")) {
                int val = Integer.parseInt(st.nextToken());
                R |= (1 << val);
            } else if(command.equals("remove")) {
                int val = Integer.parseInt(st.nextToken());
                R &= ~(1 << val);
            } else if(command.equals("toggle")) {
                int val = Integer.parseInt(st.nextToken());
                R ^= (1 << val);
            } else if(command.equals("check")) {
                int val = Integer.parseInt(st.nextToken());
                sb.append((R & (1 << val)) == 0 ? 0 : 1).append("\n");
            }
        }

        System.out.println(sb);
    }
}
