import axios from 'axios';
import { clearCookie, getCookie } from "../../config";
import { useDispatch } from "react-redux";
import { toast } from '../../utils/snackbarUtils';

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

export function useGetAllRooms() {
    const dispatch = useDispatch();
    const getAllRooms = () => {
        axios.get('api/admin/rooms')
            .then(res => {
                dispatch({ type: 'GET_ALL_ROOMS', payload: { data: res.data } })
            }).catch(err => {
                toast.error("Lấy danh sách phòng thất bại")
            })
    }
    return { getAllRooms }
}
