import * as React                 from 'react';
import {Button, Container, Stack} from "@mui/material";
import {useNavigate}              from "react-router-dom";

export default function Home() {
    const navigate = useNavigate()
    const logout = (e:any) => {
        e.preventDefault();
        localStorage.clear()
        navigate("/")
    };
    return(
      <Container>
          <Stack direction="column" spacing={1}>
              <h1 style={{alignSelf:'center'}}>Home</h1>
              <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/donorform")}>Donor form</Button>
              <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/centers")}>Centers</Button>
              <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/appointments")}>Appointments</Button>
              <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/scheduledappointments")}>Scheduled appointments</Button>
              
              <Button variant="contained" style={{width:200, alignSelf:'center', background:'red'}} onClick={logout}>Log out</Button>
          </Stack>
      </Container>
    );
}