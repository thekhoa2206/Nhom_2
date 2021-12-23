import axios from 'axios';
import { clearCookie, getCookie } from "../../config";
import { useDispatch } from "react-redux";
import { toast } from '../../utils/snackbarUtils';

let token = getCookie("jwt")
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common["Authorization"] = token;

export function useGetAllGuests() {
    const dispatch = useDispatch();
    const getAllGuests = () => {
        axios.get('api/admin/guests')
            .then(res => {
                console.log("res", res)
                dispatch({ type: 'GET_ALL_GUESTS', payload: { data: res.data } })
            }).catch(err => {
                toast.error("Lấy danh sách khách hàng thất bại")
            })
    }
    return { getAllGuests }
}
