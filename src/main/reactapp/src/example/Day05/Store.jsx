import { configureStore } from "@reduxjs/toolkit"
import userReducer from './UserSlice.jsx'

// 여러개의 slice들을 하나의 store에서 관리
// [1] Store 생성하기
// -> 모든 컴포넌트에서 store를 참조하여 저장된 슬라이스를 사용한다.
// configureStore({ reducer : {상태명1: 슬라이스명1, 상태명2: 슬라이스명2}})
const store = configureStore({
    reducer : {
        // [2] 슬라이스 등록하기
        // user 상태에 개발자가 만든 슬라이스를 대입
        user : userReducer
    }
})

// [3] store 내보내기, 다른 컴포넌트가 사용할 수 있도록
export default store;