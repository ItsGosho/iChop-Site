import React from 'react';
import {fetchById, fetchThreadStatisticsInformation, increaseReactions} from "../actions/thread.read.actions";

let threadReadDispatchers = (dispatch) => {
    return {
        fetchThreadById: (id) => {
            dispatch(fetchById(id))
        },
        increaseReactions: () => {
            dispatch(increaseReactions())
        }
    }
};

export default threadReadDispatchers;