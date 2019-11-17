import React, {Component, Fragment} from 'react';

class CommentReactDropdown extends Component {


    render() {

        return (
            <Fragment>
                {
                    (() => {
                        let isAuthenticated = true;
                        let isReactedAlready = false;

                        if (isAuthenticated && !isReactedAlready) {
                            return (
                                <Fragment>
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
                                                type="submit">
                                                <small>👍🏻</small>
                                                <span>Like</span>
                                            </button>

                                            <button
                                                className="btn btn-sm thread-right_side_button-react"
                                                type="submit">
                                                <small>👎🏻</small>
                                                <span>Dislike</span>
                                            </button>

                                        </div>
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

export default CommentReactDropdown;