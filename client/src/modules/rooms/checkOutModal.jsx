import React, { useState } from 'react';
import { Dialog } from '@mui/material';
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

function CheckOutModal(props) {
    const { open, id } = props;
    const [selectedRows, setSelectedRows] = useState([]);
    const [state, setState] = useState({
        guests: [],
        isOpenModalAddGuest: false
    })
    const { isOpenModalAddGuest, guests } = state
    const columns = [
        {
            field: "Hành động",
            width: 130,
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
        { field: 'name', headerName: 'Họ Và Tên', width: 150 },
        {
            field: 'dob',
            headerName: 'Ngày sinh',
            headerAlign: 'center',
            type: 'date',
            width: 150,
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
    const openModalAddGuestInfo = () => {
        setState({
            ...state,
            isOpenModalAddGuest: true
        })
    }
    const handleClose1 = () => {
        setState({
            ...state,
            isOpenModalAddGuest: false
        })
    }
    const handleAddGuest = (data) => {
        setState({
            ...state,
            guests: [...guests, data],
            isOpenModalAddGuest: false
        })
    }
    console.log("guests ", guests)



    return (
        <Dialog open={open} onClose={handleClose} fullWidth maxWidth='lg' >
            <DialogTitle>Phòng {id}</DialogTitle>
            <DialogTitle>Check out modal</DialogTitle>
            <DialogContent>
                <Button variant="contained" color="success" onClick={openModalAddGuestInfo}>
                    Thêm thông tin khách
                </Button>
                <Grid container rowSpacing={4} spacing={0.5}>
                    <Grid item xs={6}>
                        <ModalAddGuestInfo open={isOpenModalAddGuest} handleClose={handleClose1} handleAddGuest={handleAddGuest} />

                        <DataGrid
                            autoHeight
                            rows={guests}
                            columns={columns}
                            pageSize={5}
                            rowsPerPageOptions={[5]}
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

                    </Grid>
                    <Grid item xs={6}>
                        <div style={{ height: 600, width: '100%' }}>
                        </div>
                    </Grid>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" color="error" onClick={handleClose}>Cancel</Button>
                <Button variant="contained" color="success" onClick={handleSubmit}>Subscribe</Button>
            </DialogActions>
        </Dialog >
    )
    // console.log("open 2", open)
    // return (


    // );
}
React.memo(CheckOutModal, (props, nextProps) => {
    if (props === nextProps) {
        // don't re-render/update
        return true
    }
})

export default CheckOutModal;