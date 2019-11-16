import React from 'react';
import {fetchAllPageable, fetchTotal, removeFromAllById} from "../actions/thread.actions";

let threadDispatchers = (dispatch) => {
    return {
        fetchAllPageable: (page,size) => {
            dispatch(fetchAllPageable(page,size))
        },
        fetchTotal: () => {
            dispatch(fetchTotal())
        },
        removeFromAllById: (id) => {
           dispatch(removeFromAllById(id));
        },
    }
};

export default threadDispatchers;