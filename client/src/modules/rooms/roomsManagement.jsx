import React, { useState } from 'react';
import Grid from '@mui/material/Grid';
import ReadyRoom from './readyRoom';
import OccupiedRoom from './occupiedRoom';
import DirtyRoom from './dirtyRoom';
import { Slider, Box } from '@mui/material';
import OutOfServiceRoom from './outOfServiceRoom';
import { useGetLyric2 } from '../../utils/apiCalls';
import Loading from '../../common-components/Loading';

function RoomManagement(props) {
    const [zoom, setZoom] = useState({
        width: 230, height: 170
    })
    let arr = [{ id: 1, status: 'occupied' }, { id: 2, status: 'ready' }, { id: 3, status: 'dirty' }, { id: 4, status: 'out-of-service' }, { id: 5, status: 'ready' }]
    function valuetext(value) {
        return `${value}%`;
    }
    const sliderStyle = {
        background: "white",
        position: "fixed",
        left: 0,
        right: 0,
        bottom: 0,
        height: "50px",
        maxWidth: "15%",
        marginLeft: 'auto',
        marginRight: 20,
    }
    const handleChange = (event, newValue) => {
        switch (newValue) {
            case 0:
                setZoom({ width: 180, height: 120 })
                break;
            case 20:
                setZoom({ width: 200, height: 140 })
                break;
            case 40:
                setZoom({ width: 220, height: 160 })
                break;
            case 60:
                setZoom({ width: 230, height: 170 })
                break;
            case 80:
                setZoom({ width: 260, height: 190 })
                break;
            case 100:
                setZoom({ width: 300, height: 200 })
                break;
        }
    };
    return (
        // <React.Fragment>
        //     {isLoading ?
        //         <Loading />
        //         :
        <React.Fragment>
            <Grid container rowSpacing={4} spacing={2}>
                {
                    arr.map((data) =>
                        <React.Fragment key={data.id} >
                            <Grid item xl={2} lg={3} md={4} sm={6} xs={12}>
                                {data.status === "ready" && <ReadyRoom id={data.id} styleZoom={zoom} />}
                                {data.status === "occupied" && <OccupiedRoom id={data.id} styleZoom={zoom} />}
                                {data.status === "dirty" && <DirtyRoom id={data.id} styleZoom={zoom} />}
                                {data.status === "out-of-service" && <OutOfServiceRoom id={data.id} styleZoom={zoom} />}
                            </Grid>
                        </React.Fragment>
                    )
                }
            </Grid>
        </React.Fragment>
        // {/* <div style={sliderStyle}>
        //     <Slider
        //         aria-label="Zoom"
        //         defaultValue={0}
        //         getAriaValueText={valuetext}
        //         valueLabelDisplay="auto"
        //         onChange={handleChange}
        //         step={20}
        //         marks
        //         min={0}
        //         max={100}
        //     />
        // </div> */}
        // }


        // </React.Fragment>



    );
}

export default RoomManagement;