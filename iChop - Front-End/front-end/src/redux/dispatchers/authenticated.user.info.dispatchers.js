import React from 'react';
import {set,remove} from "../actions/authenticated.user.info.actions";


let authenticatedUserInfoDispatchers = (dispatch) => {
    return {
        setAuthenticatedUser: (user) => {
            dispatch(set(user))
        },
        removeAuthenticatedUser: () => {
            dispatch(remove())
        },
    }
};

export default authenticatedUserInfoDispatchers;