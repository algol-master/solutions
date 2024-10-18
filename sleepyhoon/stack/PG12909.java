package sleepyhoon.stack;

import java.util.Stack;

public class PG12909 {
    boolean solution(String s) {
        char[] array = s.toCharArray();
        Stack<Character> st = new Stack<>();
        st.push('/');
        for(int i=0;i<array.length;i++){
            if(st.peek() == '(' && array[i] == ')') {
                st.pop();
            }
            else {
                st.push(array[i]);
            }
        }
        if(st.size() == 1)
            return true;
        else
            return false;
    }
}
