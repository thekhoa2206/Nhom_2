import axios from 'axios';
import { clearCookie, getCookie } from "../../config";
import { useAppState } from '../../AppState';

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

export default function useFindUser() {
    const [state, dispatch] = useAppState()
    const findUser = () => {
        axios.get('/api/users/info')
            .then(res => {
                console.log("res", res)
                dispatch({ type: 'GET_USER', payload: { data: res.data } })
            }).catch(err => {
                console.log(err);
                clearCookie("jwt");
            });
    }
    return { findUser }
}