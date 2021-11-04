import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { SnackbarProvider } from 'notistack'
import { SnackbarUtilsConfigurator } from "./utils/snackbarUtils";
import { initialState, combineReducers } from './context/reducers'
import { AppStateProvider } from './AppState'
import priceReducer from './modules/rooms/price.reducer';
import userReducer from './modules/users/services/user.reducer';

const appReducers = combineReducers({
  price: priceReducer,
  user: userReducer
})


ReactDOM.render(
  <React.StrictMode>
    <AppStateProvider reducer={appReducers} initialState={initialState}>
      <SnackbarProvider maxSnack={2} anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      >
        <SnackbarUtilsConfigurator />
        <App />
      </SnackbarProvider>
    </AppStateProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
