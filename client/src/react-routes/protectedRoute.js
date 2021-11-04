
import React, { useEffect } from 'react';
import { Route, Redirect } from 'react-router-dom';
import Loading from '../common-components/Loading';
import Layout from '../layout/layout';
import { clearCookie, getCookie } from '../config';
import _ from "lodash"
import { useAppState } from '../AppState';
import useFindUser from '../modules/users/services/useFindUser';


export default function ProtectedRoute(props) {
    const { findUser } = useFindUser()
    useEffect(() => {
        findUser()
    }, [])
    const [state, dispatch] = useAppState()
    const { user, isLoading } = state
    const userRole = user?.role?.map(r => r.nameRole)

    console.log("user", user)
    const { roles, component: Component, ...rest } = props;
    let jwt = getCookie("jwt")
    if (_.isEmpty(jwt)) {
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




