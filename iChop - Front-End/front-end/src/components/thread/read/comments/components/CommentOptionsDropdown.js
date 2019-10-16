import React, {Component, Fragment} from 'react';

class CommentOptionsDropdown extends Component {


    render() {

        let isAuthenticated = true;
        let creatorUsername = 'ItsGosho';
        let currentLoggedInUserUsername = 'ItsGosho';
        let hasRoleModerator = true;

        return (
            <Fragment>

                {
                    (() => {
                        if (isAuthenticated && (currentLoggedInUserUsername === creatorUsername || hasRoleModerator)) {
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
                                                className="btn btn-light btn-sm thread-delete_button">
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

export default CommentOptionsDropdown;