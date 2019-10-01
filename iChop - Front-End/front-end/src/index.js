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

let store = createStore(
    combineReducers(reducers),
    compose(applyMiddleware(thunk, logger), window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())
);

ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </Provider>,
    document.getElementById('root'));
