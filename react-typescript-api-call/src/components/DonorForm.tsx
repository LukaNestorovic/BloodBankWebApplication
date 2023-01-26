import * as React from 'react';
import { Button, Container, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, Stack } from "@mui/material";
import { useEffect, useState } from "react";
import UserService
    from "../services/UserService";
import DonorFormService
    from "../services/DonorFormService";
import { HttpStatusCode } from "axios";
import { useNavigate } from "react-router-dom";


export default function DonorForm() {
    var storageRole = localStorage.getItem("role")
    const navigate = useNavigate()
    const enable = localStorage.getItem("enable")

    useEffect(() => {
        if (storageRole != "user" || enable != "true") {
            console.error("Access denied")
            navigate("/")
        }
    }, [])

    const [gender1, setGender1] = useState("")
    const handleChange1 = (event: SelectChangeEvent) => {
        setGender1(event.target.value as string);
    };
    const [gender2, setGender2] = useState("")
    const handleChange2 = (event: SelectChangeEvent) => {
        setGender2(event.target.value as string);
    };
    const [gender3, setGender3] = useState("")
    const handleChange3 = (event: SelectChangeEvent) => {
        setGender3(event.target.value as string);
    };
    const [gender4, setGender4] = useState("")
    const handleChange4 = (event: SelectChangeEvent) => {
        setGender4(event.target.value as string);
    };
    const [gender5, setGender5] = useState("")
    const handleChange5 = (event: SelectChangeEvent) => {
        setGender5(event.target.value as string);
    };
    const [gender6, setGender6] = useState("")
    const handleChange6 = (event: SelectChangeEvent) => {
        setGender6(event.target.value as string);
    };
    const [gender7, setGender7] = useState("")
    const handleChange7 = (event: SelectChangeEvent) => {
        setGender7(event.target.value as string);
    };
    const [gender8, setGender8] = useState("")
    const handleChange8 = (event: SelectChangeEvent) => {
        setGender8(event.target.value as string);
    };
    const [gender9, setGender9] = useState("")
    const handleChange9 = (event: SelectChangeEvent) => {
        setGender9(event.target.value as string);
    };
    const [gender10, setGender10] = useState("")
    const handleChange10 = (event: SelectChangeEvent) => {
        setGender10(event.target.value as string);
    };
    const [gender11, setGender11] = useState("")
    const handleChange11 = (event: SelectChangeEvent) => {
        setGender11(event.target.value as string);
    };
    const [gender12, setGender12] = useState("")
    const handleChange12 = (event: SelectChangeEvent) => {
        setGender12(event.target.value as string);
    };
    const [gender13, setGender13] = useState("")
    const handleChange13 = (event: SelectChangeEvent) => {
        setGender13(event.target.value as string);
    };
    const [gender14, setGender14] = useState("")
    const handleChange14 = (event: SelectChangeEvent) => {
        setGender14(event.target.value as string);
    };
    const [gender15, setGender15] = useState("")
    const handleChange15 = (event: SelectChangeEvent) => {
        setGender15(event.target.value as string);
    };
    const [answer, setAnswer] = useState("")

    const current = new Date();
    const date = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
    var storageEmail = localStorage.getItem("email")

    const [donorForm, setDonorForm] = useState({
        id: "",
        question1: "",
        question2: "",
        question3: "",
        question4: "",
        question5: "",
        question6: "",
        question7: "",
        question8: "",
        question9: "",
        question10: "",
        question11: "",
        question12: "",
        question13: "",
        question14: "",
        question15: "",
        date: current,
        userEmail: storageEmail
    })

    const handleChange = (e: any) => {
        const value = e.target.value;
        setDonorForm({ ...donorForm, [e.target.name]: value });
    };


    const saveDonorForm = (e: any) => {
        e.preventDefault();
        DonorFormService.saveDonorForm(donorForm)
            .then((response) => {
                console.log(response);
                // navigate("/home")
            })
            .catch((error) => {
                console.log(error);
                alert("Imate popunjenu anketu");
            });
    };

    return (
        <Stack direction="column" spacing={1}>
            <h1 style={{ textAlign: 'center', alignSelf: 'center' }}>Questionnaire for blood donor</h1>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you ever donated blood?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question1"
                        id="demo-simple-select"
                        value={gender1}
                        label="Answer"
                        onChange={e => {
                            handleChange1(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you ever been declined as blood
                        donor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question2"
                        id="demo-simple-select"
                        value={gender2}
                        label="Answer"
                        onChange={e => {
                            handleChange2(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Do you feel healthy to be a blood donor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question3"
                        id="demo-simple-select"
                        value={gender3}
                        label="Answer"
                        onChange={e => {
                            handleChange3(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you eat something before coming here?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question4"
                        id="demo-simple-select"
                        value={gender4}
                        label="Answer"
                        onChange={e => {
                            handleChange4(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Do you have dangerous job or hobby?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question5"
                        id="demo-simple-select"
                        value={gender5}
                        label="Answer"
                        onChange={e => {
                            handleChange5(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Do you use some type of medication every
                        day?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question6"
                        id="demo-simple-select"
                        value={gender6}
                        label="Answer"
                        onChange={e => {
                            handleChange6(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you take any medicine in last 2-3
                        days?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question7"
                        id="demo-simple-select"
                        value={gender7}
                        label="Answer"
                        onChange={e => {
                            handleChange7(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you pull tooth in last 7 days?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question8"
                        id="demo-simple-select"
                        value={gender8}
                        label="Answer"
                        onChange={e => {
                            handleChange8(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Have you suddenly lost weight in the last 6
                        months?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question9"
                        id="demo-simple-select"
                        value={gender9}
                        label="Answer"
                        onChange={e => {
                            handleChange9(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Have you ever had thyroid, pituitary, and/or hormone
                        problems?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question10"
                        id="demo-simple-select"
                        value={gender10}
                        label="Answer"
                        onChange={e => {
                            handleChange10(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Do you suffer from any chronic disease?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question11"
                        id="demo-simple-select"
                        value={gender11}
                        label="Answer"
                        onChange={e => {
                            handleChange11(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Have you had tick bites in the past 12 months and have
                        you
                        seen a doctor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question12"
                        id="demo-simple-select"
                        value={gender12}
                        label="Answer"
                        onChange={e => {
                            handleChange12(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{ alignSelf: 'center' }}>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Do you have any changes in your skin or suffer from
                        allergies?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question13"
                        id="demo-simple-select"
                        value={gender13}
                        label="Answer"
                        onChange={e => {
                            handleChange13(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                    <InputLabel id="demo-simple-select-label">Did you dring alcohol in last 6 hours?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="question14"
                        id="demo-simple-select"
                        value={gender14}
                        label="Answer"
                        onChange={e => {
                            handleChange14(e);
                            handleChange(e)
                        }}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <FormControl style={{ width: '100ch', alignSelf: 'center' }}>
                <InputLabel id="demo-simple-select-label">Did you donated blood in last 6 months?</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    name="question15"
                    id="demo-simple-select"
                    value={gender15}
                    label="Answer"
                    onChange={e => {
                        handleChange15(e);
                        handleChange(e)
                    }}
                >
                    <MenuItem value={"Yes"}>Yes</MenuItem>
                    <MenuItem value={"No"}>No</MenuItem>
                </Select>
            </FormControl>
            <Button variant="contained" style={{ width: 200, alignSelf: 'center' }}
                onClick={saveDonorForm}>Submit</Button>
        </Stack>
    );
}