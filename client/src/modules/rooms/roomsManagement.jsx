import React, { useState } from 'react';
import Grid from '@mui/material/Grid';
import Room from './room';

function RoomManagement(props) {
    let arr = [{ id: 1 }, { id: 2 }, { id: 3 }, { id: 4 }, { id: 5 }]
    console.log("xxx")
    return (
        <Grid container rowSpacing={4} spacing={2}>
            {
                arr.map((data) =>
                    <React.Fragment key={data.id}>
                        <Grid item md={4} lg={3} xl={1}>
                            <Room id={data.id} />
                        </Grid>
                    </React.Fragment>
                )
            }
        </Grid>


    );
}

export default RoomManagement;