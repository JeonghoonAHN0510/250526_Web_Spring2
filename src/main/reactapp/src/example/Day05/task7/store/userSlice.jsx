import { createSlice } from "@reduxjs/toolkit";

/*
- Slice : 상태(state), 리듀서(reducer), 액션(action)을 정의하는 곳

*/

// [1] 초기값 설정
// isAuthenticated : 로그인여부 -> true(로그인) / false(비로그인)
// userInfo : 회원정보
const initialState = { isAuthenticated : false, userInfo : null };

// [2] Slice 함수 정의
const userSlice = createSlice({
    name : "user",      // Slice 이름
    initialState,       // 초기값
    reducers : {        // 여러 개의 상태변경 함수(reducer) 정의
     // 함수명 : (state) => { },
        login : (state, action) => {
            state.isAuthenticated = true;   // 로그인여부 true
            // dispatch할 때, 전달되는 매개변수의 payload 안에 값이 존재
            // dispatch(login('hi')) -> action.payload = 'hi'
            state.userInfo = action.payload;
        },
        logout : (state) => {
            state.isAuthenticated = false;  // 로그인여부 false
            state.userInfo = null;          // 회원정보 null
        }
    }
})

// [3] 내보내기
// userSlice.reducer = userSlice의 reducers 객체
export default userSlice.reducer;
// [4] 다른 컴포넌트에서 action이 가능하도록 login, logout 내보내기
// userSlice.actions = userSlice의 reducers 객체 내의 함수들
export const { login, logout } = userSlice.actions;