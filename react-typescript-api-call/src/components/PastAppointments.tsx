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
    const [orderDirection, setOrderDirection] = useState("asc");

    const sortArrayDate = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.date > b.date ? 1 : b.date > a.date ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.date < b.date ? 1 : b.date < a.date ? -1 : 0
                );
        }
    };

    const sortArrayCenter = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.center > b.center ? 1 : b.center > a.center ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.center < b.center ? 1 : b.center < a.center ? -1 : 0
                );
        }
    };

    const sortArrayDoctor = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.doctor > b.doctor ? 1 : b.doctor > a.doctor ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.doctor < b.doctor ? 1 : b.doctor < a.doctor ? -1 : 0
                );
        }
    };

    const handleSortRequestDate = () => {
        setRowData(sortArrayDate(appointments, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    const handleSortCenter = () => {
        setRowData(sortArrayCenter(appointments, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    const handleSortRequestDoctor = () => {
        setRowData(sortArrayDoctor(appointments, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    // @ts-ignore
    return(

        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Finished Appointments</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"} onClick={handleSortRequestDate}><TableSortLabel active={true} direction={"desc"}>
                            Date</TableSortLabel></TableCell>
                        <TableCell align={"center"} onClick={handleSortCenter}><TableSortLabel active={true} direction={"desc"}>
                            Center</TableSortLabel></TableCell>
                        <TableCell align={"center"} onClick={handleSortRequestDoctor}><TableSortLabel active={true} direction={"desc"}>Doctor</TableSortLabel></TableCell>
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