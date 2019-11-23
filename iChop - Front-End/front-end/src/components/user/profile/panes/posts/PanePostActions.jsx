import React, {Component, Fragment} from 'react';
import Roles from "../../../../../constants/enums/roles.constants";
import './PanePostActions.css'
import CommentServices from "../../../../../services/comment.services";
import {compose} from "redux";
import {connect} from "react-redux";
import authenticatedUserInfoDispatchers from "../../../../../redux/dispatchers/authenticated.user.info.dispatchers";
import userProfileInfoDispatchers from "../../../../../redux/dispatchers/user.profile.info.dispatchers";
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";
import ReportServices from "../../../../../services/report.services";

class PanePostActions extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hasReported: false
        };

        this.onDelete = this.onDelete.bind(this);
        this.onReport = this.onReport.bind(this);
    }

    async componentWillReceiveProps(nextProps, nextContext) {
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;
        let hasReported = await ReportServices.hasReportedUserProfileComment(authenticatedUsername,this.props.id);
        this.setState({hasReported});
    }


    async onDelete() {
        let {username} = this.props.userProfileInfo;
        let {id} = this.props;
        let response = await CommentServices.deleteUserProfileComment(username, id);

        if (response.successful) {
            this.props.fetchPosts(username);
        }
    }

    async onReport(reason) {
        let {id} = this.props;
        let response = await ReportServices.reportUserProfileComment(id, reason);

        if(response.successful){
            this.setState({hasReported: true})
        }
    }

    render() {
        let isPostCreator = this.props.authenticatedUserInfo.username === this.props.creatorUsername;
        let isPostOnCreatorProfile = this.props.authenticatedUserInfo.username === this.props.userProfileUsername;
        let isModerator = this.props.authenticatedUserInfo.authority === Roles.MODERATOR;
        let {hasReported} = this.state;

        return (
            <Fragment>

                {isPostCreator || isPostOnCreatorProfile || isModerator ? (
                    <button className="control-button" onClick={this.onDelete}>❌Delete</button>
                ) : null}

                {!hasReported ? (
                    <Fragment>
                        <ModalOpen relationTo={this.props.id} title={'Report Post'}>
                            <button className="control-button">
                                <small>🎌</small>
                                <span>Report</span>
                            </button>
                        </ModalOpen>

                        <ReportModal relationTo={this.props.id}
                                     onReport={this.onReport}/>
                    </Fragment>
                ) : null}

            </Fragment>
        );
    }
}

let mapState = (states) => {
    return {...states}
};

export default compose(
    connect(mapState, authenticatedUserInfoDispatchers),
    connect(mapState, userProfileInfoDispatchers)
)(PanePostActions);