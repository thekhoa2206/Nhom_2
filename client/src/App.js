import React from 'react';
import { BrowserRouter as Router } from "react-router-dom";

import Routes from './react-routes/routes'
import store from "./redux/store";
import { SnackbarProvider } from 'notistack'
import { SnackbarUtilsConfigurator } from "./utils/snackbarUtils";
import { Provider } from "react-redux";

function App() {
  return (
    <React.Fragment>
      <Provider store={store}>
        <SnackbarProvider maxSnack={2} anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        >
          <SnackbarUtilsConfigurator />
          <Router>
            <Routes />
          </Router>
        </SnackbarProvider>
      </Provider>
    </React.Fragment>
  );
}

export default App;
