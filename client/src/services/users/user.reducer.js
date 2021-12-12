const userReducer = (state, action = {}) => {
    const { type } = action
    console.log("action", action)
    switch (type) {
        case 'GET_USER':
            return {
                ...state,
                user: action.payload.data,
                isLoading: false
            }
        case 'GET_ALL_USERs':
            return {
                ...state,
                userList: action.payload.data,
                isLoading: false
            }
        default:
            return state
    }
}

export default userReducer