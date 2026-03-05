/* import { allowUpdate } from "./tableApi";
import React.{useState} from "react";
import {
    fetchData, allowData
} from "../api/tableApi";

const TablePage = => 
{
    const[ ruleName , setruleName] = useState("");
    const [ serialNo , setSerialNo] = useState("")
    const [ tableData , setTableData] = useState([]);
 
    
//fetch
const handlenFetch = async () =>
{
    const response = await fetchData(ruleName);
    setTableData(response.data);

};

//allow update
const handleAllowUpdate = async () =>
{
    const response = await allowUpdate(ruleName , serialNo);
    if( response.data=== true)
    {
        alert("update allowed");
    }
    else{
        alert("update not allowed");
    }
};

return (

)
}*/
/* import { allowUpdate } from "./tableApi";
import React.{useState} from "react";
import {
    fetchData, allowData
} from "../api/tableApi";

const TablePage = => 
{
    const[ ruleName , setruleName] = useState("");
    const [ serialNo , setSerialNo] = useState("")
    const [ tableData , setTableData] = useState([]);
 
    
//fetch
const handlenFetch = async () =>
{
    const response = await fetchData(ruleName);
    setTableData(response.data);

};

//allow update
const handleAllowUpdate = async () =>
{
    const response = await allowUpdate(ruleName , serialNo);
    if( response.data=== true)
    {
        alert("update allowed");
    }
    else{
        alert("update not allowed");
    }
};

return (

)
}*/
import React, { useEffect, useState } from "react";
import { fetchByRule } from "./ruleApi";

const RuleTable = ({ ruleName }) => {

  const [data, setData] = useState([]);

  useEffect(() => {
    if (ruleName) {
      loadData();
    }
  }, [ruleName]);

  const loadData = async () => {
    try {
      const response = await fetchByRule(ruleName);
      setData(response.data);
    } catch (error) {
      console.error("Error fetching data", error);
    }
  };

  return (
    <div>
      <h2>{ruleName} Data</h2>

      <table border="1">
        <thead>
          <tr>
            <th>Serial No</th>
            <th>Percent</th>
            <th>Amount</th>
            <th>Defined Date</th>
            <th>Remarks</th>
            <th>System Date</th>
          </tr>
        </thead>

        <tbody>
          {data.map((row) => (
            <tr key={row.F_NSRNO}>
              <td>{row.F_NSRNO}</td>
              <td>{row.F_NPERCENTAGE}</td>
              <td>{row.F_NAMOUNT}</td>
              <td>{row.F_DTMONTHYEAR}</td>
              <td>{row.F_VC2REMARKS}</td>
              <td>{row.F_DTTRDATE}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default RuleTable;