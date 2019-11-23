import React, {Component, Fragment} from 'react';
import Interweave from "interweave";
import CommentInformation from "./components/CommentInformation";
import CommentCreatorInformation from "./components/CommentCreatorInformation";
import CommentOptionsDropdown from "./components/CommentOptionsDropdown";
import CommentReportButton from "./components/CommentReportButton";
import CommentReactDropdown from "./components/CommentReactDropdown";
import withState from "../../../../hocs/with.state";

class ThreadComments extends Component {


    render() {
        let {comments} = this.props.threadRead;

        return (
            <section id="section-thread_read_comments">

                {
                    (() => comments.map((comment, index) => {
                        let {
                            id,
                            createdOn,
                            likes,
                            dislikes,
                            content,
                            creatorUsername,
                            creatorTotalComments,
                            creatorMinecraftAccountName,
                            creatorMinecraftAccountUUID
                        } = comment;

                        return (
                            <div className="card thread-comments">

                                <div>
                                    <CommentInformation createdOn={createdOn}
                                                        likes={likes}
                                                        dislikes={dislikes}/>
                                </div>

                                <div className="row">
                                    <div className="col-md-3 thread-comment-creator_info_section">
                                        <CommentCreatorInformation uuid={creatorMinecraftAccountUUID}
                                                                   username={creatorUsername}
                                                                   totalComments={creatorTotalComments}
                                                                   minecraftAccountName={creatorMinecraftAccountName}/>
                                    </div>

                                    <div className="content thread-comment-content">
                                        <p className="card-text">
                                            <Interweave content={content}/>
                                        </p>
                                    </div>

                                    <div>

                                    </div>
                                </div>

                                <div>
                                    <div className="btn-group thread-comments-buttons">
                                        <div className="thread-comments-button_options">

                                            <Fragment>
                                                <CommentOptionsDropdown id={id} creatorUsername={creatorUsername}/>

                                                <CommentReportButton id={id}/>

                                                <CommentReactDropdown id={id}/>
                                            </Fragment>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        )
                    }))()
                }
            </section>)
    }
}

export default withState(ThreadComments);