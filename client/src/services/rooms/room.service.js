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
    const checkIn = async (data) => {
        await axios
        .post("/api/admin/check-in", data)
        .then(res => {
            console.log("Res.data", res)
            dispatch({ type: 'CHECK_IN', payload: {data: res.data.checkInResponse, code: 2} })
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
        .post("/api/admin/check-out", {roomId: room.id})
        .then(res => {
            dispatch({ type: 'CHECK_OUT', payload: { data: { id: room.id, roomName: room.roomName, typeRoomName: room.typeRoomName, status: 3, checkInTime: null, checkOutTime: null, deposit: null, servicesUsed: null, guests: null, sumOfPrices: null, additionalFee: null, reducedFee: null} } })
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
export function useBookRoom() {
    const dispatch = useDispatch();
    const bookRoom = (room) => {
        axios.get('api/admin/rooms/status', {params: {id: room.id, typeAction: 'book' } })
            .then(res => {
                dispatch({ type: 'BOOK_ROOM', payload: { code: 5, room } })
                toast.success("Phòng đã được đặt")   
            }).catch(err => {
                toast.error(err)
            })
       
    }
    return { bookRoom }
}
export function useUpdateServices() {
	const dispatch = useDispatch();
	const updateServices = (data) => {
		axios.post('api/admin/check-in/insert-services', data )
			.then(res => {
				dispatch({ type: 'UPDATE_SERVICES', payload: { data: res.data } })
				toast.success("Cập nhật dịch vụ thành công")
			}).catch(err => {
				toast.error("Cập nhật dịch vụ thất bại")
			})
	}
	return { updateServices }
}
export function useUpdateReservation() {
	const dispatch = useDispatch();
	const bookReservation = (reservationRoomDTORequest) => {
		axios.post('api/admin/reservations/room_reservation', reservationRoomDTORequest )
			.then(res => {
				dispatch({ type: 'BOOK_ROOM_RESERVATION', payload: { reservationRoomDTORequest: res.data } })
				toast.success("Đặt phòng thành công")
			}).catch(err => {
				toast.error("Đặt phòng thất bại")
			})
	}
	return { bookReservation }
}