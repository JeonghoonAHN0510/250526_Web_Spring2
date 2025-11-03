import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 보석 개수 N
        int K = Integer.parseInt(st.nextToken());       // 가방 개수 K
        // 무게 기준으로 오름차순
        PriorityQueue<int[]> jewelPQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewelPQ.offer(new int[]{weight, price});
        } // for end

        // 가방 오름차순
        PriorityQueue<Integer> bagPQ = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            bagPQ.offer(Integer.parseInt(br.readLine()));
        } // for end

        // 가격 내림차순
        PriorityQueue<Long> pricePQ = new PriorityQueue<>(Collections.reverseOrder());

        long totalPrice = 0;

        while (!bagPQ.isEmpty()){               // 가방을 다 소모할때까지
            int currentBag = bagPQ.poll();      // 현재 가방 무게를 꺼내서
            while (!jewelPQ.isEmpty() && jewelPQ.peek()[0] <= currentBag) {
                // 보석 무게가 현재 가방보다 작으면, 가격에 추가하기
                // 무게 기준으로 오름차순이기에 추가 못할 때까지 비교하면 된다.
                pricePQ.offer((long) jewelPQ.poll()[1]);
            } // while end

            if (!pricePQ.isEmpty()) {           // 모든 가격을 총 가격에 더하기
                totalPrice += pricePQ.poll();
            } // if end
        } // while end

        answer.append(totalPrice);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end