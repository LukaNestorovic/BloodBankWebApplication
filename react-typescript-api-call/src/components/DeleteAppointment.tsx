import React, {useState}     from "react";
import {TableCell, TableRow} from "@mui/material";
import AppointmentService    from "../services/AppointmentService";


// @ts-ignore
const DeleteAppointment = ({appointment}) => {

    var donor = localStorage.getItem("email")

    const [dto, setDto] = useState({
        id: appointment.id,
        email: donor
    })

    const deleteApp = (e: any) => {
        e.preventDefault();
        AppointmentService.deleteAppointment(dto)
            .then((response) => {
                console.log(response);
                window.location.reload();
            }).catch((error) => {
            console.log(error);
            alert("Manje od 24h do termina")
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
                <div ><button onClick={deleteApp}>Delete</button></div>
            </TableCell>
        </TableRow>
    );
}

export default DeleteAppointment;