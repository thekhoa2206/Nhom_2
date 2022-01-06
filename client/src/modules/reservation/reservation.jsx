import React, { useState, useEffect } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import Loading from '../../common-components/Loading';
import useStyles from "./reservation.styles";
import ReservationService from '../../services/reservation/reservation';
import DialogDetailReservation from './DialogDetailReservation/DialogDetailReservation';

function Reservation(props) {
    const classes = useStyles();
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
    const [reservationChoose, setReservationChoose] = useState({
        id: 0,
        nameCustomer: "",
        phoneCustomer: "",
        numberRoom: 0,
        fromDate: "",
        toDate: "",
        status: "",
    });
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
    const handleClick = (event, cellValues) => {
        console.log(cellValues);
    };

    const handleCellClick = (params) => {
        setShowDetailReservation(true);
        setReservationChoose(params.row)
    };

    const handleRowClick = (param, event) => {
        console.log("show")
        event.stopPropagation();
    };
    return (
        <React.Fragment>
            <React.Fragment>
                <Button sx={{ mb: 3 }} variant="contained" className={classes.buttonAdd}>
                    Đặt phòng
                </Button>
                <Box>
                    <DialogDetailReservation open={showDetailReservation} reservationChoose={reservationChoose} />
                </Box>
                <DataGrid
                    autoHeight
                    rows={reservations}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5]}
                    onCellClick={(param) => handleCellClick(param)}
                    onRowClick={handleRowClick}
                />
            </React.Fragment>

        </React.Fragment>

    );
}

export default Reservation;