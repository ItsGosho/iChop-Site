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

class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoading: true,
            isFound: false,
        }
    }

    async componentDidMount() {
        let {username} = this.props.match.params;
        let user = await UserServices.findByUsername(username);
        let followings = undefined;
        let followers = undefined;
        let posts = [];
        let information = undefined;
        let minecraftUUID = undefined;
        let minecraftAccountName = undefined;
        let totalLikes = 0;
        let totalDislikes = 0;

        if (!user) {
            NotificationHelper.showErrorNotification(`User wasn't found`)
        } else {
            this.props.setUser(user);
            this.props.setFollow(followings, followers);
            this.props.setPosts(posts);
            this.props.setInformation(information);
            this.props.setMinecraft(minecraftUUID, minecraftAccountName);
            this.props.setReaction(totalLikes, totalDislikes);
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
    return {userProfileInfo: states.userProfileInfo}
};

export default withRouter(
    compose(
        connect(mapState, userProfileInfoDispatchers),
    )(UserProfile)
)