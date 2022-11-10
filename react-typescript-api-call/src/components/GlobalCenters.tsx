import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import CenterService from "../services/CenterService"
import { useState } from 'react';




export default function BasicTable() {

    interface Centers {
        center: {
            name: string
            address: number
            description: string
            rating: number
        }[]
      }

    const[centers, setCenters] = useState<Centers["center"]>();

    React.useEffect(() => {
        CenterService.getCenters().
            then((response) => {
                console.log(response.data);
                setCenters(response.data)
            })
            .catch((error) => {
                console.log(error);
            });
    },[]);

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650,maxWidth: 950, margin: "auto"}} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="right">Address</TableCell>
            <TableCell align="right">Description</TableCell>
            <TableCell align="right">Rating</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {centers?.map((row) => (
            <TableRow
              key={row.name}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell align="right">{row.address}</TableCell>
              <TableCell align="right">{row.description}</TableCell>
              <TableCell align="right">{row.rating}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
