import React from 'react';
import {set,remove} from "../actions/authenticated.user.info.actions";


let authenticatedUserInfoDispatchers = (dispatch) => {
    return {
        set: (user) => {
            dispatch(set(user))
        },
        remove: () => {
            dispatch(remove())
        },
    }
};

export default authenticatedUserInfoDispatchers;