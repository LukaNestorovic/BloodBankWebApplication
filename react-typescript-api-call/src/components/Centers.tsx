import {useEffect, useState} from "react";
import CenterService from "../services/CenterService"
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Center from "./Center"

export default function Centers(){

    const [loading, setLoading] = useState(true);
    const [centers, setCenters] = useState(null);
//    const [center, setCenter] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const response = await CenterService.getCenters();
                setCenters(response.data);
            } catch (error) {
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    return(

        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Centers</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"}>Name</TableCell>
                        <TableCell align={"center"}>Address</TableCell>
                        <TableCell align={"center"}>Description</TableCell>
                        <TableCell align={"center"}>Rating</TableCell>
                    </TableRow>
                </TableHead>
                    {!loading && (
                        <TableBody>
                        {centers.map((center:any) => (
                            <Center
                                center={center}
                                key={center.id}/>
                        ))}
                        </TableBody>
                    )}
                </Table>
            </TableContainer>
    );
};
