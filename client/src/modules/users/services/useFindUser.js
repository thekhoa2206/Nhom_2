import { useState, useEffect } from 'react';
import axios from 'axios';
import { getCookie } from "../../../config";

export default function useFindUser() {
    const [user, setUser] = useState({});
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        let token = getCookie("jwt");
        if (token) {
            let i = axios.create();
            i.defaults.headers.common["Authorization"] = token;
            i.get('http://localhost:8080/api/users/info')
                .then(res => {
                    setUser(res.data);
                    setLoading(false);
                }).catch(err => {
                    console.log(err.response);
                    setLoading(false);
                });
        }
        else {
            setLoading(false)
        }

    }, []);

    return {
        user,
        setUser,
        isLoading
    }
}