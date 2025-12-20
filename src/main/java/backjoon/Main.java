package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 수열의 크기 N
        int[] array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        } // for end

        int X = Integer.parseInt(br.readLine());

        Arrays.sort(array);

        int count = 0;
        int start = 0;
        int end = N - 1;
        while (start < end){
            int sum = array[start] + array[end];

            if (sum == X){
                count++;
                start++;
                end--;
            } else if (sum < X){
                start++;
            } else {
                end--;
            } // if end
        } // while end

        answer.append(count);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end