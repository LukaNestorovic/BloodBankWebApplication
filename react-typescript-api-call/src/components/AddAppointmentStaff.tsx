import { Button, Container, FormControl, InputLabel, MenuItem, Select, Stack, TextField, Typography } from "@mui/material";
import React from "react";
import { useState } from "react";
import { string } from "yup";
import CenterService from "../services/CenterService";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import AppointmentService from "../services/AppointmentService"


export default function AddAppointmentStaff() {
    var i = 1;
    interface Centers {
        center: {
            name: string
            address: string
            description: string
            rating: number
        }[]
    }

    const [centers, setCenters] = useState<Centers["center"]>();

    const [appointment, setAppointment] = useState({
        centerName: "",
        date: ""
    })

    const handleChange = (e: any) => {
        setAppointment({
            ...appointment,
            [e.target.name]: e.target.value
        });
    };

    const handleScheduleAppointment = () => {
        //console.log(appointment)
        AppointmentService.scheduleAppointment(appointment).
        then((response) => {
            if(response.data==="") alert("Appointment not available at designated time!")
            else alert("Appointment saved!")
            console.log(response.data);
        })
        .catch((error) => {
            console.log(error);
        });
    }
    // const [centerName, setCenterName] = useState("Select");

    // const [date, setDate] = useState(new Date());

    // const handleCenterChange = (event: any) => {
    //     setCenterName(event.target.value)
    // }

    // const handleDateChange = (event: any) => {
    //     setDate(event.target.value)
    // }

    React.useEffect(() => {
        CenterService.getCenters().
            then((response) => {
                console.log(response.data);
                setCenters(response.data)
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    // React.useEffect(() => {
    //     console.log(appointment.date)
    // }, [appointment.date, appointment.centerName]);

    return (
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
                    views={["year","month","day","hours","minutes"]}
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
    );
}