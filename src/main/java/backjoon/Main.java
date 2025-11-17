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
        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        int[] result = new int[N];
        for (int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
        } // for end

        result[0] = array[0];
        int MaxLength = 1;
        for (int i = 1; i < N; i++){

        } // for end

        answer.append(MaxLength);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end