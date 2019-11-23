import React, {Component, Fragment} from 'react';
import Roles from "../../../../../constants/enums/roles.constants";
import {connect} from "react-redux";
import threadReadDispatchers from "../../../../../redux/dispatchers/thread.read.dispatchers";
import CommentServices from "../../../../../services/comment.services";

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

                {
                    (() => {
                        if (isAuthenticated && (authenticatedUsername === creatorUsername || hasRoleModerator)) {
                            return (
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
                            )
                        }
                    })()
                }
            </Fragment>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState, threadReadDispatchers)(CommentOptionsDropdown);