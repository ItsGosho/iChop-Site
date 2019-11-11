import React, {Component, Fragment} from 'react';
import Roles from "../../../constants/roles.constants";
import PropTypes from "prop-types";
import UserProfileCentralContent from "./UserProfileCentralContent";
import withState from "../../../hocs/with.state";
import UserServices from "../../../services/user.services";
import {withRouter} from "react-router-dom";
import {compose} from "redux";
import {connect} from "react-redux";
import userProfileInfoDispatchers from "../../../redux/dispatchers/user.profile.info.dispatchers";

class UserProfileCentralHead extends Component {

    constructor(props) {
        super(props);

        this.onFollow = this.onFollow.bind(this);
        this.onUnfollow = this.onUnfollow.bind(this);
    }

    async onFollow() {
        let {username} = this.props.userProfileInfo;
        let authenticatedUser = this.props.authenticatedUserInfo;


        await UserServices.follow(username);
        this.props.fetchFollow(username, authenticatedUser.username);
    }

    async onUnfollow() {
        let {username} = this.props.userProfileInfo;
        let authenticatedUser = this.props.authenticatedUserInfo;

        await UserServices.unfollow(username);
        this.props.fetchFollow(username, authenticatedUser.username);
    }

    render() {
        let {username, authority, statusMessage, isViewerFollowingHim, isViewerFollowedByHim} = this.props.userProfileInfo;
        let authenticatedUser = this.props.authenticatedUserInfo;

        return (
            <Fragment>
                <div className="col-md-auto username_and_rank_follow_unfollow">
                    <div className="div-first-head">

                        <div className="row">
                            <div>
                                <div className="col-md-auto head">
                                    <span className="div-first-content">{username}</span>
                                </div>
                            </div>
                        </div>

                        <div className="row">
                            <div>
                                <div className="col-md-auto head">
                                    <span className="div-first-content">{authority}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    {authenticatedUser.username !== '' && authenticatedUser.username !== username ? (
                        <Fragment>
                            <div className="row">
                                <div className="col-md-12 head">

                                    {isViewerFollowingHim ?
                                        (<FollowControlButton onClick={this.onUnfollow} text="Unfollow"/>) :
                                        (<FollowControlButton onClick={this.onFollow} text="Follow"/>)}

                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12 head">
                                    {isViewerFollowedByHim ?
                                        (<small className="is-followed-you">This user is following you ✋</small>)
                                        : null}
                                </div>
                            </div>
                        </Fragment>
                    ) : null}

                    <div className="dropdown-divider"/>
                </div>

                <div className="col-md-auto status">
                    <div className="status-div">
                        <div className="row">
                            <div>

                                {statusMessage != null ? (
                                    <div className="col-md-auto head">
                                        <span className="status-message">{statusMessage}</span>
                                    </div>
                                ) : null}

                            </div>
                        </div>
                    </div>
                </div>
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
    )(UserProfileCentralHead)
)

const FollowControlButton = (props) => {
    let {onClick, text} = props;

    return (
        <a href='#'
           onClick={onClick}
           className="follow-control">
            <span>{text}</span>
        </a>
    )
};