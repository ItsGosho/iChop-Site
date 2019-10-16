import React, {Component, Fragment} from 'react';
import TextEditor from "../../../editor/TextEditor";

class ThreadAddComment extends Component {


    render() {
        let isAuthenticated = true;

        return (
            <Fragment>
                {
                    (() => {
                        if (isAuthenticated) {
                            return (
                                <div className="commentBox thread-comment_box">

                                    <TextEditor/>

                                    <div id="textarea-content-createComment"
                                         className="thread-comment_box-textarea"
                                         contentEditable="true"/>

                                    <div
                                        className="row d-flex justify-content-center align-items-center thread-comment_box-buttons">

                                        <button id="button-createCommentThread-readThreadPage" className="btn btn-sm btn-success"
                                                type="button">
                                            Comment
                                        </button>

                                        <button id="button-cancelCommentThread-readThreadPage"
                                                className="btn btn-sm btn-danger thread-comment_box-button_cancel" type="button">
                                            Cancel
                                        </button>

                                    </div>
                                </div>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

export default ThreadAddComment;