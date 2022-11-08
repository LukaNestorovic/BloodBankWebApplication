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


export default function UpdateProfile() {
    const [values, setValues] = React.useState<State>({
        gender:'',
        password: '',
        email: '',
        showPassword: false,
    });

    const navigate = useNavigate()

    const[name,setName] = useState("")
    const[surname,setSurname] = useState("")
    const[email, setEmail] = useState("")
    const[address, setAddress] = useState("")
    const[city, setCity] = useState("")
    const[country, setCountry] = useState("")
    const[phone, setPhone] = useState("")
    const[gender, setGender] = useState("")
    const[occupation, setOccupation] = useState("")
    const[information, setInformation] = useState("")
    const[jmbg, setJmbg] = useState("")
    const[points, setPoints] = useState("")
    const[category, setCategory] = useState("")
    const[password, setPassword] = useState("")
    const[passwordRepeat, setPasswordRepeat] = useState("")


    const [loyalty, setLoyalty] = useState({
        id:"",
        points: 0, 
        category: ""
    })

    const [user, setUser] = useState({
        id: "",
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
        loyalty
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

    const loadUser = () => {
        var storageMail =localStorage.getItem("email")
        if(storageMail!=null){
            user.email=storageMail
            UserService.getUser(user)
            .then((response) => {
                console.log(response);
                setName(response.data.name);
                setSurname(response.data.name);
                setEmail(response.data.email);
                setAddress(response.data.address);
                setCategory(response.data.category);
                setCity(response.data.city);
                setCountry(response.data.country);
                setGender(response.data.gender);
                setInformation(response.data.information);
                setJmbg(response.data.jmbg);
                setOccupation(response.data.occupation);
                setPhone(response.data.phone);
            })
            .catch((error) => {
                console.log(error);
            });
        }
        
    };
    const updateUser = (e: any) => {
        if(password != passwordRepeat){
            alert("Passwords dont match!");
        }
        user.name=name;
        user.surname=surname;
        user.password=password;
        user.address=address;
        user.city=city;
        user.country=country;
        user.phone=phone;
        user.gender=gender;
        user.occupation=occupation;
        user.information=information;
        e.preventDefault();
        UserService.updateUser(user)
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

    React.useEffect(() => {
        // call api or anything
        loadUser();
     },[]);

    return (
        <Container>
            <Stack direction="column" spacing={1}>
                <h1 style={{alignSelf:'center'}}>Update profile</h1>
                <TextField id="outlined-basic" value={email} label="Email" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="email" onChange={(e) => alert("You cant edit email!")} type="text"/>
                            <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                            <InputLabel htmlFor="filled-adornment-password" >Password</InputLabel>
                            <FilledInput
                                id="filled-adornment-password"
                                type={values.showPassword ? 'text' : 'password'}
                                name="password"
                                onChange={e => {handleChange('password'); setPassword(e.target.value)}}
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
                        onChange={e => {handleChange('password'); setPasswordRepeat(e.target.value)}}
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
                <TextField value={name} id="outlined-basic" label="Name" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="name" onChange={(e) => setName(e.target.value)}/>
                <TextField value={surname} id="outlined-basic" label="Surname" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="surname" onChange={(e) => setSurname(e.target.value)}/>
                <TextField value={address} id="outlined-basic" label="Address" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="address" onChange={(e) => setAddress(e.target.value)}/>
                <TextField value={city} id="outlined-basic" label="City" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="city" onChange={(e) => setCity(e.target.value)}/>
                <TextField value={country} id="outlined-basic" label="Country" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="country" onChange={(e) => setCountry(e.target.value)}/>
                <TextField value={phone} id="outlined-basic" label="Phone" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="phone" onChange={(e) => setPhone(e.target.value)}/>
                <TextField value={jmbg} id="outlined-basic" label="JMBG" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="jmbg" onChange={(e) => alert("You cant change JMBG!")}/>
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
                <TextField value={occupation} id="outlined-basic" label="Occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="occupation"  onChange={(e) => setOccupation(e.target.value)}/>
                <TextField value={information} id="outlined-basic" label="Information about occupation" variant="filled" style={{width:'60ch', alignSelf:'center'}} name="information" onChange={(e) => setInformation(e.target.value)}/>
                <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={updateUser}>Save</Button>
            </Stack>
        </Container>
    );
}
