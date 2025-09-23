import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./userSlice.jsx";

/*
- Store : 여러 개의 상태를 보관하는 저장소
- 단 1개만 존재해야한다.
*/

// [1] Store 만들기
const store = configureStore({
    reducer : {
        // [2] 내가 만든 Slice 등록
        // Slice에서 정의한 name : import한 슬라이스명
        user : userSlice
    }
})
// [3] 다른 컴포넌트에서 Store를 호출할 수 있도록 내보내기
export default store;