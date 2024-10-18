import java.awt.print.Pageable;
import java.sql.Array;
import java.util.*;

/**
 * <br>package name   :
 * <br>file name      : ${NAME}
 * <br>date           : 10/11/24
 * <pre>
 * <span style="color: white;">[description]</span>
 *
 * </pre>
 * <pre>
 * <span style="color: white;">usage:</span>
 * {@code
 *
 * } </pre>
 */
public class Main {
    static char[] arr;
    static int index = 0;

    static void push(char a) {
        arr[index] = a;
        index++;
    }

    static char pop() {
        index--;
        return arr[index];
    }

    static int getSize() {
        return index;
    }

    static public boolean solution(String s) {
        arr = new char[100001];
        boolean answer = true;
        int size = s.length();
        for(int i = 0; i < size; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                push(c);
            } else {
                if(getSize() == 0) {
                    return false;
                }
                pop();
            }


        }
        if(getSize() == 0) {
            return  true;
        } else {
            return  false;
        }
    }

    public static void main(String[] args) {
        boolean val = solution("(()(");
        System.out.println(val);
    }


}
