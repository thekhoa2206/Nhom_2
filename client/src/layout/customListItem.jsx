import ListItem from "@material-ui/core/ListItem"
import ListItemText from "@material-ui/core/ListItemText"
import ListItemIcon from '@mui/material/ListItemIcon';
import React from "react"
import { Link } from "react-router-dom"
import { useLocation } from "react-router-dom"

function CustomListItem(props) {
    const location = useLocation()
    console.log("props", props)
    return (
        <ListItem
            button
            component={Link}
            to={props.to}
            selected={props.to === location.pathname}
        >
            <ListItemIcon>{props.icon}</ListItemIcon>
            <ListItemText primary={props.primary} />
        </ListItem>
    )
}
export default CustomListItem