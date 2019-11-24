import React, {Component, Fragment} from 'react';
import Roles from "../../../../../constants/enums/roles.constants";
import threadReadDispatchers from "../../../../../redux/dispatchers/thread.read.dispatchers";
import CommentServices from "../../../../../services/comment.services";
import withDispatcher from "../../../../../hocs/with.dispatcher";
import PropTypes from 'prop-types';


class CommentOptionsDropdown extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
    }

    async onDelete() {
        let {id: threadReadId} = this.props.threadRead;
        let {id: commentId} = this.props;

        let response = await CommentServices.deleteThreadComment(threadReadId, commentId);

        if (response.successful) {
            this.props.fetchThreadComments(threadReadId);
        }
    }

    render() {
        let {creatorUsername} = this.props;
        let {username: authenticatedUsername, authority: authenticatedAuthority} = this.props.authenticatedUserInfo;
        let isAuthenticated = authenticatedUsername !== undefined;
        let hasRoleModerator = authenticatedAuthority !== Roles.GUEST && authenticatedAuthority !== Roles.USER;

        return (
            <Fragment>

                {isAuthenticated && (authenticatedUsername === creatorUsername || hasRoleModerator) ? (
                    <Fragment>

                        <button
                            className="btn btn-sm dropdown-toggle"
                            type="button"
                            data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                            <small>⚙</small>
                            <span>Options</span>
                        </button>

                        <div
                            className="dropdown-menu">
                            <button type="submit"
                                    className="btn btn-light btn-sm thread-delete_button"
                                    onClick={this.onDelete}>
                                <small>❌</small>
                                <span>Delete</span>
                            </button>
                        </div>
                    </Fragment>
                ) : null}
            </Fragment>
        );
    }

}

export default withDispatcher(threadReadDispatchers)(CommentOptionsDropdown);


CommentOptionsDropdown.propTypes = {
    id: PropTypes.string,
    creatorUsername: PropTypes.string,
};