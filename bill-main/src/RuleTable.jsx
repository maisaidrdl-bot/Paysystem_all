import React, { useEffect, useState } from "react";
import { fetchByRule, deleteRule, allowUpdate } from "./ruleApi";
import { useNavigate } from "react-router-dom";

const RuleTable = ({ ruleName }) => {

  const [data, setData] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {

    if (ruleName) {
      loadData();
    }

  }, [ruleName]);

  const loadData = async () => {

    try {

      const res = await fetchByRule(ruleName);

      setData(res.data);

    } catch (error) {

      console.error("Fetch error", error);

    }
  };

  const handleDelete = async (serialNo) => {

    const check = await allowUpdate(ruleName, serialNo);

    if (!check.data) {

      alert("Delete not allowed. Month mismatch.");

      return;

    }

    await deleteRule(ruleName, serialNo);
   
const confirmDelete = window.confirm("Are you sure to delete this rule?");

if (!confirmDelete) {
  return;
}


    loadData();
  };

  const handleUpdate = async (serialNo) => {

    const check = await allowUpdate(ruleName, serialNo);

    if (!check.data) {

      alert("Update not allowed. Month mismatch.");

      return;

    }

    navigate("/update", {
      state: { ruleName, serialNo }
    });
  };

  return (

    <div>

      <h2>RULE TYPE - {ruleName}</h2>

      <button
        onClick={() => navigate("/add", { state: { ruleName } })}
      >
        Add Rule
      </button>

      <table border="1">

        <thead>

          <tr>

            <th>Serial No</th>

            {/*<th>Percent</th>

            <th>Amount</th>

            <th>Remarks</th>*/}

            {ruleName !== "RELIEF" && <th>Percent</th>}
{ruleName === "RELIEF" && <th>Amount</th>}
<th>Effective Date</th>
<th>Transaction Date</th>
<th>Remarks</th>

            <th>Actions</th>

          </tr>

        </thead>

        <tbody>

          {data.map((row) => (

            <tr key={row.serialNo}>

              <td>{row.serialNo}</td>

              {/*<td>{row.percent}</td>

              <td>{row.amount}</td>

              <td>{row.remarks}</td>*/}

              {ruleName !== "RELIEF" && <td>{row.percent}</td>}
             {ruleName === "RELIEF" && <td>{row.amount}</td>}

          <td>{row.definedDate?.split(" ")[0]}</td>
          <td>{row.systemDate?.split(" ")[0]}</td>
          <td>{row.remark}</td>

              <td>

                <button
                  onClick={() => handleUpdate(row.serialNo)}
                >
                  Update
                </button>

                <button
                  onClick={() => handleDelete(row.serialNo)}
                >
                  Delete
                </button>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>

  );
};

export default RuleTable;
