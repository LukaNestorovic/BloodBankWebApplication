import React from 'react';
import './App.css';
import LogIn from "./components/LogIn";
import Register from "./components/Register"
import Centers from "./components/Centers"
import Navbar from "./components/Navbar"
import GlobalCenters from "./components/GlobalCenters"
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Questionnaire from "./components/Questionnaire";

function App() {
  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/global-centers" element={<GlobalCenters />} />
            <Route path="/centers" element={<Centers />}/>
            <Route path="/question" element={<Questionnaire/>}/>
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
