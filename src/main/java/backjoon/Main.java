package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 용액의 수 N
        int[] array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        } // for end
        Arrays.sort(array);

        int start = 0;
        int end = N - 1;

        int answer1 = 0;
        int answer2 = 0;

        int minAbs = Integer.MAX_VALUE;
        while (start < end){
            int sum = array[start] + array[end];

            if (Math.abs(sum) < minAbs){
                minAbs = Math.abs(sum);
                answer1 = array[start];
                answer2 = array[end];
            } // if end
            if (sum == 0){
                break;
            } else if (sum < 0){
                start++;
            } else {
                end--;
            } // if end
        } // while end

        answer.append(answer1).append(" ").append(answer2);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end