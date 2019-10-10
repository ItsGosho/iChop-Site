import React, {Component, Fragment} from 'react';
import CommentReportModal from "./CommentReportModal";
import ModalOpen from "../../../modal/ModalOpen";

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

                                    <ModalOpen relationTo={'reportComment'} title={'Report Comment'}>
                                        <button
                                            className="btn btn-sm">
                                            <small>⚠</small>
                                            <span>Report</span>
                                        </button>
                                    </ModalOpen>

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