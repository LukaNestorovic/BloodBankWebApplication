import {useEffect, useState}                                                                     from "react";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TableSortLabel} from "@mui/material";
import Appointment                                                                               from "./Appointment"
import AppointmentService                                                                        from "../services/AppointmentService";
import DeleteAppointment                                                                         from "./DeleteAppointment";
import {
    useNavigate
}                                                                                                from "react-router-dom";
import PastAppointment
                                                                                                 from "./PastAppointment";

export default function PastAppointments(){

    const [loading, setLoading] = useState(true);
    const [appointments, setAppointments] = useState(null);
    const navigate = useNavigate()
    const role = localStorage.getItem("role")
    const enable = localStorage.getItem("enable")


    useEffect(() => {
        if(role === "user" && enable === "true"){
            const fetchData = async () => {
                setLoading(true);
                try {
                    const response = await AppointmentService.findPastAppointment(localStorage.getItem("email"),role,enable);
                    setAppointments(response.data);
                } catch (error) {
                    console.log(error);
                }
                setLoading(false);
            };
            fetchData();
        }
        else{
            console.error("Access denied")
            navigate("/")
        }
    }, []);

    const [rowData, setRowData] = useState(appointments);

    // @ts-ignore
    return(

        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Finished Appointments</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>
                            Date</TableCell>
                        <TableCell align={"center"} >
                            Center</TableCell>
                        <TableCell align={"center"}>Doctor</TableCell>
                    </TableRow>
                </TableHead>
                {!loading && (
                    <TableBody>
                        {appointments.map((appointment:any) => (
                            <PastAppointment
                                appointment={appointment}
                                key={appointment.id}/>
                        ))}
                    </TableBody>
                )}
            </Table>
        </TableContainer>
    );
}