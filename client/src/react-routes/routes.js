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
function Routes(props) {
    const {
        user,
        setUser,
        isLoading } = useFindUser();
    console.log("user", user)
    return (
        <UserContext.Provider value={{ user, setUser, isLoading }}>
            <Switch>
                <Route path="/" exact component={LandingPage} />
                <ProtectedRoute path="/home" exact component={Home} />
                <ProtectedRoute path="/statistics" exact component={Statistics} />
                <AuthRoute path="/login" exact component={Login} />
                <Route path="/logout" exact />
                <Route path="*" component={() => "404 NOT FOUND"} />
            </Switch>
        </UserContext.Provider>
    )

}

export default Routes;