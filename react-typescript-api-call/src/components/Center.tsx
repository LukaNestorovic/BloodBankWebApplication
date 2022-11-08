import React from "react";
import { useNavigate } from "react-router-dom";

const Center = (center:any) => {

    return (
        <tr key={center.id}>
        <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{center.name}</div>
            </td>
            <td className="text-left px-6 py-4 whitespace-nowrap">
    <div className="text-sm text-gray-500">{center.address}</div>
        </td>
        <td className="text-left px-6 py-4 whitespace-nowrap">
    <div className="text-sm text-gray-500">{center.description}</div>
        </td>
            <td className="text-left px-6 py-4 whitespace-nowrap">
                <div className="text-sm text-gray-500">{center.rating}</div>
            </td>
        <td className="text-right px-6 py-4 whitespace-nowrap font-medium text-sm">


        </td>
        </tr>
);
};

export default Center;