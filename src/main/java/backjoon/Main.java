package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(st.nextToken());
        int targetValue = Integer.parseInt(st.nextToken());

        int[] array = new int[length];

        st = new StringTokenizer(br.readLine());
        array[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < length; i++){
            int value = Integer.parseInt(st.nextToken());
            array[i] = array[i-1] + value;
        } // for end

        int left = 0;
        int right = 0;

        int minLength = Integer.MAX_VALUE;
        while (left <= right){
            if (right >= length) break;
            int sum;
            if (left == 0){
                sum = array[right];
            } else {
                sum = array[right] - array[left - 1];
            } // if end

            if (sum < targetValue){
                right++;
            } else {
                minLength = Math.min(minLength, right - left + 1);
                left++;
            } // if end
        } // while end

        if (minLength == Integer.MAX_VALUE){
            answer.append("0");
        } else {
            answer.append(minLength);
        } // if end



        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end