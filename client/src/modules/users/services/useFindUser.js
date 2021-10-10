import { useState, useEffect } from 'react';
import axios from 'axios';
import { getCookie } from "../../../config";

export default function useFindUser() {
    const [user, setUser] = useState(null);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        async function findUser() {
            let token = getCookie("jwt");
            let i = axios.create();
            i.defaults.headers.common["Authorization"] = token;
            await  i.get('http://localhost:8080/api/users/info')
                .then(res => {
                    setUser(res.data);
                    setLoading(false);
                }).catch(err => {
                    //console.log(err);
                    setLoading(false);
                });
        }

        findUser();
    }, []);

    return {
        user,
        setUser,
        isLoading
    }
}