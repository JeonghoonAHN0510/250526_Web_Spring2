import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import HomePage from "./pages/HomePage";
import MenuPage from "./pages/MenuPage";
import CartPage from "./pages/CartPage";
import './css/main.css'

export default function App( props ){
    return(
        <>
        <BrowserRouter>
            <div class="container">
                <Header/>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/menu" element={<MenuPage/>}/>
                    <Route path="/cart" element={<CartPage/>}/>
                </Routes>
            </div>
        </BrowserRouter>
        </>
    ) // return end
} // func end