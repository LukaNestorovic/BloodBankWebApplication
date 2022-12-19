import { Button, Container, FormControl, InputLabel, MenuItem, Select, Stack, TextField, Typography } from "@mui/material";
import React from "react";
import { useState } from "react";
import { string } from "yup";
import CenterService from "../services/CenterService";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import AppointmentService from "../services/AppointmentService"
import { Calendar, momentLocalizer } from 'react-big-calendar'
import { dateFnsLocalizer, Event } from 'react-big-calendar'
import moment from 'moment'
import "react-big-calendar/lib/css/react-big-calendar.css"
import { useNavigate } from "react-router-dom";
const localizer = momentLocalizer(moment)

export default function AddAppointmentStaff() {
    var i = 1;

    const navigate = useNavigate()

    interface Centers {
        center: {
            name: string
            address: string
            description: string
            rating: number
        }[]
    }

    const [events, setEvents] = useState<Event[]>([])

    const [appointment, setAppointment] = useState({
        centerName: "",
        date: "",
        email: "",
    })

    const [centers, setCenters] = useState<Centers["center"]>();

    const handleChange = (e: any) => {
        setAppointment({
            ...appointment,
            [e.target.name]: e.target.value
        });
    };

    const handleScheduleAppointment = () => {
        //console.log(appointment)
        if (appointment.centerName === "" || appointment.date === "") {
            alert("Select all data!");
            return
        }
        AppointmentService.scheduleAppointment(appointment).
            then((response) => {
                if (response.data === "") alert("Appointment not available at designated time!")
                else alert("Appointment saved!")
                console.log(response.data);
                refreshCalendar()
            })
            .catch((error) => {
                if (error.response.status == "403") {
                    alert("NOT STAFF!")
                    navigate("/")
                }
                if (error.response.status == "406") {
                    alert("Appointment not available at designated time!")
                }
                console.log(error);
            });
    }

    React.useEffect(() => {
        if ("staff" != localStorage.getItem("role")) {
            console.error("Access denied")
            navigate("/")
        }
        setAppointment({
            ...appointment,
            email: localStorage.getItem("email")
        });
        CenterService.getCentersGlobal().
            then((response) => {
                console.log(response.data);
                setCenters(response.data)
            })
            .catch((error) => {
                console.log(error);
            });
        refreshCalendar()
    }, []);

    const refreshCalendar = () => {
        console.log({ email: localStorage.getItem("email") })
        AppointmentService.findAppointmentsAdmin({ email: localStorage.getItem("email") }).
            then((response) => {
                console.log(response.data);
                const evs: React.SetStateAction<Event[]> | { allDay: boolean; title: any; start: Date; end: Date; resource: any; }[] = [];
                response.data.forEach((element: any) => {
                    evs.push({
                        allDay: false,
                        title: element.title,
                        start: new Date(element.start),
                        end: new Date(element.end),
                        resource: element.resource,
                    })
                })
                setEvents(evs)
            })
            .catch((error) => {
                console.log(error);
            });
    }

    // React.useEffect(() => {
    //     console.log(appointment.date)
    // }, [appointment.date, appointment.centerName]);

    return (
        <Container>
            <Stack
                direction="column"
                justifyContent="center"
                alignItems="center"
                spacing={2}
            >
                <h1 style={{ alignSelf: 'center' }}>Scheduling new appointment</h1>
                <FormControl>
                    <InputLabel id="demo-simple-select-label">Center</InputLabel>
                    <Select
                        style={{ minWidth: 300 }}
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={appointment.centerName}
                        label="center"
                        onChange={handleChange}
                        name="centerName"
                    >
                        {centers?.map(entry => (
                            <MenuItem value={entry.name} key={i++}>
                                {entry.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DateTimePicker
                        views={["year", "month", "day", "hours", "minutes"]}
                        disablePast
                        ampm={false}
                        renderInput={(props) => <TextField {...props} />}
                        label="Date and Time"
                        value={appointment.date}
                        onChange={(newValue) => setAppointment({
                            ...appointment,
                            date: newValue
                        })}
                    />
                </LocalizationProvider>
                <Button variant="contained" onClick={handleScheduleAppointment} style={{ width: 200, alignSelf: 'center' }}>Save</Button>
            </Stack>
            <Calendar
                events={events}
                localizer={localizer}
                startAccessor="start"
                endAccessor="end"
                style={{ height: 500 }}
            />
        </Container>
    );
}