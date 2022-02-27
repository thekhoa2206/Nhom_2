import React, { useState, useEffect } from 'react';
import { Dialog, Typography } from '@mui/material';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { DataGrid } from '@mui/x-data-grid';
import Grid from '@mui/material/Grid';
import ModalAddGuestInfo from './modalAddGuestInfo'
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import ControlPointIcon from '@mui/icons-material/ControlPoint';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';
import InputAdornment from "@material-ui/core/InputAdornment";
import SearchIcon from "@material-ui/icons/Search";
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { services } from '../../utils/constants';
import * as dayjs from 'dayjs'
import Autocomplete from '@mui/material/Autocomplete';
import { useSelector } from "react-redux";
import { useCheckIn, useGetAllRooms } from '../../services/rooms/room.service';
import { useCreateGuest } from '../../services/guests/guest.service';
import ReservationService from '../../services/reservation/reservation';

function BookRoomModal(props) {
    const { open, room } = props;
    const guestList = useSelector((state) => state.guestReducer.guestList);
    const { checkIn } = useCheckIn()
    const { createGuest } = useCreateGuest()
    const { getAllRooms } = useGetAllRooms()
    const [selectedRows, setSelectedRows] = useState([]);
    const [state, setState] = useState({
        guests: [],
        isOpenModalAddGuest: false,
        servicesUsed: [],
        serviceTotal: 0,
        deposit: 0,
        additionalFee: 0,
        reducedFee: 0,
        checkInTime: dayjs().format('YYYY-MM-DDTHH:mm'),
        checkOutTime: dayjs().add(1, 'days').format('YYYY-MM-DDTHH:mm')
    })
    const [reservations, setReServations] = useState([
        {
            id: 0,
            nameCustomer: "",
            phoneCustomer: "",
            numberRoom: 0,
            fromDate: "",
            toDate: "",
            status: "",
        }
    ])
    const [filters, setFilters] = useState({
        keyword: ""
    });
    const [showDetailReservation, setShowDetailReservation] = useState(false);
    const [showAddNewReservation, setShowAddNewReservation] = useState(false);
    const [reservationChoose, setReservationChoose] = useState({
        id: 0,
        nameCustomer: "",
        phoneCustomer: "",
        numberRoom: 0,
        fromDate: "",
        toDate: "",
        status: "",
    });
    const { isOpenModalAddGuest, guests, servicesUsed, serviceTotal, deposit, checkInTime, checkOutTime, additionalFee, reducedFee } = state
    
    const servicesColumn = [
        {
            flex: 1,
            field: " ",
            sortable: false,
            filterable: false,
            disableClickEventBubbling: true,
            headerAlign: 'center',
            align: "center",
            renderCell: (cellValues) => {
                return (
                    <IconButton
                        style={{ alignContent: "center" }}
                        size="small"
                        onClick={(event) => {
                        }}
                    >
                        <ControlPointIcon style={{ fill: "#1769aa" }} />
                    </IconButton>
                );
            }
        },
        {
            field: 'name', headerName: 'Dịch vụ', width: 150, flex: 1, sortable: false,
            filterable: false,
        },
        {
            field: 'price', headerName: 'Giá tiền', width: 130, sortable: false,
            filterable: false, flex: 1,
        },
        {
            field: 'stockQuantity', headerName: 'Số lượng kho', width: 150, sortable: false,
            filterable: false, flex: 1,
        },

    ];
    const handleClose = () => {
        props.handleClose()
    }
    const handleSubmit = () => {
        props.handleSubmit()
        let guestIds = guests?.map(s => s.id) || []
        let servicesData = servicesUsed?.map(s => ({ serviceId: s.id, quantity: s.quantity, paid: true })) || []
        let data = {
            billId: 0,
            checkInTime: dayjs(checkInTime).valueOf(),
            checkOutTime: dayjs(checkOutTime).valueOf(),
            deposit,
            additionalFee,
            reducedFee,
            guestIds,
            roomId: room.id,
            servicesUsed: servicesData
        }
        console.log("data", data)
        checkIn(data)
        getAllRooms()

    }
    const handleClick = (event, cellValues) => {
        alert(cellValues.row.name);
    };
    const handleCellClick = (param, event) => {
        event.stopPropagation();
    };
    const handleRowClick = (param, event) => {
        event.stopPropagation();
    };
 
    useEffect(() => {
        try {
            ReservationService.getListReservationByParam(filters).then((res) => {
                console.log(res.data)
                setReServations(
                    res.data.map((reservation) => {
                        return {
                            id: reservation.id,
                            nameCustomer: reservation.reservationGuestDTO.nameCustomer,
                            phoneCustomer: reservation.reservationGuestDTO.phoneCustomer,
                            numberRoom: reservation.numberRoom,
                            fromDate: reservation.fromDate,
                            toDate: reservation.toDate,
                            status: reservation.status,
                        }
                    })
                )
            })
        } catch (error) {

        }
    }, [filters])
    const columns = [
        {
            field: " ",
            width: 50,
            sortable: false,
            filterable: false,
            disableClickEventBubbling: true,
            headerAlign: 'center',
            align: "center",
            renderCell: (cellValues) => {
                return (
                    <IconButton
                        style={{ alignContent: "center" }}
                        size="small"
                        onClick={(event) => {
                            handleClick(event, cellValues);
                        }}
                    >
                        <EditIcon style={{ fill: "orange" }} />
                    </IconButton>
                );
            }
        },
        { field: 'nameCustomer', headerName: 'Tên khách', type: 'text', width: 180, headerAlign: 'center', align: "center", },
        {
            field: 'phoneCustomer',
            headerName: 'Số điện thoại',
            headerAlign: 'center',
            type: 'text',
            minWidth: 150,
            align: "center",
        },
        {
            field: 'numberRoom',
            headerName: 'Số phòng',
            headerAlign: 'center',
            type: 'text',
            minWidth: 160,
            align: "center",
        },
        {
            field: 'fromDate',
            headerName: 'Ngày đến',
            headerAlign: 'center',
            type: 'text',
            minWidth: 220,
            align: "center",
        },
        {
            field: 'toDate',
            headerName: 'Ngày đi',
            headerAlign: 'center',
            type: 'text',
            minWidth: 220,
            align: "center",
        },
        {
            field: 'status',
            headerName: 'Trạng thái',
            headerAlign: 'center',
            type: 'text',
            minWidth: 160,
            align: "center",
        }
    ];
    const defaultProps = {
        options: guestList,
        getOptionLabel: (option) => option.lastName + " " + option.firstName
    }
    console.log("state", state)
    
    return (
        <Dialog open={open} onClose={handleClose} fullWidth maxWidth='lg' >
            <DialogTitle align="center">Phòng {room.roomName}</DialogTitle>
            <DialogContent>
                <Grid sx={props.styleZoom}>
                <DataGrid
                    autoHeight
                    rows={reservations}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    onCellClick={(param) => handleCellClick(param)}
                    onRowClick={handleRowClick}
                />
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button variant="outlined" color="primary" onClick={() => handleClose()}>Đóng</Button>
                <Button variant="contained" color="primary" onClick={() => handleSubmit()}>Nhận phòng</Button>
            </DialogActions>
        </Dialog >
    )
    // console.log("open 2", open)
    // return (


    // );
}
React.memo(BookRoomModal, (props, nextProps) => {
    if (JSON.stringify(props) === JSON.stringify(nextProps)) {
        // don't re-render/update
        return true
    }
})

export default BookRoomModal;