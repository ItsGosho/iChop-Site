import React, {Component, Fragment} from 'react';
import CommentReportModal from "./CommentReportModal";

class CommentReportButton extends Component {


    render() {

        return (
            <Fragment>
                {
                    (() => {
                        let isAuthenticated = true;
                        let isCurrentLoggedInUserReportedTheComment = false;

                        if (isAuthenticated && !isCurrentLoggedInUserReportedTheComment) {
                            return (
                                <div
                                    className="thread-comments-button_report">


                                    <button
                                        className="btn btn-sm"
                                        type="button">
                                        <small>⚠</small>
                                        <span>Report</span>
                                    </button>

                                    <CommentReportModal/>
                                </div>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

export default CommentReportButton;