import React, {Component, Fragment} from 'react';
import withState from "../../../../../hocs/with.state";
import Roles from "../../../../../constants/enums/roles.constants";

class CommentOptionsDropdown extends Component {


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

export default withState(CommentOptionsDropdown);