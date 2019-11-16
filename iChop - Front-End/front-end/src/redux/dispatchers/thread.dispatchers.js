import React from 'react';
import {fetchAllPageable, fetchTotal} from "../actions/thread.actions";

let threadDispatchers = (dispatch) => {
    return {
        fetchAllPageable: (page,size) => {
            dispatch(fetchAllPageable(page,size))
        },
        fetchTotal: () => {
            dispatch(fetchTotal())
        },
    }
};

export default threadDispatchers;