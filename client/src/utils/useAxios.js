// useAxios hook

import { useState, useEffect } from 'react';
import axios from 'axios';
import { getCookie } from '../config'
import { toast } from './snackbarUtils';

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

const useAxios = (options) => {
    const [response, setResponse] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    const fetchData = () => {
        const config = {
            method: options.method,
            url: options.url,
            data: options.data,
            params: options.params
        }
        axios(config)
            .then((res) => {
                toast.success("Gọi api thành công")
                setResponse(res.data);
            })
            .catch((err) => {
                toast.error("Gọi api fail")
            })
            .finally(() => {
                setIsLoading(false);
            });
    };

    useEffect(() => {
        fetchData();
    }, [options.method, options.url, JSON.stringify(options.params), JSON.stringify(options.data)]);

    return { response, isLoading };
};

export default useAxios;