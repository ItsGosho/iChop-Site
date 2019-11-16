import React from 'react';
import {fetchAllPageable} from "../actions/thread.actions";

let threadDispatchers = (dispatch) => {
    return {
        fetchAllPageable: (page,size) => {
            dispatch(fetchAllPageable(page,size))
        },
    }
};

export default threadDispatchers;