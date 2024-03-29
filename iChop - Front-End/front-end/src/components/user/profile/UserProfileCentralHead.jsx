import React, {Component, Fragment} from 'react';
import UserServices from "../../../services/user.services";
import {Link, withRouter} from "react-router-dom";
import PropTypes from 'prop-types';
import userProfileInfoDispatchers from "../../../redux/dispatchers/user.profile.info.dispatchers";
import withDispatcher from "../../../hocs/with.dispatcher";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";
import Roles from "../../../constants/enums/roles.constants";

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
        let {username: authenticatedUsername,isAuthenticated,authorities} = this.props.authenticatedUserInfo;

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

                    {isAuthenticated && authenticatedUsername !== username ? (
                        <Fragment>

                            {UserServices.hasRole(authorities,Roles.ADMIN) ? (
                                <div className="row">
                                    <div className="col-md-12 head">
                                        <Link to={PrefixURLs.USER_CONTROL_PREFIX(username)} className="options-control">⚙</Link>
                                    </div>
                                </div>
                            ) : null}

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

export default withRouter(withDispatcher(userProfileInfoDispatchers)(UserProfileCentralHead))

const FollowControlButton = ({onClick, text}) => (
    <a href='#'
       onClick={onClick}
       className="follow-control">
        <span>{text}</span>
    </a>
);

FollowControlButton.propTypes = {
    text: PropTypes.string,
    onClick: PropTypes.func,
};