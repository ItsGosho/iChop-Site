import React,{Component} from 'react';

class PanePostReportModal extends Component {

    render() {

        return (
            <div className="modal" th:id="'modal-report_post-'+*{id}" role="dialog">
                <div className="modal-dialog">

                    <div className="modal-content">
                        <div className="modal-header">
                            <h4 className="modal-title">Report to the kings:</h4>
                        </div>
                        <form method="post"
                              th:action="@{/post/{postId}/report(postId=*{id})}">
                            <div className="modal-body">
                                        <textarea className="post-modal_report-textarea"
                                                  name="reason" placeholder="Reason..."
                                                  style="width: 100%;overflow-y: scroll;height: 200px;resize: none;"></textarea>
                            </div>
                            <div className="modal-footer">
                                <button type="submit" className="btn btn-default">Report
                                </button>
                                <button type="button" className="btn btn-default"
                                        data-dismiss="modal">
                                    Cancel
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default PanePostReportModal;