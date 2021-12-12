import React, { useEffect, useState } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import ModalAddNewUser from './modalAddNewUser'
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import { useGetAllUsers, useGetAllPrice } from '../../services/users/user.service';
import Loading from '../../common-components/Loading';
import { useAppState } from '../../AppState';
import { uniqueId } from 'lodash';

function Register(props) {
    const [state, setState] = useState(() => initState())
    const [appState] = useAppState()
    const { userList, isOpenModal } = state
    const { getAllUsers, isLoading } = useGetAllUsers()
    function initState() {
        return {
            users: [],
            userList: [],
            isOpenModal: false
        }
    }
    useEffect(() => {
        getAllUsers()
    }, [])


    useEffect(() => {
        if (appState.userList) {
            console.log("appState.userList", appState.userList)
            setState({
                ...state,
                userList: appState.userList //.map(x => ({ ...x, id: uniqueId() }))
            })
        }

    }, [JSON.stringify(appState.userList)])

    console.log("isLoading", isLoading)
    console.log("userList", userList)
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
        { field: 'name', headerName: 'Tên Giá', width: 150 },
        {
            field: 'price',
            headerName: 'Giá',
            headerAlign: 'center',
            type: 'text',
            width: 150,
        },
    ];
    const handleClick = (event, cellValues) => {
        alert(cellValues.row.name);
    };
    const handleAddPrice = (data) => {
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
    console.log("users", userList)
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
                    <ModalAddNewUser open={isOpenModal} handleClose={openModalAddNewUser} handleAddPrice={handleAddPrice} />

                    <DataGrid
                        autoHeight
                        rows={userList}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        onCellClick={handleCellClick}
                        onRowClick={handleRowClick}

                    />
                    <Box>
                        {JSON.stringify(userList)}
                    </Box>
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default withSnackbar(Register);