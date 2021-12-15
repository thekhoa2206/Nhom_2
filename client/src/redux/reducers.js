import { combineReducers } from "redux";

import priceReducer from '../modules/rooms/price.reducer';
import userReducer from '../services/users/user.reducer';

export default combineReducers({
    priceReducer,
    userReducer
});