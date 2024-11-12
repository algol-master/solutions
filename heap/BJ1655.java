
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <pre>
 * 문제 : 백준 1655
 * 난이도 : 골드 3
 * 풀이
 * 처음엔 priority queue 를 하나 만들고, 새로운 수를 집어 넣을 때마다 add 한 뒤
 * 이를 복사한 큐에 대해 (index + 1)/2 만큼 remove 한 마지막 값을 사용함.
 * 그러나, 시간 초과가 났고 (메모리 초과도 아닌), 추가하는 횟수 n에 대해, 각 시도마다
 * n/2 번의 remove를 하는 것이 문제라 생각하여 한번의 삽입과 한번의 제거만 존재하게끔 다시 구성함.
 * 
 * 가장 처음 든 생각은 작은 값들만 들어 있는 우선순위 큐와 큰 값들만 들어 있는 우선순위 큐를 구성하여
 * 작은 값들 중 가장 큰 값이나 큰 값들 중 가장 작은 값으로 중간값을 설정해야 될 것 같았고 이를 위해 가장 
 * 처음 입력된 값을 초기 중간값을 설정한 뒤, 새롭게 입력되는 수가 이보다 크면 big 이라는 우선순위 큐에, 아니면 
 * small 이라는 우선순위 큐에 입력되도록 하였음.
 * 
 * 단, 이 둘의 크기가 같거나 1 차이만 나야 하므로, 삽입하기 전에 이를 검사하여 만약 새롭게 입력하게 되는 
 * 큐가 다른 것에 비해 너무 커지면(이미 사이즈가 하나 큰데 더 추가하게 되면) 여기서 하나를 빼서 다른 큐에 
 * 집어넣도록 설계함. 이후 각 큐의 크기가 다르면 적당한 값을, 같으면 두 큐의 peek 값을 비교해 더 작은 값이
 * 들어가게 함
 * </pre>
 */
public class BJ1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> big = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        int middle = Integer.parseInt(br.readLine());
        small.add(middle);
        sb.append(middle).append("\n");

        for(int i = 2; i <= n; i++) {
            int k = Integer.parseInt(br.readLine());
            if(k > middle) {
                if(big.size() > small.size()) {
                    int tmp = big.remove();
                    small.add(tmp);
                }
                big.add(k);
            } else {
                if(small.size() > big.size()) {
                    int tmp = small.remove();
                    big.add(tmp);
                }
                small.add(k);
            }

            if(small.size() == big.size()) {
                middle = small.peek() > big.peek() ? big.peek() : small.peek();
            } else if(small.size() > big.size()) {
                middle = small.peek();
            } else {
                middle = big.peek();
            }
            sb.append(middle).append("\n");


        }

        System.out.println(sb);
    }
}
