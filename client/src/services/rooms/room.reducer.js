let initState = {
    roomList: [],
    isLoading: false,
}

const roomReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_ALL_ROOMS':
            return {
                ...state,
                roomList: action.payload.data,
                isLoading: false
            }
        default:
            return state
    }
}

export default roomReducer