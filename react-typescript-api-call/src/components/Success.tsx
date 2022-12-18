import React, {useEffect, useState} from "react";
import UserService                  from "../services/UserService";
import {useNavigate} from "react-router-dom";
import {Container}   from "@mui/material";

const Success = () => {

    const navigate = useNavigate()

    const [dto, setDto] = useState({
        email: localStorage.getItem("email")
    })

    const email = localStorage.getItem("email")

    useEffect(() => {
        if(email == null) {
            console.error("Access denied")
            navigate("/")
        }
    },[])

    const home = (e: any) => {
        e.preventDefault();
        UserService.updateEnable(dto)
            .then((response) => {
            console.log(response);
            navigate("/")
        }).catch((error) => {
            console.log(localStorage.getItem("email"))
            console.log(error);
        })
    }

    return(
        <Container>
            <h1>You're account is verified</h1>
            <button onClick={home}>LogIn</button>
        </Container>
    );
}

export default Success;