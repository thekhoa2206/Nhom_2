import { useState, useEffect } from 'react';
import axios from 'axios';
import { clearCookie, getCookie } from "../../../config";
import { useAppState } from '../../../AppState';

export default function useFindUser() {
    const [state, dispatch] = useAppState()
    const findUser = () => {
        let token = getCookie("jwt");
        if (token) {
            let i = axios.create();
            i.defaults.headers.common["Authorization"] = token;
            i.get('http://localhost:8080/api/users/info')
                .then(res => {
                    dispatch({ type: 'GET_USER', payload: { data: res.data } })
                }).catch(err => {
                    console.log(err.response);
                    clearCookie();
                });
        }
    }
    return { findUser }
}