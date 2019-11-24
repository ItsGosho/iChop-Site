import React, {Component, Fragment} from 'react';
import HTMLEditor from "../../../editors/HTMLEditor";
import CommentServices from "../../../../services/comment.services";
import withDispatchers from "../../../../hocs/with.dispatchers";


class ThreadAddComment extends Component {

    constructor(props) {
        super(props);

        this.state = {
            content: ''
        };

        this.onContentChange = this.onContentChange.bind(this);
        this.onCreate = this.onCreate.bind(this);
    }

    onContentChange(content) {
        this.setState({content})
    }

    async onCreate() {
        let {id} = this.props.threadRead;
        let response = await CommentServices.createThreadComment(id, this.state.content);

        if (response.successful) {
            this.props.showCreateComment(false);
            this.props.fetchThreadById(id);
        }
    }

    render() {
        let {isCreateCommentShow} = this.props.forms;

        return (
            <Fragment>
                {isCreateCommentShow ? (
                    <div>

                        <HTMLEditor placeholder=''
                                    onChangeHTML={this.onContentChange}/>

                        <div className="row d-flex justify-content-center align-items-center thread-comment_box-buttons">

                            <button id="button-createCommentThread-readThreadPage"
                                    className="btn btn-sm btn-success"
                                    type="button"
                                    onClick={this.onCreate}>
                                Comment
                            </button>

                            <button id="button-cancelCommentThread-readThreadPage"
                                    className="btn btn-sm btn-danger thread-comment_box-button_cancel"
                                    type="button" onClick={() => {
                                this.props.showCreateComment(false);
                            }}>
                                Cancel
                            </button>

                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

};


export default withDispatchers(ThreadAddComment);