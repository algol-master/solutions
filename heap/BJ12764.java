
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <pre>
 * 문제 : 백준 12764
 * 난이도 : 골드 3
 * 풀이
 * 사실 아이디어 자체는, 바로 생각을 해 냈다. 자리는 최대 사람의 개수만큼 존재할 수 있으니 배열 2개를 선언하였다.
 * 하나는 각 자리의 끝나는 시간이 포함되어 있는 배열, 다른 하나는 각 자리의 이용 횟수에 대한 배열이다.
 * 우선순위 큐는 입력된 사용자 중 시작하는 시간에 대해 정렬된 우선순위 큐이며 이는 compare 을 커스텀으로 
 * 정의하였다. 따라서, 우선순위 큐에는 각 사용자에 대한 인덱스가 포함되어 있고 이는 man 이라는 시작 시간과 끝나는
 * 시간에 대한 배열에 대한 인덱스이다.
 * 
 * 기본적으로 해당 우선순위 큐가 비워질 때 까지 루프문을 도는데, 이는 현재까지 사용된 자리인 result에 대하여,
 * 처음 자리부터 result 번째 자리까지 탐색하여 result.peek 보다 작은 값이 있으면 이를 교체하고, count를 올린다.
 * 
 * 만약 하나도 없으면, result를 하나 늘리고, 배열의 가장 마지막에 추가하도록 한다.
 * 이후 result와 count를 출력한다.
 * </pre>
 */
public class BJ12764 {

    private static int[] seats;
    private static int[] count;

    private static int[][] man;

    private static PriorityQueue<Integer> startAt = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(man[o1][0], man[o2][0]);
        }
    });


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int result = 0;

        int n = Integer.parseInt(br.readLine());
        seats = new int[n];
        count = new int[n];
        man = new int[n][2];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            man[i][0] = Integer.parseInt(st.nextToken());
            man[i][1] = Integer.parseInt(st.nextToken());
            startAt.add(i);
        }

        while(!startAt.isEmpty()) {
            int flag = 0;
            for(int i = 0; i < result + 1; i++) {
                //printq(startAt);
                if(seats[i] <= man[startAt.peek()][0]) {
                    count[i]++;
                    int start = startAt.remove();
                    seats[i] = man[start][1];
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                result++;
                seats[result] = man[startAt.remove()][1];
                count[result]++;
            }

        }

        sb.append(result + 1).append("\n");

        for(int i = 0; i < result + 1; i++) {
            sb.append(count[i]).append(" ");
        }

        System.out.println(sb);


    }


}
