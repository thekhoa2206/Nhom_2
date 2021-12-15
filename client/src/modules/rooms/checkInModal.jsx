import React, { useState } from 'react';
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

function CheckInModal(props) {
    const { open, id } = props;
    const [selectedRows, setSelectedRows] = useState([]);
    const [state, setState] = useState({
        guests: [],
        isOpenModalAddGuest: false,
        servicesUsed: []
    })
    const { isOpenModalAddGuest, guests, servicesUsed } = state
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
        { field: 'name', headerName: 'Họ Và Tên', width: 200 },
        {
            field: 'dob',
            headerName: 'Ngày sinh',
            headerAlign: 'center',
            type: 'date',
            width: 200,
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
                        <ControlPointIcon style={{ fill: "#2196f3" }} />
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
            setState({
                ...state,
                servicesUsed: newServicesUsed
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
            setState({
                ...state,
                servicesUsed: [...state.servicesUsed, newItem]
            })
        }
    };
    console.log("state", state)
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
        setState({
            ...state,
            guests: [...guests, data],
            isOpenModalAddGuest: false
        })
    }
    const onChangeSelect = (event) => {
        console.log(event.target.value);
    };

    console.log("guests ", guests)
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


    return (
        <Dialog open={open} onClose={handleClose} fullWidth maxWidth='lg' >
            <DialogTitle align="center">Phòng {id}</DialogTitle>
            <DialogContent>
                <Grid container rowSpacing={4} spacing={2}>
                    <Grid item xs={6}>
                        <Grid container spacing={2}>
                            <Grid item xs={4} alignSelf="center">
                                <Button variant="contained" color="primary" onClick={handleOpenModalAddGuestInfo}>
                                    Thêm mới khách
                                </Button>
                            </Grid>
                            <Grid item xs={8}>
                                <TextField
                                    fullWidth
                                    label="Tìm khách hàng..."
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment>
                                                <IconButton>
                                                    <SearchIcon style={{ fill: "#2196f3" }} />
                                                </IconButton>
                                            </InputAdornment>
                                        )
                                    }}
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
                                <IconButton size="small" onClick={handleDelete}>
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
                                            defaultValue={dayjs().format('YYYY-MM-DDTHH:mm')}
                                            fullWidth
                                            shrink="true"
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            id="datetime-local"
                                            label="Giờ ra dự kiến"
                                            type="datetime-local"
                                            defaultValue="2017-05-24T10:30"
                                            fullWidth
                                            shrink="true"
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
                                                    value={"Ngày đêm"}
                                                    onChange={onChangeSelect}
                                                >
                                                    <MenuItem key={1} value={"Đêm"}>Đêm</MenuItem>
                                                    <MenuItem key={2} value={"Ngày đêm"}>Ngày đêm</MenuItem>
                                                    <MenuItem key={3} value={"Tuần"}>Tuần</MenuItem>
                                                    <MenuItem key={4} value={"Tháng"}>Tháng</MenuItem>
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
                                    {/* <caption>Dịch vụ sử dụng</caption> */}
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
                                                    <IconButton size="small" onClick={handleDelete}>
                                                        <RemoveCircleOutlineIcon style={{ fill: "red" }} />
                                                    </IconButton>
                                                </TableCell>
                                                <TableCell component="th" scope="row">
                                                    {row.name}
                                                </TableCell>

                                                <TableCell align="right">{row.quantity}</TableCell>
                                                <TableCell align="right">{row.quantity * row.price}</TableCell>
                                            </TableRow>
                                        ))
                                            : <TableRow>
                                                <TableCell align="center" colSpan={5}>Chưa sử dụng dịch vụ gì</TableCell>
                                            </TableRow>
                                        }
                                        <TableRow>
                                            <TableCell rowSpan={3} />
                                            <TableCell colSpan={2}>Tổng dịch vụ</TableCell>
                                            <TableCell align="right">100.000</TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </Box>
                        </Paper>

                    </Grid>
                    <Grid item xs={4}>
                        <Paper variant="outlined">
                            <Box p={4}>
                                <Grid container>
                                    <Grid item xs={3}></Grid>
                                    <Grid item xs={9}>
                                        <Grid container>
                                            <Grid item xs={6}>
                                                <Typography>Tiền phòng: </Typography>
                                                <Typography>Tiền dịch vụ: </Typography>
                                                <Typography>Tổng tạm tính: </Typography>
                                            </Grid>
                                            <Grid item xs={5}>
                                                <Typography textAlign="right">0.000đ</Typography>
                                                <Typography textAlign="right">20.000đ</Typography>
                                                <Typography textAlign="right">20.000đ</Typography>
                                            </Grid>
                                        </Grid>

                                    </Grid>
                                </Grid>

                            </Box>
                        </Paper>
                    </Grid>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button variant="outlined" color="primary" onClick={handleClose}>Cancel</Button>
                <Button variant="contained" color="primary" onClick={handleSubmit}>Subscribe</Button>
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