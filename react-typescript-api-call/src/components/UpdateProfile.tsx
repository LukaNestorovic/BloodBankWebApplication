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

export default function UpdateProfile() {

    const [user, setUser] = useState({
        email: "",
        password: "",
        name: "",
        surname: "",
        address: "",
        city: "",
        country: "",
        phone: "",
        jmbg: "",
        gender: "",
        occupation: "",
        information: "",
        points: "",
        category: ""
    })

    const[showPassword,setShowPassword] = useState(false);

    const[passwordRepeat,setPasswordRepeat] = useState("");


    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };


    const handleChange = (e: any) => {
        setUser({ ...user,
        [e.target.name]: e.target.value });
    };


    const loadUser = () => {
        var storageMail =localStorage.getItem("email")

        if(storageMail!=null){
            user.email=storageMail
            UserService.getUser(user)
            .then((response) => {
                setUser({
                    email: response.data.email,
                    password: "",
                    name: response.data.name,
                    surname: response.data.surname,
                    address: response.data.address,
                    city: response.data.city,
                    country: response.data.country,
                    phone: response.data.phone,
                    jmbg: response.data.jmbg,
                    gender: response.data.gender,
                    occupation: response.data.occupation,
                    information: response.data.information,
                    points: response.data.card.points,
                    category: response.data.card.category
                });
            })
            .catch((error) => {
                console.log(error);
            });
        }
        else{
        alert("You arent signed in!");
        }
    };


    const updateUser = (e: any) => {
        e.preventDefault();
        if(user.password != passwordRepeat){
            alert("Passwords dont match!");
            return;
        }
        UserService.updateUser(user)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
                if(error.response.status==401){
                    alert("Wrong password!")
                }
            });
    };


    React.useEffect(() => {
        loadUser();
    },[]);
    
    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Update profile</h1>
                <TextField id="outlined-basic" value={user.email} label="Email" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="email" onChange={(e) => alert("You cant edit email!")} type="text"/>
                <TextField value={user.jmbg} id="outlined-basic" label="JMBG" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="jmbg" onChange={() => alert("You cant change JMBG!")}/>
                <TextField value={user.name} id="outlined-basic" label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={handleChange}/>
                <TextField value={user.surname} id="outlined-basic" label="Surname" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="surname" onChange={handleChange}/>
                <TextField value={user.address} id="outlined-basic" label="Address" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="address" onChange={handleChange}/>
                <TextField value={user.city} id="outlined-basic" label="City" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="city" onChange={handleChange}/>
                <TextField value={user.country} id="outlined-basic" label="Country" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="country" onChange={handleChange}/>
                <TextField value={user.phone} id="outlined-basic" label="Phone" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="phone" onChange={handleChange}/>
                <FormControl style={{width:'60ch', alignSelf:'center'}}>
                <InputLabel id="demo-simple-select-label">Gender</InputLabel>
                <Select
                    labelId="demo-simple-select-label"
                    name="gender"
                    id="demo-simple-select"
                    value={user.gender}
                    label="Gender"
                    onChange={handleChange}
                >
                    <MenuItem value={"male"}>Male</MenuItem>
                    <MenuItem value={"female"}>Female</MenuItem>
                </Select>
                </FormControl>
                <TextField value={user.occupation} id="outlined-basic" label="Occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="occupation"  onChange={handleChange}/>
                <TextField value={user.information} id="outlined-basic" label="Information about occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="information" onChange={handleChange}/>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password" >Old Password</InputLabel>
                        <FilledInput
                            id="filled-adornment-password"
                            type={showPassword ? 'text' : 'password'}
                            name="password"
                            onChange={handleChange}
                            endAdornment={
                                <InputAdornment position="end" >
                            <IconButton
                                aria-label="toggle password visibility"
                                onClick={handleClickShowPassword}
                                edge="end"
                            >
                                {showPassword ? <VisibilityOff /> : <Visibility />}
                            </IconButton>
                        </InputAdornment>
                    }
                />
                </FormControl>
                <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                    <InputLabel htmlFor="filled-adornment-password">Repeat password</InputLabel>
                    <FilledInput
                        id="filled-adornment-password"
                        type={showPassword ? 'text' : 'password'}
                        name="password"
                        onChange={e => {setPasswordRepeat(e.target.value)}}
                        endAdornment={
                            <InputAdornment position="end" >
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    edge="end"
                                >
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                                </IconButton>
                            </InputAdornment>
                        }
                    />
                </FormControl>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={updateUser}>Save</Button>
            </Stack>
        </Container>
    );
}
