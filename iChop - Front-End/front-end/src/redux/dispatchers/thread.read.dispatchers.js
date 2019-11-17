import React from 'react';
import {fetchById} from "../actions/thread.read.actions";

let threadReadDispatchers = (dispatch) => {
    return {
        fetchById: (id) => {
            dispatch(fetchById(id))
        },
    }
};

export default threadReadDispatchers;