import ListItem from "@material-ui/core/ListItem"
import ListItemText from "@material-ui/core/ListItemText"
import ListItemIcon from '@mui/material/ListItemIcon';
import React from "react"
import { Link } from "react-router-dom"
import { useLocation } from "react-router-dom"
import { useAppState } from "../AppState";

function CustomListItem(props) {
    const [state] = useAppState()
    const user = state.user
    const userRole = user?.role?.map(r => r.nameRole)
    const { roles } = props
    const location = useLocation()
    console.log("props", props)
    if (!userRole?.some(r => roles?.includes(r))) {
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