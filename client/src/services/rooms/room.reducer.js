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
        case 'CHECK_IN':
            const updatedRoom0 = {...action.payload.FEdata, status: action.payload.code};
            const updatedRooms0 = state.roomList.map((room) => {
                if (room.id === updatedRoom0.id) {
                    return updatedRoom0;
                }
                return room;
            });
            return {
                ...state,
                roomList: updatedRooms0,
            };
        case 'READY_ROOM':
            const updatedRoom = {...action.payload.room, status: action.payload.code};
            const updatedRooms = state.roomList.map((room) => {
                if (room.id === updatedRoom.id) {
                    return updatedRoom;
                }
                return room;
            });
            return {
                ...state,
                roomList: updatedRooms,
            };
            
        case 'CLEAN_ROOM':
            const updatedRoom1 = {...action.payload.room, status: action.payload.code};
            const updatedRooms1 = state.roomList.map((room) => {
                if (room.id === updatedRoom1.id) {
                    return updatedRoom1;
                }
                return room;
            });
            return {
                ...state,
                roomList: updatedRooms1,
            };
        case 'FIX_ROOM':
            const updatedRoom2 = {...action.payload.room, status: action.payload.code};
            const updatedRooms2 = state.roomList.map((room) => {
                if (room.id === updatedRoom2.id) {
                    return updatedRoom2;
                }
                return room;
            });
            return {
                ...state,
                roomList: updatedRooms2,
            };
        default:
            return state
    }
}

export default roomReducer