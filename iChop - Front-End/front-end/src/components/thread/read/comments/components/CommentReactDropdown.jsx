import React, {Component, Fragment} from 'react';
import ReactionServices from "../../../../../services/reaction.services";
import Roles from "../../../../../constants/enums/roles.constants";
import {compose} from "redux";
import {connect} from "react-redux";
import formsDispatchers from "../../../../../redux/dispatchers/forms.dispatchers";
import threadReadDispatchers from "../../../../../redux/dispatchers/thread.read.dispatchers";

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
        let {authority: authenticatedAuthority} = this.props.authenticatedUserInfo;
        let {hasReacted} = this.state;
        let isAuthenticated = authenticatedAuthority !== Roles.GUEST;

        console.log(hasReacted);
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
                ) : null}
            </Fragment>
        );
    }

}

let mapState = (state) => {
   return {...state};
};

export default connect(mapState, threadReadDispatchers)(CommentReactDropdown);