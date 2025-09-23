import { configureStore } from "@reduxjs/toolkit";
import useReducer from "./userStore.jsx";

const store = configureStore({
    reducer : {
        user : useReducer
    }
})

export default store;