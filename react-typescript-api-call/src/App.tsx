import React from 'react';
import './App.css';
import LogIn from "./components/LogIn";
import Register from "./components/Register"
import Navbar from "./components/Navbar"
import UpdateProfile from "./components/UpdateProfile"
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<UpdateProfile />} />
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
