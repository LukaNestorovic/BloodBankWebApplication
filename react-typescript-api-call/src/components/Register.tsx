import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {
    Button,
    Container,
    FilledInput,
    FormControl,
    IconButton,
    InputAdornment,
    InputLabel, MenuItem,
    OutlinedInput,
    Paper, Select, SelectChangeEvent, Stack
} from "@mui/material";
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import {ChangeEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import UserService from "../services/UserService"

interface State {
    gender: string;
    password: string;
    email: string;
    showPassword: boolean;
}

export default function Register() {
    const [values, setValues] = React.useState<State>({
        gender:'',
        password: '',
        email: '',
        showPassword: false,
    });
    const[gender, setGender] = useState("")
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const navigate = useNavigate()

    const [user, setUser] = useState({
        id: "",
        email: "",
        password: "",
        repeatPassword: "",
        name: "",
        surname: "",
        address: "",
        city: "",
        country: "",
        phone: "",
        jmbg: "",
        gender: "",
        occupation: "",
        information: ""
    })

    const handleChange1 = (event: SelectChangeEvent) => {
        setGender(event.target.value as string);
    };

    const handleChange =
        (prop: keyof State) => (event: React.ChangeEvent<HTMLInputElement>) => {
            setValues({ ...values, [prop]: event.target.value });
        };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleChange2 = (e: any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };

    const handleChange3 = (e: any) => {
        const value = e.target.value;
        setUser({ ...user, [e.target.name]: value });
    };


    const saveUser = (e: any) => {
        e.preventDefault();
        UserService.saveEmployee(user)
            .then((response) => {
                console.log(response);
                //navigate("/register");
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };
    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Register</h1>
                <TextField id="outlined-basic" label="Email" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="email" onChange={(e) => handleChange2(e)} type="text"/>
                            <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                            <InputLabel htmlFor="filled-adornment-password" >Password</InputLabel>
                            <FilledInput
                                id="filled-adornment-password"
                                type={values.showPassword ? 'text' : 'password'}
                                name="password"
                                onChange={e => {handleChange('password'); handleChange2(e)}}
                                endAdornment={
                            <InputAdornment position="end" >
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                    />
                </FormControl>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password">Repeat password</InputLabel>
                    <FilledInput
                        id="filled-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        name="password"
                        onChange={e => {handleChange('password'); handleChange2(e)}}
                        endAdornment={
                            <InputAdornment position="end" >
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                    />
                </FormControl>
                <TextField id="outlined-basic" label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Surname" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="surname" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Address" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="address" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="City" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="city" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Country" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="country" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Phone" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="phone" onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="JMBG" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="jmbg" onChange={(e) => handleChange2(e)}/>
                <FormControl style={{width:'60ch', alignSelf:'center'}}>
                <InputLabel id="demo-simple-select-label">Gender</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    name="gender"
                    id="demo-simple-select"
                    value={gender}
                    label="Gender"
                    onChange={e => {handleChange1(e); handleChange2(e)}}
                >
                    <MenuItem value={"male"}>Male</MenuItem>
                    <MenuItem value={"female"}>Female</MenuItem>
                </Select>
                </FormControl>
                <TextField id="outlined-basic" label="Occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="occupation"  onChange={(e) => handleChange2(e)}/>
                <TextField id="outlined-basic" label="Information about occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="information" onChange={(e) => handleChange2(e)}/>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={saveUser}>Register</Button>
            </Stack>
        </Container>
    );
}
