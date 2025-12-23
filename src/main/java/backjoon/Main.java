package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] primeNumbers = new int[N];

        int number = 2;
        int index = -1;
        while (number <= N){
            if (primeNum(number)){
                primeNumbers[++index] = primeNumbers[Math.max(index - 1, 0)] + number;
            } // if end
            number++;
        } // while end

        int left = 0;
        int right = 0;
        int count = 0;
        while (left <= right){
            if (right > index) break;
            int sum;
            if (left == 0){
                sum = primeNumbers[right];
            } else {
                sum = primeNumbers[right] - primeNumbers[left - 1];
            } // if end
            if (sum == N){
                count++;
                left++;
            } else if (sum > N){
                left++;
            } else {
                right++;
            } // if end
        } // while end

        answer.append(count);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static boolean primeNum(int number){
        if (number < 2) return false;
        if (number == 2) return true;
        for (int i = 2; i <= Math.floor(Math.sqrt(number)); i++ ){
            if (number % i == 0) return false;
        } // for end
        return true;
    } // func end
} // class end