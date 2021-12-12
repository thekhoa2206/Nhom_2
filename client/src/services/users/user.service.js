import axios from 'axios';
import { useState } from 'react';
import { clearCookie, getCookie } from "../../config";
import { useAppState } from '../../AppState';
import { toast } from '../../utils/snackbarUtils';

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

export function useFindUser() {
    const [state, dispatch] = useAppState()
    const findUser = () => {
        axios.get('/api/users/info')
            .then(res => {
                console.log("res", res)
                dispatch({ type: 'GET_USER', payload: { data: res.data } })
            }).catch(err => {
                console.log(err);
                clearCookie("jwt")
            });
    }
    return { findUser }
}

export function useGetAllUsers() {
    const [state, dispatch] = useAppState()
    const [isLoading, setIsLoading] = useState(true)
    const getAllUsers = () => {
        axios.get('api/users/list')
            .then(res => {
                console.log("res", res)
                dispatch({ type: 'GET_ALL_USERS', payload: { data: res.data } })
            }).catch(err => {
                toast.error("gọi fail")
            }).finally(() => {
                setIsLoading(false)
            });
    }
    return { getAllUsers, isLoading }
}