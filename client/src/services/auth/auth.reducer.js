let initState = {
    roles: [],
    services: [],
    bills:[],
    isLoading: false,
}

const authReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_ALL_ROLES':
            return {
                ...state,
                roles: action.payload.data,
                isLoading: false
            }
        case 'GET_ALL_SERVICES':
            return {
                ...state,
                services: action.payload.data,
                isLoading: false
            }
        case 'GET_ALL_BILLS':
            return {
                ...state,
                bills: action.payload.data,
                isLoading: false
            }
        default:
            return state
    }
}

export default authReducer