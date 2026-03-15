import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

// FETCH
export const fetchByRule = (ruleName) => {
  return axios.get(`${BASE_URL}/fetch`, {
    params: { ruleName }
  });
};

// ADD
export const addRule = (data) => {
  return axios.post(`${BASE_URL}/add`, data);
};

// UPDATE
export const updateRule = (data) => {
  return axios.put(`${BASE_URL}/update`, data);
};

// DELETE
export const deleteRule = (ruleName, serialNo) => {
  return axios.delete(`${BASE_URL}/delete`, {
    params: { ruleName, serialNo }
  });
};

// CHECK UPDATE ALLOWED
export const allowUpdate = (ruleName, serialNo) => {
  return axios.get(`${BASE_URL}/allowUpdate`, {
    params: { ruleName, serialNo }
  });
};