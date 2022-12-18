import React, {useState}     from "react";
import {TableCell, TableRow} from "@mui/material";
import AppointmentService    from "../services/AppointmentService";
import {useNavigate}         from "react-router-dom";

// @ts-ignore
const Appointment = ({appointment}) => {

    var donor = localStorage.getItem("email")
    const navigate = useNavigate()
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
                AppointmentService.generateQR(appointment.center, appointment.doctor, appointment.date)
                    .then((response) => {
                        console.log(response);
                        console.log(donor)
                    }).catch((error) => {
                        console.log(error)
                })
        }).catch((error) => {
            if(error.response.status == 400)
                alert("Imate zakazan termin");
            else if(error.response.status == 404) {
                alert("Popunite formu");
                navigate("/donorform")
            }
            else if(error.response.status == 401)
                alert("Dali ste krv u poslednjih 6 meseci")
            console.log(error);
        })
        AppointmentService.sendMail(donor)
            .then((response) => {
                console.log(donor);
                console.log(response);
                alert("Check your email");
            }).catch((error) => {
            console.log(error)
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