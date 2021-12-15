import React, { useEffect, useState } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import ModalAddNewUser from './modalAddNewUser'
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import { useGetAllUsers } from '../../services/users/user.service';
import Loading from '../../common-components/Loading';
import { useSelector } from "react-redux";
import { uniqueId } from 'lodash';

function Register(props) {
    const [state, setState] = useState(() => initState())
    const userList = useSelector((state) => state.userReducer.userList);
    const { usersList, isOpenModal } = state
    const { getAllUsers, isLoading } = useGetAllUsers()
    function initState() {
        return {
            users: [],
            usersList: [],
            isOpenModal: false
        }
    }
    useEffect(() => {
        getAllUsers()
    }, [])


    useEffect(() => {
        if (userList) {
            setState({
                ...state,
                usersList: userList //.map(x => ({ ...x, id: uniqueId() }))
            })
        }

    }, [JSON.stringify(userList)])
    console.log("state", state)
    const columns = [
        {
            field: " ",
            width: 60,
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
        { field: 'name', headerName: 'Họ và tên', width: 150 },
        { field: 'email', headerName: 'Email', headerAlign: 'center', width: 150, },
        { field: 'address', headerName: 'Địa chỉ', headerAlign: 'center', width: 150, },
        { field: 'phone', headerName: 'Số điện thoại', headerAlign: 'center', width: 150, },

    ];
    const handleClick = (event, cellValues) => {
        alert(cellValues.row.name);
    };
    const handleAddUser = (data) => {
        setState({
            ...state,
            isOpenModal: !isOpenModal
        })
    }
    const openModalAddNewUser = () => {
        setState({
            ...state,
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

            {isLoading ?
                <Loading />
                :
                <React.Fragment>
                    <Button sx={{ mb: 3 }} variant="contained" color="success" onClick={openModalAddNewUser}>
                        Thêm mới người dùng
                    </Button>
                    <span></span>
                    <ModalAddNewUser open={isOpenModal} handleClose={openModalAddNewUser} handleAddUser={handleAddUser} />

                    <DataGrid
                        autoHeight
                        rows={usersList}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        onCellClick={handleCellClick}
                        onRowClick={handleRowClick}

                    />
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default withSnackbar(Register);