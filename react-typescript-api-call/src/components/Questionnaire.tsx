import * as React from 'react';
import TextField from "@mui/material/TextField";
import {Container, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, Stack} from "@mui/material";
import {useState} from "react";
import {DatePicker} from "@mui/lab";



export default function Questionnaire(){
    const [startDate, setStartDate] = useState(new Date());
    const[gender, setGender] = useState("")
    const handleChange1 = (event: SelectChangeEvent) => {
        setGender(event.target.value as string);
    };
    const[answer, setAnswer] = useState("")

    return(
        <Stack direction="column" spacing={1}>
            <h1 style={{textAlign: 'center', alignSelf: 'center'}}>Questionnaire for blood donor</h1>
            <TextField id="outlined-basic" label="Date" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
            <TextField id="outlined-basic" label="Surname (parent name) Name" variant="outlined" style={{alignSelf:'start'}} name="date" type="text" fullWidth={true} inputProps={{style:{textAlign : 'center'}}}/>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <TextField id="outlined-basic" label="JMBG" variant="outlined" style={{width:'50ch', alignSelf:'start'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <TextField id="outlined-basic" label="Date of birth" variant="outlined" style={{width:'50ch'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Gender</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={gender}
                        label="Gender"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"male"}>Male</MenuItem>
                        <MenuItem value={"female"}>Female</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <TextField id="outlined-basic" label="Address" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <TextField id="outlined-basic" label="City" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <TextField id="outlined-basic" label="Phone" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <TextField id="outlined-basic" label="Mobile phone" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <TextField id="outlined-basic" label="Occupation" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <TextField id="outlined-basic" label="Information" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
                <TextField id="outlined-basic" label="Number of previous donations" variant="outlined" style={{width:'50ch', alignSelf:'end'}} name="date" type="text" inputProps={{style:{textAlign : 'center'}}}/>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Did you ever donated blood?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="answer"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Did you ever been declined as blood donor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Do you feel healthy to be a blood donor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Did you eat something before coming here?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Do you have dangerous job or hobby?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="answer"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Do you use some type of medication every day?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Did you take any medicine in last 2-3 days?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Did you pull tooth in last 7 days?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Have you suddenly lost weight in the last 6 months?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="answer"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'60ch'}}>
                    <InputLabel id="demo-simple-select-label">Have you ever had thyroid, pituitary, and/or hormone problems?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'50ch'}}>
                    <InputLabel id="demo-simple-select-label">Do you suffer from any chronic disease?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Stack direction="row" spacing={1} style={{alignSelf:'center'}}>
                <FormControl style={{width:'70ch'}}>
                    <InputLabel id="demo-simple-select-label">Have you had tick bites in the past 12 months and have you seen a doctor?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
                <FormControl style={{width:'70ch'}}>
                    <InputLabel id="demo-simple-select-label">Do you have any changes in your skin or suffer from allergies?</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        name="gender"
                        id="demo-simple-select"
                        value={answer}
                        label="Answer"
                        onChange={e => {handleChange1(e)}}
                    >
                        <MenuItem value={"Yes"}>Yes</MenuItem>
                        <MenuItem value={"No"}>No</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
        </Stack>
    );
}