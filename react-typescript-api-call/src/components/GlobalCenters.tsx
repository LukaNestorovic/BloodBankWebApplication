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
import { Button, TextField } from '@mui/material';
import { Box } from '@mui/system';

export default function BasicTable() {

  interface Centers {
    center: {
        name: string
        address: string
        description: string
        rating: number
    }[]
  }

  interface Query{
    name: string
    address: string
    description: string
    rating: number
  }
  
  const[centers, setCenters] = useState<Centers["center"]>();

  const[query, setQuery] = useState<Query>({
    name:"",
    address:"",
    description:"",
    rating:0
  });

  const handleQuery = (event: any) => {
    setQuery(({
      ...query,
      [event.target.name] : event.target.value
    }));
  }

  const handleSearch = () => {
    CenterService.getCentersGlobalFilter(query).
      then((response) => {
        console.log(response)
        if(response.data==""){
          setCenters([]);
          return;
        }
        setCenters(response.data);
      })
      .catch((error) =>{
        console.log(error);
      })
  }

  React.useEffect(() => {
    console.log(query.name + " " + query.address + " " + query.description + " " + query.rating);
  }, [query]);
  
  React.useEffect(() => {
    CenterService.getCentersGlobal().
      then((response) => {
        console.log(response.data);
        setCenters(response.data)
      })
      .catch((error) => {
        console.log(error);
      });
  },[]);

  return (
    <Box>
      <Box display="flex" justifyContent="center" sx={{pt:5}}>
        <TextField onChange={handleQuery} name="name" id="outlined-basic" label="Name" variant="outlined" />
        <TextField onChange={handleQuery} name="address" id="outlined-basic" label="Address" variant="outlined" />
        <TextField onChange={handleQuery} name="description" id="outlined-basic" label="Description" variant="outlined" />
        <TextField onChange={handleQuery} name="rating" id="outlined-basic" label="Rating" variant="outlined" />
        <Button variant="contained" onClick={handleSearch}>Search (Refresh)</Button>
      </Box>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650,maxWidth: 1250, margin: "auto"}} aria-label="simple table">
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
    </Box>
  );
}
