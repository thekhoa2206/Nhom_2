import React, { useState, useEffect } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import Loading from '../../common-components/Loading';
import { useAppState } from '../../AppState';
import { uniqueId } from 'lodash';
import useStyles from "./reservation.styles";

function Reservation(props) {
    const classes = useStyles();
    const [state1, setState] = useState(() => initState())
    const [state] = useAppState()
    const { price1, isOpenModal } = state1
    function initState() {
        return {
            users: [],
            price1: [],
            isOpenModal: false
        }
    }
    useEffect(() => {
        let params = {
            keyword: "vip",
            status: 1
        }
    }, [])

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
        { field: 'nameCustomer', headerName: 'Tên khách', type: 'text', width: 160 },
        {
            field: 'phoneNumber',
            headerName: 'Số điện thoại',
            headerAlign: 'center',
            type: 'text',
            width: 150,
        },
        {
            field: 'fromDate',
            headerName: 'Ngày đến',
            headerAlign: 'center',
            type: 'text',
            width: 160,
        },
        {
            field: 'toDate',
            headerName: 'Ngày đi',
            headerAlign: 'center',
            type: 'text',
            width: 160,
        },
        {
            field: 'numberRoom',
            headerName: 'Số phòng',
            headerAlign: 'center',
            type: 'text',
            width: 160,
        },
        {
            field: 'numberPeople',
            headerName: 'Số người',
            headerAlign: 'center',
            type: 'text',
            width: 160,
        },
        {
            field: 'status',
            headerName: 'Trạng thái',
            headerAlign: 'center',
            type: 'text',
            width: 160,
        },
    ];
    const handleClick = (event, cellValues) => {
        alert(cellValues.row.name);
    };

    const openModalAddNewUser = () => {
        setState({
            ...state1,
            isOpenModal: !isOpenModal
        })
    }

    const handleCellClick = (param, event) => {
        event.stopPropagation();
    };

    const handleRowClick = (param, event) => {
        event.stopPropagation();
    };
    return (
        <React.Fragment>
            <React.Fragment>
                    <Button sx={{ mb: 3 }} variant="contained" className={classes.buttonAdd} onClick={openModalAddNewUser}>
                        Đặt phòng
                    </Button>
                    <DataGrid
                        autoHeight
                        rows={price1}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        onCellClick={handleCellClick}
                        onRowClick={handleRowClick}
                    />
                </React.Fragment>
            
        </React.Fragment>

    );
}

export default Reservation;