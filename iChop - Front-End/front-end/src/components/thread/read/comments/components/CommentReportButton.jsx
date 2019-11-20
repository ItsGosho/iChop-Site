import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";

class CommentReportButton extends Component {

    constructor(props) {
        super(props);

        this.onReport = this.onReport.bind(this);
    }

    async onReport(reason) {
        let {id} = this.props;
        console.log(id);

        /*TODO: REPORT [COMMENT!]*/
    }
    render() {
        let {id} = this.props;

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

                                    <ModalOpen relationTo={id} title={'Report Comment'}>
                                        <button
                                            className="btn btn-sm">
                                            <small>⚠</small>
                                            <span>Report</span>
                                        </button>
                                    </ModalOpen>

                                    <ReportModal relationTo={id}
                                                 onReport={this.onReport}/>
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