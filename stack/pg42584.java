import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] solution(int[] prices) {
        int len = prices.length;

        Deque<Integer> stack = new ArrayDeque<>();

        int[] answer = new int[len];
        stack.push(0);

        for(int i = 1; i < len; i++) {

            while(!stack.isEmpty() && (prices[stack.peek()] > prices[i])) {
                int index = stack.pop();
                answer[index] = i - index;
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = len - index -1;
        }

        return answer;
    }

}
