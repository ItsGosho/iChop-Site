import React from 'react';
import {setProfileInfo} from "../actions/user.profile.actions";


let userProfileInfoDispatchers = (dispatch) => {
    return {
        setInfo: (user) => {
            dispatch(setProfileInfo(user))
        }
    }
};

export default userProfileInfoDispatchers;