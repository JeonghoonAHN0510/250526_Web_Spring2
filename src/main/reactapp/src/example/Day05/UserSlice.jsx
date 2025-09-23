import { createSlice } from '@reduxjs/toolkit';

// [1] 전역변수의 초기값 설정
// '로그인 여부'를 저장 : true(로그인) / false(비로그인)
const initialState = { isAuthenticated : false };

// [2] 상태를 변경하는 reducers 함수 정의
// createSlice({name : slice이름, 초기값, reducers : {액션함수명 : (state) => { }})
const userSlice = createSlice({
    name : "user",  // slice의 이름, 저장소에 저장되는 값의 일부분
    initialState,   // [1]에서 정의한 객체로 초기값을 설정, 추후에 다양하게 변경 가능
    reducers : {
        login : (state) => {state.isAuthenticated = true},        // 로그인 함수가 실행되면, 처리되는 코드
        logout : (state) => {state.isAuthenticated = false}       // 로그아웃 함수가 실행되면, 처리되는 코드
    }
    // 상태가 변경되는 함수 정의
});

// [3] 내보내기
// [3-1] export default 내보내기는 한 번만 가능
export default userSlice.reducer;
// [3-2] export 내보내기는 여러번 가능 : 다른 컴포넌트에서 import할 수 있도록
export const { login, logout } = userSlice.actions;