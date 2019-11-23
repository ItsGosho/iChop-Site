import {compose} from "redux";
import {connect} from "react-redux";
import authenticatedUserInfoDispatchers from "../redux/dispatchers/authenticated.user.info.dispatchers";
import formsDispatchers from "../redux/dispatchers/forms.dispatchers";
import threadReadDispatchers from "../redux/dispatchers/thread.read.dispatchers";
import threadAllDispatchers from "../redux/dispatchers/thread.all.dispatchers";
import userProfileInfoDispatchers from "../redux/dispatchers/user.profile.info.dispatchers";

const withDispatchers = (Comp) => {
    const mapToState = (states) => ({...states});

    return compose(
        connect(mapToState, authenticatedUserInfoDispatchers),
        connect(mapToState, formsDispatchers),
        connect(mapToState, threadReadDispatchers),
        connect(mapToState, threadAllDispatchers),
        connect(mapToState, userProfileInfoDispatchers),
    )(Comp);
};


export default withDispatchers;