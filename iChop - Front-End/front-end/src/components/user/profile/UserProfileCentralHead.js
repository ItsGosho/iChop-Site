import React, {Component, Fragment} from 'react';
import Roles from "../../../constants/roles.constants";
import PropTypes from "prop-types";
import UserProfileCentralContent from "./UserProfileCentralContent";

class UserProfileCentralHead extends Component {

    constructor(props) {
        super(props);

        this.onFollow = this.onFollow.bind(this);
        this.onUnfollow = this.onUnfollow.bind(this);
    }


    onFollow() {
        console.log("Follow");
    }

    onUnfollow() {
        console.log("Unfollow");
    }

    render() {
        let {username,authority} = this.props.user;

        let statusMessage = 'Hello!';
        let isAuthenticated = true;

        let isFollowedMe = true;
        let isImFollowedHim = false;

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

                    {isAuthenticated ? (
                        <Fragment>
                            <div className="row">
                                <div className="col-md-12 head">

                                    {isImFollowedHim ?
                                        (<FollowControlButton onClick={this.onUnfollow} text="Unfollow"/>) :
                                        (<FollowControlButton onClick={this.onFollow} text="Follow"/>)}

                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12 head">
                                    {isFollowedMe ?
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

UserProfileCentralHead.propTypes = {
    user: PropTypes.object
};

export default UserProfileCentralHead;

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