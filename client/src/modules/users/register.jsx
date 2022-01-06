import React, { useEffect, useState } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import ModalAddNewUser from './modalAddNewUser'
import ModalEditUser from './modalEditUser'
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
    const { usersList, isOpenModalAdd, isOpenModalEdit, user } = state
    const { getAllUsers, isLoading } = useGetAllUsers()
    function initState() {
        return {
            user: {},
            usersList: [],
            isOpenModalAdd: false,
            isOpenModalEdit: false,
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
                            handleClickEdit(event, cellValues);
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
    const handleClickEdit = (event, cellValues) => {
        console.log("cellValues.row", cellValues.row)
        setState({
            ...state,
            user: cellValues.row,
            isOpenModalEdit: !isOpenModalEdit
        })
    };
    const openModalAddNewUser = () => {
        setState({
            ...state,
            isOpenModalAdd: !isOpenModalAdd
        })
    }
    const openModalEditUser = () => {
        setState({
            ...state,
            isOpenModalEdit: !isOpenModalEdit
        })
    }

    const handleCellClick = (param, event) => {
        event.stopPropagation();
    };

    const handleRowClick = (param, event) => {
        event.stopPropagation();
    };
    console.log("state", state)
    return (
        <React.Fragment>

            {isLoading ?
                <Loading />
                :
                <React.Fragment>
                    <Button sx={{ mb: 3 }} variant="contained" color="primary" onClick={openModalAddNewUser}>
                        Thêm mới
                    </Button>
                    <span></span>
                    <ModalAddNewUser open={isOpenModalAdd} handleClose={openModalAddNewUser} />
                    <ModalEditUser open={isOpenModalEdit} handleClose={openModalEditUser} user={user} />



                    <DataGrid
                        autoHeight
                        rows={usersList}
                        columns={columns}
                        pageSize={10}
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