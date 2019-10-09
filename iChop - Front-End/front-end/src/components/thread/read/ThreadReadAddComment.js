import React,{Component} from 'react';

class ThreadReadAddComment extends Component {


    render() {

        return (
            <div className="commentBox thread-comment_box" sec:authorize="isAuthenticated()">

                <th:block th:insert="editor/text-editor"></th:block>

                <div id="textarea-content-createComment"
                     className="thread-comment_box-textarea"
                     contentEditable="true">

                </div>
                <div className="row d-flex justify-content-center align-items-center thread-comment_box-buttons">
                    <button sec:authorize="isAuthenticated()" id="button-createCommentThread-readThreadPage"
                            className="btn btn-sm" type="button">
                        Comment
                    </button>
                    <button id="button-cancelCommentThread-readThreadPage"
                            className="btn btn-sm thread-comment_box-button_cancel" type="button">
                        Cancel
                    </button>
                </div>
            </div>

        );
    }

}

export default ThreadReadAddComment;