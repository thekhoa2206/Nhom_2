
import React, { useContext } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { UserContext } from '../context/userContext';
import Loading from '../common-components/Loading';
import Layout from '../layout/layout';

export default function ProtectedRoute(props) {
    const { user, isLoading } = useContext(UserContext);
    console.log(user, isLoading);

    const { component: Component,
        ...rest } = props;

    if (isLoading) {
        return <Loading />
    }

    if (user) {
        return (<Route {...rest} render={(props) => (<Layout><Component {...props} /></Layout>)} />)
    } else {
        return <Redirect to='/login' />
    }

}




