import React, { useState, useEffect } from 'react';
import { Dialog, Typography } from '@mui/material';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import Grid from '@mui/material/Grid';
import IconButton from '@mui/material/IconButton';
import ControlPointIcon from '@mui/icons-material/ControlPoint';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { useSelector } from "react-redux";


function EditInfoRoomModal(props) {
    const { open, room } = props
    const services = useSelector((state) => state.authReducer.services);
    const [state, setState] = useState({
        servicesUsed: room.servicesUsed ? room.servicesUsed : [],
        serviceTotal: 0,
    })
    const { servicesUsed, serviceTotal } = state
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
                            handleAddItem(event, cellValues);
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
            valueGetter: (params) =>
                `${numberWithCommas(params.row.price)}`,

        },

    ];
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    const handleAddItem = (event, cellValues) => {
        let idArr = servicesUsed.map(a => a.id)
        let rowId = cellValues.row.id
        let newServiceTotal = 0;
        //nếu đã có sp thì +1
        if (idArr.includes(rowId)) {
            let item = servicesUsed.find(x => x.id === rowId)
            let updatedItem = {
                ...item,
                quantity: item.quantity + 1
            }
            let newServicesUsed = servicesUsed.filter(function (item) {
                return item.id != rowId;
            });
            newServicesUsed.push(updatedItem)
            newServicesUsed.sort(function (a, b) {
                return a.id - b.id || a.name.localeCompare(b.name);
            });
            for (const item of newServicesUsed) {
                console.log("newServiceTotal", newServiceTotal)
                newServiceTotal += item.quantity * item.price
            }
            setState({
                ...state,
                servicesUsed: newServicesUsed,
                serviceTotal: newServiceTotal
            })
        }
        //chưa có thì thêm mới vào
        else {
            let newItem = {
                id: cellValues.row.id,
                name: cellValues.row.name,
                quantity: 1,
                price: cellValues.row.price
            }
            let newServicesUsed = [...state.servicesUsed, newItem]
            for (const item of newServicesUsed) {
                newServiceTotal += item.quantity * item.price
            }
            setState({
                ...state,
                servicesUsed: newServicesUsed,
                serviceTotal: newServiceTotal
            })
        }
    };
    const handleDeleteItem = (value) => {
        let itemId = value.id
        let newServiceTotal = 0;
        if (value.quantity > 1) {
            console.log("1")
            let item = servicesUsed.find(x => x.id === itemId)
            let updatedItem = {
                ...item,
                quantity: item.quantity - 1
            }
            let newServicesUsed = servicesUsed.filter(function (item) {
                return item.id != itemId;
            });
            newServicesUsed.push(updatedItem)
            newServicesUsed.sort(function (a, b) {
                return a.id - b.id || a.name.localeCompare(b.name);
            });
            for (const item of newServicesUsed) {
                newServiceTotal += item.quantity * item.price
            }
            setState({
                ...state,
                servicesUsed: newServicesUsed,
                serviceTotal: newServiceTotal
            })
        } else {
            let newServicesUsed = servicesUsed.filter(service => service.id !== itemId)
            for (const item of newServicesUsed) {
                newServiceTotal += item.quantity * item.price
            }
            setState({
                ...state,
                servicesUsed: newServicesUsed,
                serviceTotal: newServiceTotal
            })
        }
    }
    const handleCellClick = (param, event) => {
        event.stopPropagation();
    };
    const handleRowClick = (param, event) => {
        event.stopPropagation();
    };
    const handleClose = () => {
        props.handleClose()
    }
    const handleSubmit = () => {
        let data = {
            roomId: room.id,
            services: servicesUsed.map(s => ({ serviceId: s.id, quantity: s.quantity, paid: true }))
        }
        console.log("state", state)
        props.handleSubmit(data)
    }
    return (
        <React.Fragment>
            <Dialog open={open} onClose={handleClose} fullWidth maxWidth='md'>
                <DialogTitle align='center'>Cập nhật dịch vụ</DialogTitle>
                <DialogContent>
                    <Box pl={4}>
                        <Grid container>
                            <Grid item xs={5}>
                                <div style={{ width: '100%', height: 260, }}>
                                    <DataGrid
                                        rowHeight={40}
                                        rows={services}
                                        columns={servicesColumn}
                                        pageSize={4}
                                        rowsPerPageOptions={[4]}
                                        onCellClick={handleCellClick}
                                        onRowClick={handleRowClick}
                                    />
                                </div>
                            </Grid>
                            <Grid item xs={1}></Grid>
                            <Grid item xs={6}>
                                <Paper variant="outlined">
                                    <Box style={{ width: '100%', minHeight: 260, }}>
                                        <Table aria-label="caption table">
                                            <TableHead>
                                                <TableRow>
                                                    <TableCell></TableCell>
                                                    <TableCell>Tên</TableCell>
                                                    <TableCell align="right">Số lượng</TableCell>
                                                    <TableCell align="right">Thành tiền</TableCell>
                                                </TableRow>
                                            </TableHead>
                                            <TableBody>
                                                {servicesUsed?.length ? servicesUsed.map((row) => (
                                                    <TableRow key={row.id}>
                                                        <TableCell align="right">
                                                            <IconButton size="small" onClick={() => handleDeleteItem(row)}>
                                                                <RemoveCircleOutlineIcon style={{ fill: "red" }} />
                                                            </IconButton>
                                                        </TableCell>
                                                        <TableCell component="th" scope="row">
                                                            {row.name}
                                                        </TableCell>

                                                        <TableCell align="right">{row.quantity}</TableCell>
                                                        <TableCell align="right">{numberWithCommas(row.quantity * row.price)}</TableCell>
                                                    </TableRow>
                                                ))
                                                    : <TableRow>
                                                        <TableCell align="center" colSpan={5}>Chưa sử dụng dịch vụ gì</TableCell>
                                                    </TableRow>
                                                }
                                                <TableRow>
                                                    <TableCell rowSpan={3} />
                                                    <TableCell colSpan={2}>Tổng dịch vụ</TableCell>
                                                    <TableCell align="right">{numberWithCommas(serviceTotal)}</TableCell>
                                                </TableRow>
                                            </TableBody>
                                        </Table>
                                    </Box >
                                </Paper >

                            </Grid >
                        </Grid >

                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button variant="outlined" color="primary" onClick={handleClose}>Đóng</Button>
                    <Button variant="contained" color="primary" onClick={handleSubmit}>Thêm</Button>
                </DialogActions>



            </Dialog>

        </React.Fragment >
    );
}

export default EditInfoRoomModal;