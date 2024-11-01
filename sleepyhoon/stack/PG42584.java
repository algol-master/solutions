package sleepyhoon.stack;

import java.util.*;

class PG42584 {
    public int[] solution(int[] prices) {
        // index를 저장한다.
        Stack<Integer> st = new Stack<>();
        int length = prices.length;
        int[] answer = new int[length];
        st.push(0);
        for(int i=1;i<length;i++){
            // i 번째에서 가격이 감소하는 경우
            while(!st.empty() && prices[st.peek()] > prices[i]) {
                int startIdx = st.pop();
                answer[startIdx] = i - startIdx;
            }
            st.push(i);
        }
        // 가격이 하락한 적 없는 경우
        while(!st.empty()){
            int index = st.pop();
            answer[index] = length - index - 1;
        }
        return answer;
    }
}
