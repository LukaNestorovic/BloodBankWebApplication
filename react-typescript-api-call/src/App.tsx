import React from 'react';
import './App.css';
import LogIn from "./components/LogIn";
import Register from "./components/Register"
import Centers from "./components/Centers"
import UpdateProfile from "./components/UpdateProfile"
import GlobalCenters from "./components/GlobalCenters"
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Questionnaire from "./components/Questionnaire";
import DonorForm                      from "./components/DonorForm";
import AddAppointmentStaff from './components/AddAppointmentStaff';

function App() {
  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route index element={<LogIn />} />
            <Route path="/" element={<LogIn />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<UpdateProfile />} />
            <Route path="/global-centers" element={<GlobalCenters />} />
            <Route path="/centers" element={<Centers />}/>
            <Route path="/question" element={<Questionnaire/>}/>
            <Route path="/donorform" element={<DonorForm/>}/>
            <Route path="/appointment/staff" element={<AddAppointmentStaff/>}/>
          </Routes>
        </BrowserRouter>
      </>
  );
}

export default App;
