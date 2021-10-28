import React, { useState } from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { CardActionArea } from '@mui/material';
import CheckInModal from './checkInModal';

function Room(props) {
    const { id } = props
    const [open, setOpen] = useState(false);
    const [color, setColor] = useState("green");
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const handleSubmit = () => {
        if (color == "red") {
            setColor("orange")
        } else if (color == "orange") {
            setColor("green")
        }
        else setColor("red");
        setOpen(false);
    };
    console.log("yyy")
    return (
        <React.Fragment>
            <CheckInModal open={open} id={id} handleSubmit={handleSubmit} handleClose={handleClose} />
            <Card sx={{ maxWidth: 345 }}>
                <CardActionArea onClick={handleClickOpen}>
                    <CardContent style={{ backgroundColor: color }}>
                        <Typography color="white" gutterBottom variant="h5" component="div">
                            Phòng {id}
                        </Typography>
                        <Typography color="white" variant="body2" >
                            Lizards are a widespread group of squamate reptiles, with over 6,000
                            species, ranging across all continents except Antarctica
                        </Typography>
                    </CardContent>
                </CardActionArea>

            </Card>

        </React.Fragment >



    );
}
export default Room