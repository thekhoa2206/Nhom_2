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
export function useCreateGuest() {
    const dispatch = useDispatch();
    const createGuest = async (data) => {
        await axios
        .post("/api/admin/guests", data)
        .then(res => {
            console.log("res", res.data)
            dispatch({ type: 'CREATE_GUEST', payload: { data: {...data, id: res.data.id} } })
            toast.success("Tạo mới khách thành công")
        })
        .catch((err) => {
            toast.error("Tạo mới khách thất bại")
        });
    }
    return { createGuest }
}
