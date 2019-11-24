import React, {Component, Fragment} from 'react';
import ReactionServices from "../../../../../services/reaction.services";
import Roles from "../../../../../constants/enums/roles.constants";
import threadReadDispatchers from "../../../../../redux/dispatchers/thread.read.dispatchers";
import withDispatcher from "../../../../../hocs/with.dispatcher";
import ReactionType from "../../../../../constants/enums/reaction.types.constants";
import PropTypes from 'prop-types';


class CommentReactDropdown extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hasReacted: false
        }
    }

    async componentWillReceiveProps(nextProps, nextContext) {
        /*TODO: fix the 4 calls that are made ,because of the re-rendering,with state...*/
        let {id} = this.props;
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;

        let hasReacted = await ReactionServices.hasReactedThreadComment(id, authenticatedUsername);
        this.setState({hasReacted})
    }

    render() {
        let {id: commentId} = this.props;
        let {id: threadId} = this.props.threadRead;
        let {authority: authenticatedAuthority} = this.props.authenticatedUserInfo;
        let {hasReacted} = this.state;
        let isAuthenticated = authenticatedAuthority !== Roles.GUEST;

        return (
            <Fragment>

                {isAuthenticated && !hasReacted ? (
                    <div
                        className="thread-comments-button_options">
                        <button
                            className="btn btn-sm dropdown-toggle"
                            type="button"
                            data-toggle="dropdown"
                            aria-haspopup="true"
                            aria-expanded="false">
                            <small>💡</small>
                            <span>React</span>
                        </button>
                        <div
                            className="dropdown-menu">

                            <button
                                className="btn btn-sm thread-right_side_button-react"
                                type="submit" onClick={async () => {
                                await ReactionServices.reactComment(commentId, ReactionType.LIKE);
                                this.props.fetchThreadById(threadId);
                            }}>
                                <small>👍🏻</small>
                                <span>Like</span>
                            </button>

                            <button
                                className="btn btn-sm thread-right_side_button-react"
                                type="submit" onClick={async () => {
                                await ReactionServices.reactComment(commentId, ReactionType.DISLIKE);
                                this.props.fetchThreadById(threadId);
                            }}>
                                <small>👎🏻</small>
                                <span>Dislike</span>
                            </button>

                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

}


export default withDispatcher(threadReadDispatchers)(CommentReactDropdown);


CommentReactDropdown.propTypes = {
    id: PropTypes.string,
};