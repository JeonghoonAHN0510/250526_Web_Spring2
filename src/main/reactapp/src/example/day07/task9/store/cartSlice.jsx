import { createSlice } from "@reduxjs/toolkit";

// [1] 전역변수 설정
const menu = [
    { id: 1, name: "아메리카노", price: 3000 },
    { id: 2, name: "카페라떼", price: 4000 },
    { id: 3, name: "카푸치노", price: 4500 },
];
const cartList = [];
const initialValue = { menu, cartList };

// [2] Slice 함수 정의
const cartSlice = createSlice({
    name : "cart",
    initialState : initialValue,
    reducers : {
        addCart : (state, action) => {
            let check = false;
            state.cartList.forEach( (item) => {
                if ( item.id == action.payload.id ){
                    item.amount += 1;
                    check = true;
                } // if end
            }) // forEach end
            if ( !check ){
                action.payload.amount = 1;
                state.cartList.push( action.payload );
            } // if end
        }
    }
})

// [3] 내보내기
export default cartSlice.reducer;
export const { addCart } = cartSlice.actions;