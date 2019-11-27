import React, {Component, Fragment} from 'react';
import ReactionServices from "../../../../../services/reaction.services";
import withDispatchers from "../../../../../hocs/with.dispatchers";
import ReactionType from "../../../../../constants/enums/reaction.types.constants";

class ThreadButtonsRight extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hasReacted: false
        }
    }

    async componentWillReceiveProps(nextProps, nextContext) {
        /*TODO: fix the 4 calls that are made ,because of the re-rendering,with state...*/
        let {id} = this.props.threadRead;
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;

        let hasReacted = await ReactionServices.hasReactedThread(id, authenticatedUsername);
        this.setState({hasReacted})
    }

    render() {
        let {id} = this.props.threadRead;
        let {isAuthenticated} = this.props.authenticatedUserInfo;
        let {hasReacted} = this.state;

        return (
            <Fragment>
                {isAuthenticated ? (
                    <Fragment>
                        <button
                            id="button-commentThread-readThreadPage"
                            className="btn btn-sm" type="button"
                            aria-haspopup="true" aria-expanded="false" onClick={() => {
                            this.props.showCreateComment(!this.props.forms.isCreateCommentShow);
                        }}>
                            <small>💬</small>
                            <span>Comment</span>
                        </button>

                        {!hasReacted ? (
                            <Fragment>
                                <button className="btn btn-sm dropdown-toggle"
                                        type="button"
                                        data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                    <small>💡</small>
                                    React
                                </button>

                                <div className="dropdown-menu">
                                    <button
                                        className="btn btn-sm thread-right_side_button-react"
                                        type="button" onClick={async () => {
                                        await ReactionServices.reactThread(id, ReactionType.LIKE);
                                        this.props.fetchThreadById(id);
                                    }}>
                                        <small>👍🏻</small>
                                        <span> Like</span>
                                    </button>
                                    <button
                                        className="btn btn-sm thread-right_side_button-react"
                                        type="button" onClick={async () => {
                                        await ReactionServices.reactThread(id, ReactionType.DISLIKE);
                                        this.props.fetchThreadById(id);
                                    }}>
                                        <small>👎🏻</small>
                                        <span> Dislike</span>
                                    </button>
                                </div>
                            </Fragment>
                        ) : null}

                    </Fragment>
                ) : null}
            </Fragment>
        );
    }

}


export default withDispatchers(ThreadButtonsRight);