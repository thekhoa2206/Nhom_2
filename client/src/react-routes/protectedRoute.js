
import React, { useContext } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { UserContext } from '../context/userContext';
import Loading from '../common-components/Loading';
import Layout from '../layout/layout';
import { clearCookie, getCookie } from '../config';
import _ from "lodash"

export default function ProtectedRoute(props) {
    const { user, isLoading } = useContext(UserContext);
    const userRole = user?.role?.map(r => r.nameRole)
    const { roles, component: Component, ...rest } = props;
    let jwt = getCookie("jwt")
    if (!user || _.isEmpty(jwt)) {
        clearCookie("jwt")
        return <Redirect to='/' />
    }
    if (isLoading) {
        return <Loading />
    }
    if (!userRole?.some(r => roles?.includes(r))) {
        // role not authorised so redirect to home page
        return <Redirect to='/home' />
    }
    return (<Route {...rest} render={(props) => (<Layout><Component {...props} /></Layout>)} />)



}




