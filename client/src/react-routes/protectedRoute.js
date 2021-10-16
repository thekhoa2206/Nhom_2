
import React, { useContext } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { UserContext } from '../context/userContext';
import Loading from '../common-components/Loading';
import Layout from '../layout/layout';
import { clearCookie } from '../config';

export default function ProtectedRoute(props) {
    const { user, isLoading } = useContext(UserContext);
    console.log(user, isLoading);

    const { roles, component: Component, ...rest } = props;
    if (!user) {
        console.log("cllear")
        clearCookie("jwt")
        return <Redirect to='/' />
    }
    if (isLoading) {
        console.log("d")
        return <Loading />
    }
    if (!user?.roles?.some(r => roles.includes(r))) {
        console.log("a")
        // role not authorised so redirect to home page
        return <Redirect to='/home' />
    }
    return (<Route {...rest} render={(props) => (<Layout><Component {...props} /></Layout>)} />)



}




