import React, { useContext } from 'react';
import { Redirect } from 'react-router-dom';
import { UserContext } from '../context/userContext';
import { Link } from 'react-router-dom';
import Button from '@mui/material/Button';
function LandingPage() {
    const { user } = useContext(UserContext);
    if (user) {
        <Redirect to='/home' />
    }

    return (
        <React.Fragment>
            <div className="page">
                <h3>This is the public landing page</h3>
            </div>
            <Link to="/login">
                <Button variant="contained" color="success" >Login</Button>
            </Link>
        </React.Fragment>

    )
}

export default LandingPage;