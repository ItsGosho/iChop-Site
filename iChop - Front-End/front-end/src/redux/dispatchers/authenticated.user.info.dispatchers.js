import React from 'react';
import {fetchAuthenticatedUserInfo,removeAuthenticatedUserInfo} from "../actions/authenticated.user.info.actions";


let authenticatedUserInfoDispatchers = (dispatch) => {
    return {
        fetchAuthenticatedUserInfo: () => {
            dispatch(fetchAuthenticatedUserInfo())
        },
        removeAuthenticatedUserInfo: () => {
            dispatch(removeAuthenticatedUserInfo())
        },
    }
};

export default authenticatedUserInfoDispatchers;