import { createSlice } from "@reduxjs/toolkit";

// [1] 초기값 설정
// isAuthenticated : 로그인여부 -> true(로그인) / false(비로그인)
const initialState = { isAuthenticated : false };

// [2] reducers 함수 정의
const userSlice = createSlice({
    name : "user",
    initialState,
    reducers : {
        login : (state) => {state.isAuthenticated = true},
        logout : (state) => {state.isAuthenticated = false}
    }
})

// [3] 내보내기
export default userSlice.reducer;
export const { login, logout } = userSlice.actions;