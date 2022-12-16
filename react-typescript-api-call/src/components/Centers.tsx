import {useEffect, useState} from "react";
import CenterService from "../services/CenterService"
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TableSortLabel} from "@mui/material";
import Center from "./Center"
import {
    useNavigate
} from "react-router-dom";

export default function Centers(){

    const [loading, setLoading] = useState(true);
    const [centers, setCenters] = useState(null);
    const navigate = useNavigate()

    let role = localStorage.getItem("role")
    const enable = localStorage.getItem("enable")

    useEffect(() => {
        if(role === "user" && enable === "true"){
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
        }
        else{
            console.error("Access denied")
            navigate("/")
        }

    }, []);

    const [rowData, setRowData] = useState(centers);
    const [orderDirection, setOrderDirection] = useState("asc");

    const sortArrayRating = (arr:any, orderBy:any) => {
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

    const sortArrayName = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.name > b.name ? 1 : b.name > a.name ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.name < b.name ? 1 : b.name < a.name ? -1 : 0
                );
        }
    };

    const sortArrayAddress = (arr:any, orderBy:any) => {
        switch (orderBy) {
            case "asc":
            default:
                return arr.sort((a:any, b:any) =>
                    a.address > b.address ? 1 : b.address > a.address ? -1 : 0
                );
            case "desc":
                return arr.sort((a:any, b:any) =>
                    a.address < b.address ? 1 : b.address < a.address ? -1 : 0
                );
        }
    };

    const handleSortRequestRating = () => {
        setRowData(sortArrayRating(centers, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    const handleSortRequestName = () => {
        setRowData(sortArrayName(centers, orderDirection));
        setOrderDirection(orderDirection === "asc" ? "desc" : "asc");
    };

    const handleSortRequestAddress = () => {
        setRowData(sortArrayAddress(centers, orderDirection));
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
                        <TableCell align={"center"} onClick={handleSortRequestName}><TableSortLabel active={true} direction={"desc"}>
                            Name</TableSortLabel></TableCell>
                        <TableCell align={"center"} onClick={handleSortRequestAddress}>
                            <TableSortLabel active={true} direction={"desc"}>Address</TableSortLabel></TableCell>
                        <TableCell align={"center"}>Description</TableCell>
                        <TableCell align={"center"} onClick={handleSortRequestRating}>
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
