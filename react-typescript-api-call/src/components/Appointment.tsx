import React, {useState}     from "react";
import {TableCell, TableRow} from "@mui/material";
import AppointmentService    from "../services/AppointmentService";
import UserService           from "../services/UserService";

// @ts-ignore
const Appointment = ({appointment}) => {

    var donor = localStorage.getItem("email")



    const update = (e: any) => {
        e.preventDefault();
        AppointmentService.updateAppointment(appointment.id, donor)
            .then((response) => {
                console.log(response);
                alert("Successfully saved user data!");
                window.location.reload();
        }).catch((error) => {
            console.log(donor)
            console.log(appointment.id)
            console.log(error);
        })
    };

    return (
        <TableRow key={appointment.id}>
            <TableCell align={"center"}>
                <div>{appointment.id}</div>
            </TableCell >
            <TableCell align={"center"}>
                <div>{appointment.date}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{appointment.center}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div >{appointment.doctor}</div>
            </TableCell>
            <TableCell align={"center"}>
                <div ><button onClick={update}>Schedule</button></div>
            </TableCell>
        </TableRow>
    );
}

export default Appointment;