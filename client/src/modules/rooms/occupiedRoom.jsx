import React, { useState, useEffect } from 'react';
import Card from '@mui/material/Card';
import { Grid, IconButton } from '@mui/material';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import CheckOutModal from './checkOutModal';
import HotelIcon from '@mui/icons-material/Hotel';
import Avatar from '@mui/material/Avatar';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import HailIcon from '@mui/icons-material/Hail';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import AttachMoneyIcon from '@mui/icons-material/AttachMoney';
import PaidIcon from '@mui/icons-material/Paid';
import PriceCheckIcon from '@mui/icons-material/PriceCheck';
import LogoutIcon from '@mui/icons-material/Logout';
import ChangeCircleOutlinedIcon from '@mui/icons-material/ChangeCircleOutlined';
import CleaningServicesOutlinedIcon from '@mui/icons-material/CleaningServicesOutlined';
import ConstructionOutlinedIcon from '@mui/icons-material/ConstructionOutlined';
import LocalCafeOutlinedIcon from '@mui/icons-material/LocalCafeOutlined';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import EditInfoRoomModal from './editInfoRoomModal';
import { useUpdateServices } from "../../services/rooms/room.service";
import dayjs from 'dayjs';

function OccupiedRoom(props) {
    const { room } = props
    const [open, setOpen] = useState(false);
    const [openModalUpdate, setOpenModalUpdate] = useState(false);
    const [anchorEl, setAnchorEl] = useState(null);
    const [timer, setTimer] = useState(convertMinsToHrsMins((dayjs()).diff(dayjs(room.checkInTime), "minute")));
    const [state, setState] = useState({
        serviceTotal: calculateSum(room.servicesUsed),
    })
    useEffect(() => {
        const timerId = setInterval(() => setTimer(convertMinsToHrsMins((dayjs()).diff(dayjs(room.checkInTime), "minute"))), 60000);

        return () => clearInterval(timerId);
    });
    function calculateSum(data) {
        let sum = 0;
        if (data?.length) {
            data.forEach((e) => { sum += e.quantity * e.price })
        }
        return sum
    }
    const { serviceTotal } = state
    const openAnchor = Boolean(anchorEl);
    const { updateServices } = useUpdateServices()
    const handleClickAnchor = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleCloseAnchor = () => {
        setAnchorEl(null);
    };
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClickOpenModalUpdate = () => {
        setOpenModalUpdate(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const handleCloseModalUpdate = () => {
        setOpenModalUpdate(false);
    };
    const handleSubmit = () => {
        setOpen(false);
    };
    const handleSubmitModalUpdate = (data) => {
        console.log("data111", data)
        updateServices(data)
        setOpenModalUpdate(false);
    };
    function convertMinsToHrsMins(minutes) {
        var h = Math.floor(minutes / 60);
        var m = minutes % 60;
        // h = h < 10 ? '0' + h : h;
        // m = m < 10 ? '0' + m : m;
        return h + ' gi??? ' + m + " ph??t";
    }
    function numberWithCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    console.log("abc")
    return (
        <React.Fragment>
            <CheckOutModal open={open} room={room} handleSubmit={handleSubmit} handleClose={handleClose} />
            <EditInfoRoomModal open={openModalUpdate} room={room} handleSubmit={handleSubmitModalUpdate} handleClose={handleCloseModalUpdate} />
            <Card sx={props.styleZoom}>
                <CardActionArea onClick={handleClickAnchor}>
                    <CardContent style={{ backgroundColor: "red" }} sx={props.styleZoom}>
                        <Grid container spacing={2} rowSpacing={4}>
                            <Grid pl={2} pt={3}>
                                <HotelIcon fontSize='large' style={{ fill: "white" }} />
                            </Grid>
                            <Grid pl={5} pt={3}>
                                <Typography align="center" fontSize="28px" fontWeight="bold" color="white" gutterBottom variant="h5" component="div">
                                    {room.roomName}
                                </Typography>
                            </Grid>
                            <Grid pl={4} pt={3}>
                                <Typography align="center" fontSize="12px" color="white" variant="h5" component="div">
                                    Ph??ng
                                </Typography>
                                <Typography align="center" fontSize="12px" color="white" variant="h5" component="div">
                                    {room.typeRoomName}
                                </Typography>
                            </Grid>
                            <Grid pl={2}>
                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    flexWrap: 'wrap',
                                }}>
                                    <HailIcon fontSize='small' style={{ fill: "white", paddingRight: "5px" }} />
                                    <span style={{ color: "white", fontSize: "13px", paddingRight: "5px" }} >
                                        Check-in:
                                    </span>
                                    <span style={{ color: "white", fontSize: "15px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {dayjs(room.checkInTime).hour() < 10 ? '0' + dayjs(room.checkInTime).hour() : dayjs(room.checkInTime).hour()}:{dayjs(room.checkInTime).minute() < 10 ? '0' + dayjs(room.checkInTime).minute() : dayjs(room.checkInTime).minute()}
                                    </span>
                                    <span style={{ color: "white", fontSize: "15px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {dayjs(room.checkInTime).format('DD/MM/YY')}
                                    </span>
                                </div>

                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    flexWrap: 'wrap',
                                }}>
                                    <AccessTimeIcon fontSize='small' style={{ fill: "white", paddingRight: "5px" }} />
                                    <span style={{ color: "white", fontSize: "13px", paddingRight: "5px" }} >
                                        Th???i l?????ng:
                                    </span>
                                    <span style={{ color: "white", fontSize: "14px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {timer}
                                    </span>
                                </div>
                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    flexWrap: 'wrap',
                                }}>
                                    <AttachMoneyIcon fontSize='small' style={{ fill: "white", paddingRight: "5px" }} />
                                    <span style={{ color: "white", fontSize: "13px", paddingRight: "5px" }} >
                                        Ti???n ph??ng:
                                    </span>
                                    <span style={{ color: "white", fontSize: "15px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {numberWithCommas(room?.sumOfPrices ? room.sumOfPrices : 0)}??
                                    </span>
                                </div>
                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    flexWrap: 'wrap',
                                }}>
                                    <PaidIcon fontSize='small' style={{ fill: "white", paddingRight: "5px" }} />
                                    <span style={{ color: "white", fontSize: "13px", paddingRight: "5px" }} >
                                        ?????t tr?????c:
                                    </span>
                                    <span style={{ color: "white", fontSize: "15px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {numberWithCommas(room.deposit)}??
                                    </span>
                                </div>
                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    flexWrap: 'wrap',
                                }}>
                                    <PriceCheckIcon fontSize='small' style={{ fill: "white", paddingRight: "5px" }} />
                                    <span style={{ color: "white", fontSize: "13px", paddingRight: "5px" }} >
                                        T???ng ti???n:
                                    </span>
                                    <span style={{ color: "white", fontSize: "15px", paddingRight: "5px", fontWeight: "bold" }} >
                                        {numberWithCommas(room.sumOfPrices + serviceTotal + room.additionalFee - room.deposit - room.reducedFee)}??
                                    </span>
                                </div>
                            </Grid>
                        </Grid>
                    </CardContent>

                </CardActionArea>

            </Card>
            <Menu
                anchorEl={anchorEl}
                open={openAnchor}
                onClose={handleCloseAnchor}
                onClick={handleCloseAnchor}
                PaperProps={{
                    elevation: 0,
                    sx: {
                        overflow: 'visible',
                        filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                        mt: 10.5,
                        '&:before': {
                            content: '""',
                            display: 'block',
                            position: 'absolute',
                            top: 0,
                            left: 10,
                            width: 10,
                            height: 10,
                            bgcolor: 'background.paper',
                            transform: 'translateY(-50%) rotate(45deg)',
                            zIndex: 0,
                        },
                    },
                }}
                anchorOrigin={{
                    vertical: "center",
                    horizontal: "right",
                }}
                transformOrigin={{ horizontal: "right", vertical: "center" }}
            >
                <MenuItem disabled >
                    <HailIcon /> Kh??ch thu?? ph??ng
                </MenuItem>
                <MenuItem onClick={handleClickOpen}>
                    <LogoutIcon /><Typography variant="h9">Tr??? ph??ng</Typography>
                </MenuItem>
                <MenuItem onClick={handleClickOpenModalUpdate}>
                    <LocalCafeOutlinedIcon /> <Typography variant="h7">C???p nh???t d???ch v???</Typography>
                </MenuItem>
                <MenuItem >
                    <ChangeCircleOutlinedIcon /> <Typography variant="h7">?????i ph??ng</Typography>
                </MenuItem>
                <MenuItem disabled>
                    <CleaningServicesOutlinedIcon /><Typography variant="h7">D???n ph??ng</Typography>
                </MenuItem>
                <MenuItem disabled>
                    <ConstructionOutlinedIcon /><Typography variant="h7">S???a ph??ng</Typography>
                </MenuItem>
                <MenuItem disabled>
                    <CalendarTodayIcon /><Typography variant="h7">?????t ph??ng</Typography>
                </MenuItem>
            </Menu>

        </React.Fragment >



    );
}
function areEqual(prevProps, nextProps) {

    if (JSON.stringify(prevProps.styleZoom) === JSON.stringify(nextProps.styleZoom)) {
        // don't re-render/update
        return true
    } else return false
}

export default React.memo(OccupiedRoom, areEqual);