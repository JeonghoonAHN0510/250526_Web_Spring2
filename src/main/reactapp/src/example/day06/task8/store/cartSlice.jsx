import { createSlice } from "@reduxjs/toolkit";

// [1] 전역변수 설정
const cartList  = [];
const menu = [
    { id: 1, name: "아메리카노", price: 3000, amount : 0 },
    { id: 2, name: "카페라떼", price: 4000, amount : 0 },
    { id: 3, name: "카푸치노", price: 4500, amount : 0 },
];
const initialState = { menu };

// [2] Slice 함수 정의
const cartSlice = createSlice({
    name : "cart",
    initialState,
    reducers : {
        addCart : (state, action) => {
            state.menu.forEach( (item) => {
                if ( item.id == action.payload ) item.amount += 1;
            })
        }
    }
})

// [3] 내보내기
export default cartSlice.reducer;
export const { addCart } = cartSlice.actions;