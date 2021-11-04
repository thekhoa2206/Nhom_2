
import axios from 'axios';
import { getCookie } from '../config'
import { toast } from './snackbarUtils';
import { useState } from 'react';
import { useAppState } from "../AppState";

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;
/** Gọi API mẫu:
    export function xxxxxx() {
        const { response, isLoading } = useAxios({
            method: 'xxxxxx',
            url: "xxxxxx",
            data: {xxxxxx},
            params:{xxxxxxx}
        })
        return {
            xxxxx: response,
            isLoading: isLoading
        }
    }
 */
// export function useGetAllUsers() {
//     const { response, isLoading } = useAxios({
//         method: 'get',
//         url: "/api/users/list",
//         data: {}
//     })
//     return {
//         data: response,
//         isLoading: isLoading
//     }
// }
export function useGetAllPrice() {
    const [state, dispatch] = useAppState()
    const [isLoading, setIsLoading] = useState(true)
    const getAllPrice = async (params) => {

        axios.get("/api/admin/price/list", { params })
            .then(res => {
                dispatch({ type: 'GET_ALL_PRICE', payload: res.data })
                toast.success("Gọi api thành công")
            })
            .catch((err) => {
                toast.error("Gọi api thất bại")
            }).finally(() => {
                setIsLoading(false)
            })
    }

    return {
        isLoading,
        getAllPrice
    }
}
export function useCreatePrice() {
    const [state, dispatch] = useAppState()
    const createPrice = async (data) => {
        axios
            .post("/api/admin/price", data)
            .then(res => {
                dispatch({ type: 'ADD_PRICE', payload: { name: "gia vip vcl", price: 69696 } })
                toast.success("Gọi api thành công")
            })
            .catch((err) => {
                toast.error("Gọi api thất bại")
            });
    }
    return { createPrice }
}
export function useGetLyric2() {
    // const { response, isLoading } = useAxios({
    //     method: 'get',
    //     url: "https://api.lyrics.ovh/v1/Coldplay/Fix You",
    //     data: {}
    // })
    // return {
    //     lyrics: response?.lyrics,
    //     isLoading: isLoading
    // }
}