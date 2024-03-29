import React, {Component, Fragment} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';
import UserProfileCentralContent from "./UserProfileCentralContent";
import {Redirect, withRouter} from "react-router-dom";
import UserServices from "../../../services/user.services";
import NotificationHelper from "../../../helpers/notification.helper";
import userProfileInfoDispatchers from "../../../redux/dispatchers/user.profile.info.dispatchers";
import withDispatcher from "../../../hocs/with.dispatcher";
import NotificationMessagesConstants from "../../../constants/notification/notification.messages.constants";
import RoutingURLs from "../../../constants/routing/routing.constants";


class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            isFound: true,
        }
    }

    async componentDidMount() {
        this.props.clearProfile();
        let {username} = this.props.match.params;
        let user = await UserServices.findByUsername(username);
        let profileViewer = this.props.authenticatedUserInfo;

        if (!user) {
            NotificationHelper.showErrorNotification(NotificationMessagesConstants.USER_NOT_FOUND)
        } else {
            this.props.fetchUser(username);
            this.props.fetchFollow(username, profileViewer.username);
            this.props.fetchPosts(username);
            this.props.fetchInformation(username);
            this.props.fetchMinecraft(username);
            this.props.fetchReactions(username);
        }

        this.setState({isLoading: false});

        if (!user) {
            this.setState({isFound: false})
        }
    }

    render() {
        let {isFound, isLoading} = this.state;

        console.log(isFound);
        return (
            <Fragment>
                {!isLoading ? (
                    <Fragment>
                        {isFound ? (
                            <div className="container container-user-profile">
                                <div className="row">
                                    <UserProfileLeftSideInformation/>
                                    <UserProfileCentralContent/>
                                </div>
                            </div>
                        ) : <Redirect to={RoutingURLs.HOME} push/>}
                    </Fragment>
                ) : null}
            </Fragment>
        );
    }

}

export default withRouter(withDispatcher(userProfileInfoDispatchers)(UserProfile));