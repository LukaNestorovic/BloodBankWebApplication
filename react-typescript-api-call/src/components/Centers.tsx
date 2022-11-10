import {useEffect, useState} from "react";
import CenterService from "../services/CenterService"
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TableSortLabel} from "@mui/material";
import Center from "./Center"

export default function Centers(){

    const [loading, setLoading] = useState(true);
    const [centers, setCenters] = useState(null);

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

    const [rowData, setRowData] = useState(centers);
    const [orderDirection, setOrderDirection] = useState("asc");

    const sortArray = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.rating > b.rating ? 1 : b.rating > a.rating ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.rating < b.rating ? 1 : b.rating < a.rating ? -1 : 0
                );
        }
    };

    const handleSortRequest = () => {
        setRowData(sortArray(centers, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    return(

        <TableContainer component={Paper}>
            <h1 style={{textAlign: 'center',
                alignSelf: 'center'}}>Centers</h1>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell align={"center"}>Id</TableCell>
                        <TableCell align={"center"} onClick={handleSortRequest}><TableSortLabel active={true} direction={"desc"}>
                            Name</TableSortLabel></TableCell>
                        <TableCell align={"center"} onClick={handleSortRequest}>
                            <TableSortLabel active={true} direction={"desc"}>Address</TableSortLabel></TableCell>
                        <TableCell align={"center"}>Description</TableCell>
                        <TableCell align={"center"} onClick={handleSortRequest}>
                            <TableSortLabel active={true} direction={"desc"}>Rating
                            </TableSortLabel>
                        </TableCell>
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
