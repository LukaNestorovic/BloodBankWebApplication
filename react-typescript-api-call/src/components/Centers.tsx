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
        <div className="container mx-auto my-8">
            <div className="h-12">

            </div>
            <div className="flex shadow border-b">
                <table className="min-w-full">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                            Id
                        </th>
                        <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                            Name
                        </th>
                        <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                            Address
                        </th>
                        <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                            Description
                        </th>
                        <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                            Rating
                        </th>
                    </tr>
                    </thead>
                    {!loading && (
                        <tbody className="bg-white">
                        {centers.map((center:any) => (
                            <Center
                                center={center}
                                key={center.id}/>
                        ))}
                        </tbody>
                    )}
                </table>
            </div>
        </div>
    );
};
