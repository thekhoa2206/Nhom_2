import React, { useEffect, useState } from 'react';
import { Dialog } from '@mui/material';
import DialogTitle from '@mui/material/DialogTitle';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import * as dayjs from 'dayjs'
import { InputAdornment } from '@mui/material';
import { IconButton } from '@mui/material';
import SearchIcon from "@material-ui/icons/Search";
import { useSelector } from "react-redux";
import GuestService from '../../../services/guest/guest.service';
import { toast } from "../../../utils/snackbarUtils"
import { Autocomplete } from '@mui/material';

function ModalAddNewReservation(props) {
    const { open, handleClose } = props
    const guestList = useSelector((state) => state.guestReducer.guestList);
    const [state, setState] = useState({
        guests: [],
        isOpenModalAddGuest: false,
        servicesUsed: [],
        serviceTotal: 0,
    })
    const { guests } = state
    const [filtersGuest, setFiltersGuest] = useState({
        keyword: "0",
    });

    const handleSubmit = () =>{

    }
    const handleAddUser = () =>{

    }

    const handleChangeNote = () =>{

    }
    const handleChangeName = () =>{

    }
    const handleChangePhone = () => {

    }
    const handleChangeNumberRoom = () =>{

    }
    const defaultProps = {
        options: guestList,
        getOptionLabel: (option) => option.lastName + " " + option.firstName
    }
    const onChangeSearch = (event, value) => {
        setState({
            ...state,
            guests: value
        })
    }
    return (
        <Dialog open={open} fullWidth={true}  onClose={handleClose} maxWidth='md'>
            <DialogTitle align="center">THÊM MỚI ĐƠN ĐẶT PHÒNG </DialogTitle>
            <DialogContent>
                <Grid item xs={12} pl={3} >
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
                                //     endAdornment: (
                                //         <InputAdornment position="end">
                                //             <IconButton disabled>
                                //                 <SearchIcon style={{ fill: "#2196f3" }} />
                                //             </IconButton>
                                //         </InputAdornment>
                                //     )
                                // }}
                                />
                            }
                        />
                </Grid>
                <Box onSubmit={handleSubmit} xs={8}>
                    <Grid
                        container
                        alignItems="center"
                        direction="row"
                        style={{display: "flex"}}
                    >
                        <Grid item sm={6}>
                            <Grid xs={12} pl={3}>
                                <TextField
                                onChange={handleChangeName}
                                    margin="normal"
                                    fullWidth
                                    id="name"
                                    label="Họ và Tên"
                                    name="name"
                                    autoComplete="name"
                                    autoFocus
                                />
                            </Grid>
                            <Grid xs={12} pl={3}>
                                <TextField
                                onChange={handleChangePhone}
                                    margin="normal"
                                    fullWidth
                                    id="phoneNumber"
                                    label="Số điện thoại"
                                    name="phoneNumber"
                                    autoComplete="phoneNumber"
                                />
                            </Grid>
                            <Grid xs={12} pl={3}>
                                <TextField
                                    onChange={handleChangeNumberRoom}
                                    margin="normal"
                                    fullWidth
                                    name="numberRoom"
                                    label="Số phòng dự định"
                                    type="number"
                                    id="dob"
                                />
                            </Grid>
                        </Grid>
                        <Grid item sm={6}>
                            <Grid xs={12} pl={3}>
                                <TextField
                                    id="datetime-local"
                                    label="Ngày dự định đến"
                                    type="datetime-local"
                                    defaultValue={dayjs().format('YYYY-MM-DDTHH:mm')}
                                    fullWidth
                                    shrink="true"
                                />   
                            </Grid>
                            <Grid xs={12} pl={3} style={{marginTop: "20px"}}>
                                <TextField
                                    id="datetime-local"
                                    label="Ngày dự định đi"
                                    type="datetime-local"
                                    defaultValue={dayjs().format('YYYY-MM-DDTHH:mm')}
                                    fullWidth
                                    shrink="true"
                                />          
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item xs={12} pl={3}>
                            <TextField
                                onChange={handleChangeNote}
                                margin="normal"
                                fullWidth
                                name="note"
                                label="Ghi chú"
                                type="text"
                                id="dob"
                            />
                    </Grid>
                </Box>
            </DialogContent>
            <DialogActions>
                <Button
                    variant="outlined"
                    color="primary"
                    onClick={handleClose}>
                    Đóng
                </Button>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleAddUser}>
                    Thêm
                </Button>
            </DialogActions>

        </Dialog>
    );
}

export default ModalAddNewReservation;