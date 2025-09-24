import { configureStore } from '@reduxjs/toolkit';
import { persistReducer, persistStore } from 'redux-persist';
import cartSlice from './cartSlice.jsx'
import storage from 'redux-persist/lib/storage';    // 로컬 스토리지

// [2] 스토리지 저장 설정
const persistConfig = { key : 'cartList', storage };

// [3] Reducer에 설정 적용
const persistedReducer = persistReducer( persistConfig, cartSlice );

// [1] Store 만들기
const store = configureStore({
    reducer : {
        cart : persistedReducer
    }
})

// [4] 내보내기
export default store;
export const persistor = persistStore(store);