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
export function useCheckIn() {
    const dispatch = useDispatch();
    const checkIn = async (data, FEdata) => {
        await axios
        .post("/api/admin/check-in", data)
        .then(res => {
            dispatch({ type: 'CHECK_IN', payload: {FEdata, code: 2} })
            toast.success("Nhận phòng thành công")
        })
        .catch((err) => {
            console.log("err", err)
            toast.error("Nhận phòng không thành công")
        });
    }
    return { checkIn }
}
export function useCheckOut() {
    const dispatch = useDispatch();
    
    const checkOut = async (room) => {
        await axios
        .post("/", room)
        .then(res => {
            dispatch({ type: 'CHECK_OUT', payload: { data: {...res.data, id: room.id, roomName: room.roomName, typeRoomName: room.typeRoomName, floor: room.floor} } })
            toast.success("Trả phòng thành công")
        })
        .catch((err) => {
            toast.error("Trả phòng không thành công")
        });
                
    }
    return { checkOut }
}
export function useReadyRoom() {
    const dispatch = useDispatch();
    const readyRoom = (room) => {
        axios.get('api/admin/rooms/status', {params: {id: room.id, typeAction: 'ready' } })
            .then(res => {
                dispatch({ type: 'READY_ROOM', payload: { code: 1, room } })
                toast.success("Phòng đã sẵn sàng")   
            }).catch(err => {
                toast.error(err)
            })
       
    }
    return { readyRoom }
}
export function useCleanRoom() {
    const dispatch = useDispatch();
    const cleanRoom = (room) => {
        axios.get('api/admin/rooms/status', {params: {id: room.id, typeAction: 'clean' } })
            .then(res => {
                dispatch({ type: 'CLEAN_ROOM', payload: { code: 3, room } })
                toast.success("Phòng đang được dọn")   
            }).catch(err => {
                toast.error(err)
            })
       
    }
    return { cleanRoom }
}
export function useFixRoom() {
    const dispatch = useDispatch();
    const fixRoom = (room) => {
        axios.get('api/admin/rooms/status', {params: {id: room.id, typeAction: 'edit' } })
            .then(res => {
                dispatch({ type: 'FIX_ROOM', payload: { code: 4, room } })
                toast.success("Phòng đang được sửa chữa")   
            }).catch(err => {
                toast.error(err)
            })  
    }
    return { fixRoom }
}