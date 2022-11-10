import React from 'react';
import './App.css';
import LogIn from "./components/LogIn";
import Register from "./components/Register"
import Navbar from "./components/Navbar"
import Centers from "./components/GlobalCenters"
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {
  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/global-centers" element={<Centers />} />
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
