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
import * as dayjs from 'dayjs'
import Autocomplete from '@mui/material/Autocomplete';
import { useSelector } from "react-redux";
import { useCheckIn, useGetAllRooms } from '../../services/rooms/room.service';
import { useCreateGuest } from '../../services/guests/guest.service';

function CheckInModal(props) {
    const { open, room } = props;
    const guestList = useSelector((state) => state.guestReducer.guestList);
    const services = useSelector((state) => state.authReducer.services);
    const { checkIn } = useCheckIn()
    const { createGuest } = useCreateGuest()
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

    const { isOpenModalAddGuest, guests, servicesUsed, serviceTotal, deposit, checkInTime, checkOutTime, additionalFee, reducedFee } = state
    const columns = [
        {
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
                            handleClick(event, cellValues);
                        }}
                    >
                        <EditIcon style={{ fill: "orange" }} />
                    </IconButton>
                );
            }
        },
        {
            field: 'name',
            headerName: 'Họ Và Tên',
            headerAlign: 'center',
            align: "center",
            width: 200,
            valueGetter: (params) =>
                `${params.row.firstName + " " + params.row.lastName}`,
        },
        {
            field: 'birthday',
            headerName: 'Ngày sinh',
            headerAlign: 'center',
            align: "center",
            width: 200,
            valueGetter: (params) =>
                `${dayjs(params.row.birthday).format('DD/MM/YYYY')}`,
        },
    ];
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
    const handleClose = () => {
        props.handleClose()
    }
    const handleSubmit = () => {
        props.handleSubmit()
        let guestIds = guests?.map(s => s.id) || []
        let servicesData = servicesUsed?.map(s => ({ serviceId: s.id, quantity: s.quantity, paid: true })) || []
        let data = {
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
    const handleSelectionChange = (selection) => {
        setSelectedRows(selection);
    };
    const handleDelete = () => {
        let guestNotSelected = guests.filter(guest => !selectedRows.includes(guest.id))
        setState({
            ...state,
            guests: guestNotSelected
        })
    };
    const handleOpenModalAddGuestInfo = () => {
        setState({
            ...state,
            isOpenModalAddGuest: !isOpenModalAddGuest
        })
    }
    const handleAddGuest = (data) => {
        createGuest(data)
        setState({
            ...state,
            isOpenModalAddGuest: false
        })
    }
    const handleChangeCheckOutTime = (event) => {
        setState({
            ...state,
            checkOutTime: event.target.value
        })
    }
    const handleChangeCheckInTime = (event) => {
        setState({
            ...state,
            checkOutTime: event.target.value
        })
    }
    const onChangeSelect = (event) => {
        console.log(event.target.value);
    };
    const handleChangeDeposit = (event) => {
        setState({
            ...state,
            deposit: parseInt(event.target.value)
        })
    }
    const handleChangeAdditionalFee = (event) => {
        setState({
            ...state,
            additionalFee: parseInt(event.target.value)
        })
    }
    const handleChangeReducedFee = (event) => {
        setState({
            ...state,
            reducedFee: parseInt(event.target.value)
        })
    }
    const onChangeSearch = (event, value) => {
        setState({
            ...state,
            guests: value
        })
    }
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    // const servicesUsed = [
    //     {
    //         id: 1,
    //         name: "Cocacola",
    //         quantity: 2,
    //         price: 10000
    //     },
    //     {
    //         id: 2,
    //         name: "Nước lọc",
    //         quantity: 3,
    //         price: 10000
    //     },
    // ]
    const defaultProps = {
        options: guestList,
        getOptionLabel: (option) => option.lastName + " " + option.firstName
    }
    return (
        <Dialog open={open} onClose={handleClose} fullWidth maxWidth='lg' >
            <DialogTitle align="center">Phòng {room.roomName}</DialogTitle>
            <DialogContent>
                <Grid container rowSpacing={4} spacing={2}>
                    <Grid item xs={6}>
                        <Grid container spacing={2}>
                            <Grid item xs={4} alignSelf="center">
                                <Button variant="contained" color="primary" onClick={() => handleOpenModalAddGuestInfo()}>
                                    Thêm mới khách
                                </Button>
                            </Grid>
                            <Grid item xs={8}>
                                <Autocomplete
                                    {...defaultProps}
                                    disableClearable
                                    multiple
                                    value={guests}
                                    filterSelectedOptions
                                    renderTags={() => null}
                                    onChange={onChangeSearch}
                                    renderInput={(params) =>
                                        <TextField
                                            {...params}
                                            // fullWidth
                                            label="Tìm khách hàng..."
                                        // InputProps={{
                                        //     startAdornment: (
                                        //         <InputAdornment position="start">
                                        //             <IconButton disabled>
                                        //                 <SearchIcon style={{ fill: "#1769aa" }} />
                                        //             </IconButton>
                                        //         </InputAdornment>
                                        //     )
                                        // }}
                                        />
                                    }
                                />
                            </Grid>
                        </Grid>
                        <ModalAddGuestInfo open={isOpenModalAddGuest} handleClose={handleOpenModalAddGuestInfo} handleAddGuest={handleAddGuest} />
                        <div style={{ width: '100%', paddingTop: 10 }}>
                            <DataGrid
                                autoHeight
                                rows={guests}
                                columns={columns}
                                pageSize={2}
                                rowsPerPageOptions={[2]}
                                checkboxSelection={true}
                                onCellClick={handleCellClick}
                                onRowClick={handleRowClick}
                                onSelectionModelChange={handleSelectionChange}

                            />
                            {selectedRows.length > 0 &&
                                <IconButton size="small" onClick={() => handleDelete()}>
                                    <DeleteIcon style={{ fill: "red" }} />
                                </IconButton>
                            }
                        </div>

                    </Grid>

                    <Grid item xs={6}>
                        <Paper variant="outlined">
                            <Box p={4}>
                                <Grid container columnSpacing={3}>
                                    <Grid item xs={6} >
                                        <TextField
                                            id="datetime-local"
                                            label="Giờ vào"
                                            type="datetime-local"
                                            defaultValue={checkInTime}
                                            fullWidth
                                            shrink="true"
                                            disabled
                                            onChange={handleChangeCheckInTime}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            id="datetime-local"
                                            label="Giờ ra dự kiến"
                                            type="datetime-local"
                                            defaultValue={checkOutTime}
                                            fullWidth
                                            shrink="true"
                                            onChange={handleChangeCheckOutTime}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Box mt={2}>
                                            <FormControl fullWidth>
                                                <InputLabel id="type-price">Hình thức nghỉ</InputLabel>
                                                <Select
                                                    labelId="type-price"
                                                    id="type-price"
                                                    label="Hình thức nghỉ"
                                                    value={"Ngày thường"}
                                                    onChange={onChangeSelect}
                                                >
                                                    <MenuItem key={1} value={"Ngày thường"}>Ngày thường</MenuItem>
                                                    <MenuItem key={2} value={"Ngày lễ"}>Ngày lễ</MenuItem>
                                                    {/* <MenuItem key={3} value={"Tuần"}>Tuần</MenuItem>
                                                    <MenuItem key={4} value={"Tháng"}>Tháng</MenuItem> */}
                                                </Select>
                                            </FormControl>
                                        </Box>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            InputProps={{
                                                endAdornment: <InputAdornment position="end">đ</InputAdornment>,
                                            }}
                                            margin="normal"
                                            name="deposit"
                                            type="number"
                                            id="deposit"
                                            label="Đặt trước"
                                            onChange={handleChangeDeposit}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            InputProps={{
                                                endAdornment: <InputAdornment position="end">đ</InputAdornment>,
                                            }}
                                            margin="normal"
                                            name="reduced-fee"
                                            type="number"
                                            id="reduced-fee"
                                            label="Giảm trừ"
                                            onChange={handleChangeReducedFee}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            fullWidth
                                            InputProps={{
                                                endAdornment: <InputAdornment position="end">đ</InputAdornment>,
                                            }}
                                            margin="normal"
                                            name="additional-fee"
                                            type="number"
                                            id="additional-fee"
                                            label="Phụ thu"
                                            onChange={handleChangeAdditionalFee}
                                        />
                                    </Grid>
                                </Grid>
                            </Box>
                        </Paper>
                    </Grid>
                    <Grid item xs={4}>
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

                    <Grid item xs={4}>
                        <Paper variant="outlined">
                            <Box>
                                <Table aria-label="caption table">
                                    {/* Dịch vụ sử dụng */}
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
                            </Box>
                        </Paper>

                    </Grid>
                    <Grid item xs={4}>
                        <Grid container >
                            {/* <Grid item xs={12} pb={2}>
                                <TextField
                                    id="outlined-multiline-static"
                                    label="Ghi chú"
                                    multiline
                                    rows={2}
                                    defaultValue="Ghi chú"
                                    fullWidth
                                />
                            </Grid> */}
                            <Grid item xs={12}>
                                <Paper variant="outlined">
                                    <Box pl={4} pb={2} pt={2}>
                                        <Grid container>
                                            <Grid item xs={2}></Grid>
                                            <Grid item xs={10}>
                                                <Grid container>
                                                    <Grid item xs={6}>
                                                        <Typography>Tiền phòng: </Typography>
                                                        <Typography>Tiền dịch vụ: </Typography>
                                                        <Typography>Đặt trước: </Typography>
                                                        <Typography>Giảm trừ: </Typography>
                                                        <Typography>Phụ thu: </Typography>
                                                        <Typography fontWeight="bold">Tổng thanh toán: </Typography>
                                                    </Grid>
                                                    <Grid item xs={5}>
                                                        <Typography textAlign="right">0.000đ</Typography>
                                                        <Typography textAlign="right">{numberWithCommas(serviceTotal)}đ</Typography>
                                                        <Typography textAlign="right">-{numberWithCommas(deposit)}đ</Typography>
                                                        <Typography textAlign="right">-{numberWithCommas(reducedFee)}đ</Typography>
                                                        <Typography textAlign="right">{numberWithCommas(additionalFee)}đ</Typography>
                                                        <Typography textAlign="right" fontWeight="bold">{numberWithCommas(serviceTotal - deposit + additionalFee - reducedFee)}đ</Typography>
                                                    </Grid>
                                                </Grid>

                                            </Grid>
                                        </Grid>

                                    </Box>
                                </Paper>
                            </Grid>

                        </Grid>
                    </Grid>
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
React.memo(CheckInModal, (props, nextProps) => {
    if (JSON.stringify(props) === JSON.stringify(nextProps)) {
        // don't re-render/update
        return true
    }
})

export default CheckInModal;