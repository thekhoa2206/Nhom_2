import React, { useState } from 'react';
import { Dialog } from '@mui/material';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';


function ModalAddNewUser(props) {
    const { open } = props;
    const [state, setState] = useState({})
    const { name, dob } = state
    const handleAddUser = (event) => {
        let data = {
            id: name + "-id",
            name: name,
            dob: dob
        }
        props.handleAddGuest(data)
    }
    const handleNameChange = (e) => {
        setState({
            ...state,
            name: e.target.value
        })
    }
    const handleDobChange = (e) => {
        setState({
            ...state,
            dob: e.target.value
        })
    }
    const handleClose = () => {
        props.handleClose()
    }
    return (
        <Dialog open={open} onClose={handleClose}>
            <DialogTitle>Thêm mới người dùng </DialogTitle>
            <Box p={3}>
                <Grid
                    container
                    justifyContent="space-evenly"
                    alignItems="center"
                    direction="row"
                >
                    <Grid item xs={6}>
                        <TextField
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
                            onChange={handleDobChange}
                            margin="normal"
                            required
                            name="dob"
                            type="date"
                            id="dob"
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
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
                            onChange={handleDobChange}
                            margin="normal"
                            required
                            name="dob"
                            type="date"
                            id="dob"
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
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
                            onChange={handleDobChange}
                            margin="normal"
                            required
                            name="dob"
                            type="date"
                            id="dob"
                        />
                    </Grid>
                    <Grid item xs={6}>

                    </Grid>
                    <Grid item xs={6} >
                        <Button variant="contained" color="success" onClick={handleAddUser}>Add</Button>
                    </Grid>


                </Grid>
            </Box>


        </Dialog>
    );
}

export default ModalAddNewUser;