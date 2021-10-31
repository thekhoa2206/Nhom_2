import React, { useEffect, useState } from 'react';
import { withSnackbar } from 'notistack';
import Button from '@mui/material/Button';
import { DataGrid } from '@mui/x-data-grid';
import ModalAddNewUser from './modalAddNewUser'
import EditIcon from '@mui/icons-material/Edit';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import { useGetAllUsers } from '../../utils/apiCalls';
import Loading from '../../common-components/Loading';

function Register(props) {
    const [state, setState] = useState(() => initState())
    const { isOpenModal, users } = state

    const { data, isLoading } = useGetAllUsers()
    function initState() {

        return {
            users: [],
            isOpenModal: false
        }
    }
    useEffect(() => {
        if (data) {
            let data1 = data.map(obj => ({ id: "1" + obj.name, name: obj.name }))
            setState({
                ...state,
                users: data1
            })
        }

    }, [JSON.stringify(data)])

    console.log("isLoading", isLoading)
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
        // {
        //     field: 'dob',
        //     headerName: 'Ngày sinh',
        //     headerAlign: 'center',
        //     type: 'date',
        //     width: 150,
        // },
    ];
    const handleClick = (event, cellValues) => {
        alert(cellValues.row.name);
    };
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
    console.log("users", users)
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
                    <ModalAddNewUser open={isOpenModal} handleClose={openModalAddNewUser} />

                    <DataGrid
                        autoHeight
                        rows={users}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        onCellClick={handleCellClick}
                        onRowClick={handleRowClick}

                    />
                    <Box>
                        {JSON.stringify(data)}
                    </Box>
                </React.Fragment>
            }
        </React.Fragment>
    );
}

export default withSnackbar(Register);