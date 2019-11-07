import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import {BrowserRouter} from "react-router-dom";
import {applyMiddleware, combineReducers, compose, createStore} from "redux";
import reducers from './redux/reducers'
import thunk from 'redux-thunk'
import logger from 'redux-logger'
import Provider from "react-redux/es/components/Provider";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-notifications-component/dist/theme.css'
import UserServices from "./services/user.services";
import {set} from "./redux/actions/authenticated.user.info.actions";

let store = createStore(
    combineReducers(reducers),
    compose(applyMiddleware(thunk/*, logger*/), window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__() || compose)
);

(async () => {
     let user = await UserServices.retrieveUserByToken();
     store.dispatch(set(user));
})();

ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </Provider>,
    document.getElementById('root')
);
