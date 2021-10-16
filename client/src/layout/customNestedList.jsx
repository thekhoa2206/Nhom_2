import ListItem from "@material-ui/core/ListItem"
import ListItemText from "@material-ui/core/ListItemText"
import ListItemIcon from '@mui/material/ListItemIcon';
import React, { useContext } from "react"
import { Link } from "react-router-dom"
import { useLocation } from "react-router-dom"
import Collapse from '@mui/material/Collapse';
import List from '@mui/material/List';
import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import { UserContext } from "../context/userContext";

function CustomNestedList(props) {
    const { user } = useContext(UserContext)
    const { roles } = props
    const location = useLocation()
    const [open, setOpen] = React.useState(false);
    // const [selected, setSelected] = React.useState(true);

    const handleClick = () => {
        setOpen(!open);
    };
    // const handleSelected = () => {
    //     setSelected(true);
    // };
    if (!user?.roles?.some(r => roles.includes(r))) {
        return <div></div>
    } else {
        return (
            <React.Fragment>
                <ListItem
                    button
                    onClick={handleClick}
                >
                    <ListItemIcon>{props.iconFather}</ListItemIcon>
                    <ListItemText primary={props.primaryFather} />
                    {open ? <ExpandLess /> : <ExpandMore />}
                </ListItem>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        {props?.kids.map((kid, index) =>
                            <ListItem
                                key={index}
                                sx={{ pl: 4 }}
                                button
                                component={Link}
                                to={kid.to}
                                selected={kid.to === location.pathname}
                            >
                                <ListItemIcon>{kid.icon}</ListItemIcon>
                                <ListItemText primary={kid.primary} />
                            </ListItem>
                        )}

                    </List>
                </Collapse>
            </React.Fragment>

        )

    }

}
export default CustomNestedList