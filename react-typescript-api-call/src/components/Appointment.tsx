import React, {useState}     from "react";
import {TableCell, TableRow} from "@mui/material";
import AppointmentService    from "../services/AppointmentService";

// @ts-ignore
const Appointment = ({appointment}) => {

    var donor = localStorage.getItem("email")

    const [dto, setDto] = useState({
        id: appointment.id,
        email: donor
    })

    const update = (e: any) => {
        e.preventDefault();
        AppointmentService.updateAppointment(dto)
            .then((response) => {
                console.log(response);
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