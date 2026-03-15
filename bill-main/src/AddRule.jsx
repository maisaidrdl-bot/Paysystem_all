import React, { useState } from "react";
import { addRule } from "./ruleApi";
import { useLocation, useNavigate } from "react-router-dom";

const AddRule = () => {

  const { state } = useLocation();
  const navigate = useNavigate();

  const ruleName = state.ruleName;

  const [percent, setPercent] = useState("");
  const [amount, setAmount] = useState("");
  const [remarks, setRemarks] = useState("");
  const [definedDate, setDefinedDate] = useState("");

  const handleSubmit = async () => {

const date = new Date(definedDate);
  date.setDate(1);

  const formattedDate = date.toISOString().split("T")[0]


    const data = {
      ruleType: ruleName,
      percent:percent,
      amount:amount,
      definedDate:formattedDate,
      remarks:remarks,
      
    };

    await addRule(data);

    navigate(-1);
  };

  return (

    <div>

      <h2>Add {ruleName}</h2>


{ruleName !== "RELIEF" && (
      <input
        placeholder="Percent"
        onChange={(e) => setPercent(e.target.value)}
      />
)}

      <br/>
{ruleName === "RELIEF" && (
      <input
        placeholder="Amount"
        onChange={(e) => setAmount(e.target.value)}
      />
)}
      <br/>


      <input
        placeholder="Remarks"
        onChange={(e) => setRemarks(e.target.value)}
      />

      <br/>

      <input
        type="date"
        onChange={(e) => setDefinedDate(e.target.value)}
      />

      <br/><br/>

      <button onClick={handleSubmit}>Save</button>

    </div>
  );
};

export default AddRule;