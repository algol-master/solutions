package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * <pre>
 * 문제 : BJ10973
 * 문제 링크 : https://www.acmicpc.net/problem/10973
 * 난이도 : 실버 3
 * 걸린 시간 : 13분
 * 알고리즘 : 이전순열 / 다음순열 변경
 *
 * 풀이
 * 그냥 다음 순열의 로직을 약간만 바꾸면 가능하다.
 * </pre>
 */
public class BJ10973 {

    public static int N;
    public static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int pivot = -1;
        for(int i = N - 2; i >= 0; i--) {
            if(arr[i] > arr[i + 1]) {
                pivot = i;
                break;
            }
        }

        if(pivot == -1) {
            System.out.println(-1);
            return;
        }  else {
            for(int i = N - 1; i > pivot; i--) {
                if(arr[pivot] > arr[i]) {
                    swap(pivot, i);
                    break;
                }
            }
            Arrays.sort(arr, pivot + 1, N, Collections.reverseOrder());
        }

        for(int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
