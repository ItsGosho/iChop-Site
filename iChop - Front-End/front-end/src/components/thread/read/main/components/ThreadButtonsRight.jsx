import React, {Component, Fragment} from 'react';
import {connect} from "react-redux";
import formsDispatchers from "../../../../../redux/dispatchers/forms.dispatchers";
import Roles from "../../../../../constants/enums/roles.constants";

class ThreadButtonsRight extends Component {

    render() {
        let isAuthenticated = this.props.authenticatedUserInfo.authority !== Roles.GUEST;
        console.log(this.props);
        let isLikedThreadAlready = false;

        return (
            <Fragment>
                {
                    (() => {
                        if (isAuthenticated) {
                            return (
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

                                    <button className="btn btn-sm dropdown-toggle"
                                            type="button"
                                            data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="false">
                                        <small>💡</small>
                                        React
                                    </button>

                                    {
                                        (() => {
                                            if (!isLikedThreadAlready) {
                                                return (
                                                    <div className="dropdown-menu">
                                                        <button
                                                            className="btn btn-sm thread-right_side_button-react"
                                                            type="button">
                                                            <small>👍🏻</small>
                                                            <span> Like</span>
                                                        </button>
                                                        <button
                                                            className="btn btn-sm thread-right_side_button-react"
                                                            type="button">
                                                            <small>👎🏻</small>
                                                            <span> Dislike</span>
                                                        </button>
                                                    </div>
                                                );
                                            }
                                        })()
                                    }

                                </Fragment>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

let mapState = (state) => {
    return {...state}
};

export default connect(mapState, formsDispatchers)(ThreadButtonsRight);