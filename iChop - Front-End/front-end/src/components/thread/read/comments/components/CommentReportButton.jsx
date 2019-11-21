import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";
import ReportServices from "../../../../../services/report.services";

class CommentReportButton extends Component {

    constructor(props) {
        super(props);

        this.onReport = this.onReport.bind(this);
    }

    async onReport(reason) {
        let {id} = this.props;

        ReportServices.reportThreadComment(id,reason);
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