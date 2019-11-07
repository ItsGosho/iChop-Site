import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Redirect} from "react-router-dom";
import {connect} from "react-redux";
import authenticatedUserInfoDispatchers from "../../../redux/dispatchers/authenticated.user.info.dispatchers";
import NotificationHelper from "../../../helpers/notification.helper";
import UserServices from "../../../services/user.services";

class Logout extends Component {

    constructor(props) {
        super(props);

        this.logout = this.logout.bind(this);
    }

    componentDidMount() {
        this.logout();
    }

    logout() {
        let username = this.props.authenticatedUserInfo.username;

        if (username) {
            UserServices.logout();
            this.props.removeAuthenticatedUser();
        } else {
            NotificationHelper.showErrorNotification('You are not logged in!');
        }
    }

    render() {
        let isSuccessful = true;

        return (
            <Fragment>
                {isSuccessful ? <Redirect to={RoutingURLs.HOME} push/> : null}
            </Fragment>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState, authenticatedUserInfoDispatchers)(Logout);