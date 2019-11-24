import React, {Component, Fragment} from 'react';
import Roles from "../../../../../constants/enums/roles.constants";
import './PanePostActions.css'
import CommentServices from "../../../../../services/comment.services";
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";
import ReportServices from "../../../../../services/report.services";
import withDispatchers from "../../../../../hocs/with.dispatchers";
import UserServices from "../../../../../services/user.services";


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
        let hasReported = await ReportServices.hasReportedUserProfileComment(authenticatedUsername, this.props.id);
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

        if (response.successful) {
            this.setState({hasReported: true})
        }
    }

    render() {
        let {username: authenticatedUsername, authorities: authenticatedAuthorities} = this.props.authenticatedUserInfo;
        let {creatorUsername, userProfileUsername} = this.props;
        let {hasReported} = this.state;

        let isPostCreator = authenticatedUsername === creatorUsername;
        let isPostOnCreatorProfile = authenticatedUsername === userProfileUsername;
        let isModerator = UserServices.hasRole(authenticatedAuthorities, Roles.MODERATOR);

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

export default withDispatchers(PanePostActions);