package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 암호는 최소 1개의 모음 (a,e,i,o,u) 최소 2개 이상의 자음으로 구성
// 오름차순으로 구성
// 오름차순으로 출력
public class BJ1759 {
    static List<StringBuilder> answer = new ArrayList<>();
    static String[] list;
    static int n;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        list = new String[c];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<c;i++){
            list[i] = st.nextToken();
        }
        Arrays.sort(list);
        StringBuilder tmp = new StringBuilder();
        combination(tmp,0,0);
        for (StringBuilder stringBuilder : answer) {
            System.out.println(stringBuilder);
        }
    }
    private static void combination(StringBuilder tmp,int start, int depth) {
        if(depth==n) {
            int count1 = 0; // 모음 개수
            int count2 = 0; // 자음 개수
            for(int i=0;i<depth;i++){
                if(tmp.charAt(i) == 'a' ||tmp.charAt(i) == 'e' ||tmp.charAt(i) == 'i' ||tmp.charAt(i) == 'o' ||tmp.charAt(i) == 'u')
                    count1++;
                else
                    count2++;
            }
            if(count1 >= 1 && count2 >= 2)
                answer.add(tmp);
        }
        else {
            for(int i=start;i<c;i++){
                tmp.append(list[i]);
                combination(new StringBuilder(tmp),i+1,depth+1);
                tmp.deleteCharAt(depth);
            }
        }
    }
}
