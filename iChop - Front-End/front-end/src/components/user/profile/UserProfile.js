import React, {Component, Fragment} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';
import UserProfileCentralContent from "./UserProfileCentralContent";
import {withRouter} from "react-router-dom";
import UserServices from "../../../services/user.services";
import NotificationHelper from "../../../helpers/notification.helper";
import {compose} from "redux";
import {connect} from "react-redux";
import userProfileInfoDispatchers from "../../../redux/dispatchers/user.profile.info.dispatchers";
import CommentServices from "../../../services/comment.services";

class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            isFound: false,
        }
    }

    async componentDidMount() {
        let username = this.props.match.params.username;
        let user = await UserServices.findByUsername(username);
        let profileViewer = this.props.authenticatedUserInfo;

        if (!user) {
            NotificationHelper.showErrorNotification(`User wasn't found`)
        } else {
            this.props.fetchUser(username);
            this.props.fetchFollow(username, profileViewer.username);
            this.props.fetchPosts(username);
            this.props.fetchInformation(username);
            this.props.fetchMinecraft(username);
            this.props.fetchReactions(username);
        }

        this.setState({isLoading: false});
        this.setState({isFound: user !== undefined});
    }

    render() {
        let {isFound, isLoading} = this.state;

        return (
            <Fragment>
                {!isLoading && isFound ? (
                    <div className="container container-user-profile">
                        <div className="row">
                            <UserProfileLeftSideInformation/>
                            <UserProfileCentralContent/>
                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

}

let mapState = (states) => {
    return {...states}
};

export default withRouter(
    compose(
        connect(mapState, userProfileInfoDispatchers),
    )(UserProfile)
)