import { combineReducers } from "redux";

import priceReducer from '../modules/rooms/price.reducer';
import userReducer from '../services/users/user.reducer';
import authReducer from '../services/auth/auth.reducer';

export default combineReducers({
    priceReducer,
    userReducer,
    authReducer
});