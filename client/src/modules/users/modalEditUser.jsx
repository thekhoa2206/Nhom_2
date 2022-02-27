import React, { useEffect, useState } from 'react';
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
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import { useEditUser } from '../../services/users/user.service';

function ModalEditUser(props) {
    const { open, user } = props;
    console.log("props", props)
    const [state, setState] = useState({
        roleIds: []
    })

    useEffect(() => {
        if (user) {
            console.log("user", user)
            setState({
                id: user.id,
                address: user.address,
                email: user.email,
                idCard: user.idCard,
                name: user.name,
                phone: user.phone,
                roleIds: user?.roles?.map(x => {
                    if (x.name == "ROLE_STAFF")
                        return 2
                    if (x.name == "ROLE_ADMIN")
                        return 1
                }),
                salaryDay: 0,
                gender: user.sex,
                username: user.username
            })
        }
    }, [user?.id])

    const { name, address, email, idCard, phone, roleIds, salaryDay, gender, username } = state

    const { editUser } = useEditUser()
    const handleEditUser = (event) => {
        let data = {
            address: state.address,
            email: state.email,
            idCard: state.idCard,
            name: state.name,
            phone: state.phone,
            roles: state.roleIds,
            salaryDay: 0,
            sex: state.gender == "Nam" ? true : false,
            username: state.username
        }
        editUser(state.id, data)
        props.handleClose()
    }
    const handleNameChange = (e) => {
        setState({
            ...state,
            name: e.target.value
        })
    }
    const handleUsernameChange = (e) => {
        setState({
            ...state,
            username: e.target.value
        })
    }
    const handleDobChange = (e) => {
        setState({
            ...state,
            dob: e.target.value
        })
    }
    const handleRoleChange = (event) => {
        const {
            target: { value },
        } = event;
        setState(
            // On autofill we get a the stringified value.
            {
                ...state,
                roleIds: typeof value === 'string' ? value.split(',') : value,
            }
        );
    };
    const handleGenderChange = (e) => {
        setState({
            ...state,
            gender: e.target.value
        })
    }
    const handleAddressChange = (e) => {
        setState({
            ...state,
            address: e.target.value
        })
    }
    const handleEmailChange = (e) => {
        setState({
            ...state,
            email: e.target.value
        })
    }
    const handlePhoneChange = (e) => {
        setState({
            ...state,
            phone: e.target.value
        })
    }
    const handleIdCardChange = (e) => {
        setState({
            ...state,
            idCard: e.target.value
        })
    }
    const handleClose = () => {
        props.handleClose()
    }
    const handleSubmit = () => {
        props.handleClose()
    }
    console.log("state", state)
    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle align="center">CHỈNH SỬA THÔNG TIN NGƯỜI DÙNG </DialogTitle>
            <DialogContent>
                <Box onSubmit={handleSubmit} pl={4}>
                    <Grid
                        container
                        justifyContent="space-evenly"
                        alignItems="center"
                        direction="row"
                    >
                        <Grid item xs={6} pl={3}>
                            <TextField
                                value={name}
                                onChange={handleNameChange}
                                margin="normal"
                                required
                                id="name"
                                label="Họ và Tên"
                                name="name"
                                autoComplete="name"
                                autoFocus
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                value={username}
                                onChange={handleUsernameChange}
                                margin="normal"
                                required
                                id="username"
                                label="Tên đăng nhập"
                                name="username"
                                autoComplete="username"
                            />
                        </Grid>
                        <Grid item xs={6} pl={4}>
                            <FormControl required component="fieldset">
                                <FormLabel component="legend">Giới tính</FormLabel>
                                <RadioGroup onChange={handleGenderChange} value={gender == "Nam" ? true : false} row aria-label="gender" name="row-radio-buttons-group">
                                    <FormControlLabel value={true} control={<Radio />} label="Nam" />
                                    <FormControlLabel value={false} control={<Radio />} label="Nữ" />
                                </RadioGroup>
                            </FormControl>
                        </Grid>
                        <Grid item xs={6} >
                            <Box mt={1}>
                                <FormControl style={{ width: 192 }}>
                                    <InputLabel id="role">Phân quyền</InputLabel>
                                    <Select
                                        multiple
                                        labelId="role"
                                        id="role"
                                        label="Phân quyền"
                                        value={roleIds}
                                        onChange={handleRoleChange}
                                    >
                                        <MenuItem key={1} value={1}>Quản trị viên</MenuItem>
                                        <MenuItem key={2} value={2}>Nhân viên lễ tân</MenuItem>
                                    </Select>
                                </FormControl>
                            </Box>
                        </Grid>

                        <Grid item xs={6} pl={3}>
                            <TextField
                                value={address}
                                onChange={handleAddressChange}
                                margin="normal"
                                required
                                id="address"
                                label="Địa chỉ"
                                name="address"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                value={email}
                                onChange={handleEmailChange}
                                margin="normal"
                                required
                                name="email"
                                label="Email"
                                type="text"
                                id="dob"
                            />
                        </Grid>
                        <Grid item xs={6} pl={3}>
                            <TextField
                                value={phone}
                                onChange={handlePhoneChange}
                                margin="normal"
                                required
                                id="phone"
                                label="Số điện thoại"
                                name="phone"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                value={idCard}
                                onChange={handleIdCardChange}
                                margin="normal"
                                required
                                label="Số CMND/ Hộ chiếu"
                                name="idCard"
                                type="text"
                                id="idCard"
                            />
                        </Grid>
                        <Grid item xs={6}>

                        </Grid>
                        <Grid item xs={6} >

                        </Grid>


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
                    onClick={handleEditUser}>
                    Thêm
                </Button>
            </DialogActions>

        </Dialog>
    );
}

export default ModalEditUser;