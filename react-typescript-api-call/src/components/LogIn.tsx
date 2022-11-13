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
    InputLabel,
    OutlinedInput,
    Paper, Stack
} from "@mui/material";
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import {useState} from "react";
import {useNavigate} from "react-router-dom";

interface State {
    password: string;
    email: string;
    showPassword: boolean;
}

export default function LogIn() {
    const paperStyle={alignItems: 'center'}
    const [values, setValues] = React.useState<State>({
        password: '',
        email: '',
        showPassword: false,
    });
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const navigate = useNavigate()


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

    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };
    return (
        <Container>
                    <Stack direction="column" spacing={1}>
                        <h1 style={{alignSelf:'center'}}>LogIn</h1>
                        <TextField id="outlined-basic" label="Email" variant="filled" style={{width:'60ch', alignSelf:'center'}} value={email}/>
                        <FormControl variant="filled" style={{width:'60ch', alignSelf:'center'}}>
                        <InputLabel htmlFor="filled-adornment-password" >Password</InputLabel>
                        <FilledInput
                        id="filled-adornment-password"
                        type={values.showPassword ? 'text' : 'password'}
                        value={values.password}
                        onChange={handleChange('password')}
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
                        <Button variant="contained" style={{width:200, alignSelf:'center'}}>LogIn</Button>
                        <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/register")}>Register</Button>
                        <Button variant="contained" style={{width:200, alignSelf:'center'}} onClick={() => navigate("/global-centers")}>Centers</Button>
                    </Stack>
        </Container>
    );
}
