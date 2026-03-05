import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

// 🔹 Fetch
export const fetchData = (ruleName) => {
  return axios.get(`${BASE_URL}/fetch`, {
    params: { ruleName }
  });
};

// 🔹 Allow Update
export const allowUpdate = (ruleName, serialNo) => {
  return axios.get(`${BASE_URL}/allowUpdate`, {
    params: { ruleName, serialNo }
  });
};

// 🔹 Add
export const addData = (data) => {
  return axios.post(`${BASE_URL}/add`, data);
};

// 🔹 Update
export const updateData = (data) => {
  return axios.post(`${BASE_URL}/update`, data);
};

// 🔹 Delete
export const deleteData = (data) => {
  return axios.post(`${BASE_URL}/delete`, data);
};