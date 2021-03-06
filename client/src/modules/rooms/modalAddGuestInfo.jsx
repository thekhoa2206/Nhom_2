import React, { useState } from 'react';
import { Dialog } from '@mui/material';
import DialogTitle from '@mui/material/DialogTitle';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import { countryList } from '../../utils/constants';
import * as dayjs from 'dayjs'
import { v4 as uuidv4 } from 'uuid';
import GuestService from '../../services/guest/guest.service';
function ModalAddGuestInfo(props) {
    const { open } = props;
    const [state, setState] = useState({ nationality: "Viet Nam" })
    const { firstName, lastName, dob, address, email, id, idNumber, nationality, phoneNumber } = state
    const handleAddGuest = (event) => {
        let data = {
            address: address,
            birthday: dayjs(dob).valueOf(),
            email: email,
            firstName: firstName,
            idCard: idNumber,
            lastName: lastName,
            nationality: nationality,
            phoneNumber: phoneNumber
        }
        props.handleAddGuest(data)
    }
    const handleFirstNameChange = (e) => {
        setState({
            ...state,
            firstName: e.target.value
        })
    }
    const handleLastNameChange = (e) => {
        setState({
            ...state,
            lastName: e.target.value
        })
    }
    const handleDobChange = (e) => {
        console.log("dob", e.target.value)
        setState({
            ...state,
            dob: e.target.value
        })
    }
    const handleChangeNationality = (e) => {
        setState({
            ...state,
            nationality: e.target.value
        })
    }
    const handlePhoneNumberChange = (e) => {
        setState({
            ...state,
            phoneNumber: e.target.value
        })
    }
    const handleEmailChange = (e) => {
        setState({
            ...state,
            email: e.target.value
        })
    }
    const handleIDNumberChange = (e) => {
        setState({
            ...state,
            idNumber: e.target.value
        })
    }
    const handleAddressChange = (e) => {
        setState({
            ...state,
            address: e.target.value
        })
    }

    const handleClose = () => {
        props.handleClose()
    }
    return (
        <Dialog open={open} onClose={handleClose} sx={{}}>
            <DialogTitle>Th??m th??ng tin kh??ch</DialogTitle>
            <DialogContent>
                <Box pl={4}>
                    <Grid
                        container
                    >
                        {/* T??n */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleFirstNameChange}
                                margin="normal"
                                required
                                id="firstName"
                                label="T??n"
                                name="firstName"
                                autoComplete="firstName"
                                autoFocus
                            />
                        </Grid>
                        {/* H??? */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleLastNameChange}
                                margin="normal"
                                required
                                id="lastName"
                                label="H???"
                                name="lastName"
                                autoComplete="lastName"
                            />
                        </Grid>
                        {/* Ng??y sinh */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleDobChange}
                                margin="normal"
                                required
                                name="dob"
                                type="date"
                                id="dob"
                                label="Ng??y sinh"
                                InputLabelProps={{ shrink: true }}
                            />
                        </Grid>
                        {/* S??? ??i???n tho???i */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handlePhoneNumberChange}
                                margin="normal"
                                required
                                name="phone-number"
                                type="number"
                                id="phone-number"
                                label="S??? ??i???n tho???i"
                            />
                        </Grid>
                        {/* Qu???c t???ch */}
                        <Grid item xs={6}>
                            <Box mt={2}>
                                <FormControl style={{ width: 192 }}>
                                    <InputLabel id="nationality">Qu???c t???ch</InputLabel>
                                    <Select
                                        labelId="nationality"
                                        id="nationality"
                                        label="Qu???c t???ch"
                                        value={state.nationality}
                                        onChange={handleChangeNationality}
                                    >
                                        {
                                            countryList.map((country, index) => {
                                                return (
                                                    <MenuItem key={index} value={country}>{country}</MenuItem>
                                                )
                                            })
                                        }
                                    </Select>
                                </FormControl>
                            </Box>
                        </Grid>
                        {/* S??? cmnd */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleIDNumberChange}
                                margin="normal"
                                required
                                name="id-number"
                                type="number"
                                id="id-number"
                                label="S??? CMND/ H??? chi???u"
                            />
                        </Grid>
                        {/* ?????a ch??? */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleAddressChange}
                                margin="normal"
                                required
                                name="address"
                                type="text"
                                id="address"
                                label="?????a ch???"
                            />
                        </Grid>
                        {/* Email */}
                        <Grid item xs={6}>
                            <TextField
                                onChange={handleEmailChange}
                                margin="normal"
                                name="email"
                                type="text"
                                id="email"
                                label="Email"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Box></Box>
                        </Grid>



                    </Grid>

                </Box>
            </DialogContent>
            <DialogActions>
                <Button variant="outlined" color="primary" onClick={handleClose}>????ng</Button>
                <Button variant="contained" color="primary" onClick={handleAddGuest}>Th??m</Button>
            </DialogActions>



        </Dialog>
    );
}

export default ModalAddGuestInfo;