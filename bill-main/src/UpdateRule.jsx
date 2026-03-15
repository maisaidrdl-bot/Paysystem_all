import React, { useState } from "react";
import { updateRule } from "./ruleApi";
import { useLocation, useNavigate } from "react-router-dom";

const UpdateRule = () => {

  const { state } = useLocation();
  const navigate = useNavigate();

  const { ruleName, serialNo } = state;

  const [percent, setPercent] = useState("");
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");

  const handleUpdate = async () => {

    const data = {
      ruleType: ruleName,
      id: serialNo,
      percent,
      amount,
      remarks
    };

    await updateRule(data);


const confirmUpdate = window.confirm("Are you sure to update this rule?");

if (!confirmUpdate) {
  return;
}



    navigate(-1);
  };

  return (

    <div>

      <h2>Update Rule</h2>



{ruleName !== "RELIEF" && (
      <input
        placeholder="Percent"
        onChange={(e) => setPercent(e.target.value)}
      />)}

      <br/>

{ruleName === "RELIEF" && (
      <input
        placeholder="Amount"
        onChange={(e) => setAmount(e.target.value)}
      />)}

      <br/>

      <input
        placeholder="Remarks"
        onChange={(e) => setRemarks(e.target.value)}
      />

      <br/><br/>

      <button onClick={handleUpdate}>
        Update
      </button>

    </div>

  );
};

export default UpdateRule;