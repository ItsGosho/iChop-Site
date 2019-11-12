import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";
import './PanePostActions.css'
import PropTypes from "prop-types";
import PanePost from "./PanePost";
import withState from "../../../../../hocs/with.state";
import CommentServices from "../../../../../services/comment.services";
import {compose} from "redux";
import {connect} from "react-redux";
import authenticatedUserInfoDispatchers from "../../../../../redux/dispatchers/authenticated.user.info.dispatchers";
import userProfileInfoDispatchers from "../../../../../redux/dispatchers/user.profile.info.dispatchers";
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";

class PanePostActions extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
        this.onReport = this.onReport.bind(this);
    }


    async onDelete() {
        let {username} = this.props.userProfileInfo;
        let {id} = this.props;
        let response = await CommentServices.deleteUserProfileComment(username,id);

        if(response.successful){
            this.props.fetchPosts(username);
        }
    }

    async onReport(reason) {

        console.log(this.props);
        //console.log(`Report post with ID: ${id} and reason [${reason}]`);
    }

    render() {
        let isPostCreator = this.props.authenticatedUserInfo.username === this.props.creatorUsername;
        let isPostOnCreatorProfile = this.props.authenticatedUserInfo.username === this.props.userProfileUsername;
        let isModerator = this.props.authenticatedUserInfo.authority === Roles.MODERATOR;

        return (
            <Fragment>

                {isPostCreator || isPostOnCreatorProfile || isModerator ? (
                    <button className="control-button" onClick={this.onDelete}>❌Delete</button>
                ) : null}

                <ModalOpen relationTo={'reportPost'} title={'Report Post'}>
                    <button className="control-button">
                        <small>🎌</small>
                        <span>Report</span>
                    </button>
                </ModalOpen>

                <ReportModal relationTo={'reportPost'}
                             key={this.props.id}
                             onReport={this.onReport}/>

            </Fragment>
        );
    }
}

let mapState = (states) => {
    return {...states}
};

export default compose(
    connect(mapState,authenticatedUserInfoDispatchers),
    connect(mapState,userProfileInfoDispatchers)
)(PanePostActions);