package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 : BJ1052
 * 문제 링크 :https://www.acmicpc.net/problem/1052
 * 난이도 : 골드 5
 * 걸린 시간 : 25분
 * 알고리즘 : 비트마스크
 *
 * 풀이
 * </pre>
 * 풀이 방법은 쉽게 나왔다.(해당 주제에 대해 공부하니, 어떤 알고리즘을 사용할지는 쉽게 나오는 듯. 좋은건 아니다)
 * 그냥 주어진 수를 계속 절반으로 나누다가, 홀수라 나누지 못하면 그에 맞는 물을 구하고 다시 나누게끔 하였다. 2의 배수 단위의
 * 물은 어쨋거나 1L만 사서 채워지니, 총 몇 L의 물을 구할건지에 대한 문제가 된다.
 *
 * 다만, 단순히 2로 나누고 짝수/홀수 검사하는건 딱봐도 시간초과 내지는 뭔가 문제가 생길 듯 하니 2를 나누면 오른쪽으로 한칸 이동이 된다는
 * 성질을 이용해 마지막 비트를 검사하여 홀수/짝수를 나누고, 이를 기반으로 홀수면 1을 더하고, 물의 구매량을 더하고, 오른쪽으로 비트 이동이
 * 되도록 하였다.
 * 이 과정에서 물의 구매량은 루프 한번 당 2씩 곱해지게 설정해 두었다.
 *
 * 한번 틀렸는데, 시간초과 문제였다. while문의 종료 조건을 N > 2^M 으로 해두었는데, M 이 1000까지 가므로 당연하지만 시간초과는 고사하고
 * 오버플로우가 난다.(단, 백준에서는 2^M을 계산하여 오버플로우가 되고, 음수가 되어 무한 루프 과정에서 시간초과가 뜬 듯 하다)
 * 그래서 그냥 비트의 개수를 세는 Integer.bitCount를 사용해서 M과 비교하니 잘 되었다.
 *
 */
public class BJ1052 {

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count = 0;
        int index = 1;

        while(Integer.bitCount(N) > M) {
            if((N & 1) == 0) {
                N = N >> 1;
            } else {
                count += index;
                N = (N + 1) >> 1;
            }
            index = index * 2;
        }

        sb.append(count);
        System.out.println(sb);
    }
}
