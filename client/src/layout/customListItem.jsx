import ListItem from "@material-ui/core/ListItem"
import ListItemText from "@material-ui/core/ListItemText"
import ListItemIcon from '@mui/material/ListItemIcon';
import React, { useContext } from "react"
import { Link } from "react-router-dom"
import { useLocation } from "react-router-dom"
import { UserContext } from "../context/userContext";

function CustomListItem(props) {
    const { user } = useContext(UserContext)
    const { roles } = props
    const location = useLocation()
    console.log("props", props)
    if (!user?.roles?.some(r => roles.includes(r))) {
        return <div></div>
    }
    else {
        return (
            <React.Fragment key={props.to}>
                <ListItem
                    button
                    component={Link}
                    to={props.to}
                    selected={props.to === location.pathname}
                >
                    <ListItemIcon>{props.icon}</ListItemIcon>
                    <ListItemText primary={props.primary} />
                </ListItem>
            </React.Fragment>

        )
    }

}
export default CustomListItem