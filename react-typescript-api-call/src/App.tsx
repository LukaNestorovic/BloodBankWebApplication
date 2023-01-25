import React from 'react';
import './App.css';
import LogIn from "./components/LogIn";
import Register                         from "./components/Register"
import Centers                          from "./components/Centers"
import UpdateProfile                    from "./components/UpdateProfile"
import GlobalCenters                    from "./components/GlobalCenters"
import { BrowserRouter, Route, Routes } from "react-router-dom";
import DonorForm                        from "./components/DonorForm";
import AddAppointmentStaff              from './components/AddAppointmentStaff';
import Appointments                     from "./components/Appointments"
import DeleteAppointments               from "./components/DeleteAppointments";
import Success                          from "./components/Success";
import SearchAppointmentsUser           from './components/SearchAppointmentsUser';
import Home                             from "./components/Home";
import PastAppointments                 from "./components/PastAppointments";


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
          <Route path="/centers" element={<Centers />} />
          <Route path="/donorform" element={<DonorForm />} />
          <Route path="/appointment/staff" element={<AddAppointmentStaff />} />
          <Route path="/appointments" element={<Appointments />} />
          <Route path="/scheduledappointments" element={<DeleteAppointments />} />
          <Route path="/success" element={<Success />} />
          <Route path="/appointment/user" element={<SearchAppointmentsUser />} />
          <Route path="/home" element={<Home />} />
          <Route path="/pastappointments" element={<PastAppointments />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
