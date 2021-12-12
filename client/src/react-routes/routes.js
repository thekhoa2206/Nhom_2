import React, { useEffect } from 'react';
import { Route, Switch } from "react-router-dom";
import Login from '../modules/auth/login'
import Home from '../modules/home'
import LandingPage from '../modules/landingPage';
import ProtectedRoute from './protectedRoute'
import AuthRoute from './authRoute';
import Statistics from '../modules/statistics/statistics';
import Register from '../modules/auth/register';
import RoomManagement from '../modules/rooms/roomsManagement';
import Statistics2 from '../modules/statistics/statistics2';
import Reservation from '../modules/Reservation/reservation'
function Routes(props) {
    return (
        <Switch>
            <AuthRoute
                path="/"
                exact
                component={LandingPage}
            />
            <AuthRoute
                path="/login"
                exact
                component={Login}
            />
            <ProtectedRoute
                path="/register"
                exact
                component={Register}
                roles={['ROLE_ADMIN']}
            />
            <ProtectedRoute
                path="/home"
                exact
                component={Home}
                roles={['ROLE_ADMIN', 'ROLE_STAFF']}
            />
            <ProtectedRoute
                path="/reservation"
                exact
                component={Reservation}
                roles={['ROLE_ADMIN', 'ROLE_STAFF']}
            />
            <ProtectedRoute
                path="/statistics"
                exact
                component={Statistics}
                roles={['ROLE_ADMIN']}
            />
            <ProtectedRoute
                path="/statistics2"
                exact
                component={Statistics2}
                roles={['ROLE_ADMIN', 'ROLE_STAFF']}
            />
            <ProtectedRoute
                path="/rooms"
                exact
                component={RoomManagement}
                roles={['ROLE_ADMIN', 'ROLE_STAFF']}
            />
            <Route
                path="/logout"
                exact
            />
            <Route
                path="*"
                component={() => "404 NOT FOUND"}
            />
        </Switch>
    )

}

export default Routes;