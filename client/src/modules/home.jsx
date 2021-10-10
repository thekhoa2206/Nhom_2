import React, { useState } from 'react';
import Room from './rooms/room'
import Grid from '@mui/material/Grid';
function Home(props) {
    let arr = [{ id: 1 }, { id: 2 }, { id: 3 }, { id: 4 }, { id: 5 }]
    return (
        <React.Fragment>
            <Grid container rowSpacing={4} spacing={2}>
                {
                    arr.map(data => {
                        return (
                            <Grid item md={4} lg={3} xl={1}>
                                <Room id={data.id} />
                            </Grid>
                        )
                    })
                }
            </Grid>

        </React.Fragment>

    );
}

export default Home;