import React from "react";
import { useNavigate } from "react-router-dom";
import {TableCell, TableRow} from "@mui/material";

// @ts-ignore
const Center = ({center}) => {

    return (
        <TableRow key={center.id}>
            <TableCell align={"center"}>
                <div>{center.id}</div>
            </TableCell >
        <TableCell align={"center"}>
        <div>{center.name}</div>
            </TableCell>
            <TableCell align={"center"}>
    <div >{center.address}</div>
        </TableCell>
        <TableCell align={"center"}>
    <div >{center.description}</div>
        </TableCell>
            <TableCell align={"center"}>
                <div >{center.rating}</div>
            </TableCell>
        <td >


        </td>
        </TableRow>
);
};

export default Center;