import React, {Component} from 'react';

class CommentReportModal extends Component {


    render() {

        return (
            <div className="modal"
                 role="dialog">
                <div
                    className="modal-dialog">

                    <div
                        className="modal-content">
                        <div
                            className="modal-header">
                            <h4 className="modal-title">Report
                                to the
                                kings:</h4>
                        </div>
                        <div
                            className="modal-body">
                            <textarea
                                className="thread-comments-modal"
                                name="reason"
                                placeholder="Reason..."/>
                        </div>
                        <div
                            className="modal-footer">
                            <button
                                type="button"
                                className="btn btn-default">
                                Report
                            </button>
                            <button
                                type="button"
                                className="btn btn-default"
                                data-dismiss="modal">
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            < /div>
        );
    }

}

export default CommentReportModal;