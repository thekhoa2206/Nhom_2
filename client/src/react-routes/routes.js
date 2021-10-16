import React from 'react';
import { Route, Switch } from "react-router-dom";
import Login from '../modules/auth/login'
import Home from '../modules/home'
import LandingPage from '../modules/landingPage';
import ProtectedRoute from './protectedRoute'
import { UserContext } from '../context/userContext';
import useFindUser from '../modules/users/services/useFindUser'
import AuthRoute from './authRoute';
import Statistics from '../modules/statistics/statistics';
import Register from '../modules/auth/register';
import RoomManagement from '../modules/rooms/roomsManagement';
import Statistics2 from '../modules/statistics/statistics2';
function Routes(props) {
    const { user, setUser, isLoading } = useFindUser()
    return (
        <UserContext.Provider value={{ user, setUser, isLoading }}>
            <Switch>
                <Route
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
                    roles={['admin']}
                />
                <ProtectedRoute
                    path="/home"
                    exact
                    component={Home}
                />
                <ProtectedRoute
                    path="/statistics"
                    exact
                    component={Statistics}
                    roles={['admin']}
                />
                <ProtectedRoute
                    path="/statistics2"
                    exact
                    component={Statistics2}
                    roles={['admin', 'a']}
                />
                <ProtectedRoute
                    path="/rooms"
                    exact
                    component={RoomManagement}
                    roles={['admin', 'a']}
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
        </UserContext.Provider>
    )

}

export default Routes;