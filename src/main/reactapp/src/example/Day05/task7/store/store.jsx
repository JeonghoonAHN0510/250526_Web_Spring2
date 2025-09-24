import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./userSlice.jsx";
import storage from 'redux-persist/lib/storage'                 // 로컬스토리지
import storageSession from 'redux-persist/lib/storage/session'  // 세션스토리지
import { persistStore, persistReducer } from 'redux-persist';
/*
- Store : 여러 개의 상태를 보관하는 저장소
- 단 1개만 존재해야한다.

- persistence : 로컬/세션 스토리지에 저장하여, 상태를 유지하는 방법
- 설치 : npm install redux-persist
- 스토리지 설정
*/

// [4] redux-persist 설정
// { key : '저장할 이름' , storage : 저장소종류 }
const persistConfig = { key : 'user' , storage };   // -> 로컬스토리지에 user라는 이름으로 상태를 저장

// [5] Reducer에 persist 설정 적용
// const persistedReducer = persistReducer( persist옵션, 설정할Reducer );
const persistedReducer = persistReducer( persistConfig, userSlice );

// [1] Store 만들기
const store = configureStore({
    reducer : {
        // [2] 내가 만든 Slice 등록
        // Slice에서 정의한 name : import한 슬라이스명
        // user : userSlice            -> persist 적용 전
        // [6] persist가 적용된 reducer를 store에 등록
        user : persistedReducer     // -> persist 적용 후
    }
})
// [3] 다른 컴포넌트에서 Store를 호출할 수 있도록 내보내기
export default store;
// [7] 등록된 persistedStore 내보내기
export const persistor = persistStore(store);