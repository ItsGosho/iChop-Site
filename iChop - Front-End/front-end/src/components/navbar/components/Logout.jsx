import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import {Redirect} from "react-router-dom";
import authenticatedUserInfoDispatchers from "../../../redux/dispatchers/authenticated.user.info.dispatchers";
import NotificationHelper from "../../../helpers/notification.helper";
import UserServices from "../../../services/user.services";
import withDispatcher from "../../../hocs/with.dispatcher";
import Roles from "../../../constants/enums/roles.constants";
import NotificationMessagesConstants from "../../../constants/notification/notification.messages.constants";


class Logout extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isSuccessful: false,
        };
    }

    componentDidMount() {
        let {authority} = this.props.authenticatedUserInfo;

        if (authority !== Roles.GUEST) {
            UserServices.logout();
            this.props.removeAuthenticatedUserInfo();

            this.setState({isSuccessful: true});
            NotificationHelper.showSuccessNotification(NotificationMessagesConstants.LOGOUT_SUCCESSFUL)
        }
    }

    render() {
        let {isSuccessful} = this.state;

        return (
            <Fragment>
                {isSuccessful ? <Redirect to={RoutingURLs.HOME} push/> : null}
            </Fragment>
        );
    }

}

export default withDispatcher(authenticatedUserInfoDispatchers)(Logout);