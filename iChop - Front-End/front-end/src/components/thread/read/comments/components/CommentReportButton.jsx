import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";

class CommentReportButton extends Component {

    constructor(props) {
        super(props);

        this.state = {
            reason: ''
        };

        this.onReasonValueChange = this.onReasonValueChange.bind(this);
    }

    onReasonValueChange(value) {
        this.setState({reason: value})
    }

    render() {
        let {reason} = this.state;
        console.log(reason);

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

                                    <ReportModal relationTo={'reportComment'}
                                                 value={reason}
                                                 onValueChange={this.onReasonValueChange}/>
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