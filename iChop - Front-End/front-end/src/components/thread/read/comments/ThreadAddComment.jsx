import React, {Component, Fragment} from 'react';
import HTMLEditor from "../../../editors/HTMLEditor";
import withState from "../../../../hocs/with.state";
import Roles from "../../../../constants/enums/roles.constants";
import formsDispatchers from "../../../../redux/dispatchers/forms.dispatchers";
import {connect} from "react-redux";

class ThreadAddComment extends Component {


    render() {
        let {isCreateCommentShow} = this.props.forms;

        return (
            <Fragment>
                {isCreateCommentShow ? (
                    <div>

                        <HTMLEditor/>

                        <div
                            className="row d-flex justify-content-center align-items-center thread-comment_box-buttons">

                            <button id="button-createCommentThread-readThreadPage"
                                    className="btn btn-sm btn-success"
                                    type="button">
                                Comment
                            </button>

                            <button id="button-cancelCommentThread-readThreadPage"
                                    className="btn btn-sm btn-danger thread-comment_box-button_cancel"
                                    type="button" onClick={()=>{
                                this.props.showCreateComment(!this.props.forms.isCreateCommentShow);
                            }}>
                                Cancel
                            </button>

                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

}

let mapState = (state) => {
    return {...state}
};

export default connect(mapState, formsDispatchers)(ThreadAddComment);