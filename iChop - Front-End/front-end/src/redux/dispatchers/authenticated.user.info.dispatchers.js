import React from 'react';
import {fetchAuthenticatedUser,removeAuthenticatedInfo} from "../actions/authenticated.user.info.actions";


let authenticatedUserInfoDispatchers = (dispatch) => {
    return {
        fetchAuthenticatedUserInfo: () => {
            dispatch(fetchAuthenticatedUser())
        },
        removeAuthenticatedUserInfo: () => {
            dispatch(removeAuthenticatedInfo())
        },
    }
};

export default authenticatedUserInfoDispatchers;