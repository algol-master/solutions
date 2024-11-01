package sleepyhoon.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 걸린 시간 : 45분 (order-1==n 때문에 삽질함)
public class BJ12789 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> st = new Stack<>();
        String answer = "";
        int n = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        // 현재 n번 학생이 들어갈 차례라는 뜻이다.
        int order = 1;
        // 현재 줄 서 있는 학생의 번호. 초기값은 임의로 0
        int current = 0;
        for(int i=0;i<n;i++){
            current = Integer.parseInt(inputs[i]);
            if(current == order) order++;
            else if(!st.empty() && st.peek() == order) {
                order++;
                st.pop();
                i--;
            }
            else st.push(current);
        }
        while(!st.empty()){
            int output = st.pop();
            if(order == output) {
                order++;
                continue;
            }
            answer = "Sad";
            break;
        }
        // 모두 잘 들어간 것이 맞는가?
        if(order-1 == n) {
            answer = "Nice";
        } else
            answer = "Sad";
        System.out.println(answer);
    }
}
