import React, {Component, Fragment} from 'react';
import ReportTableColumns from "./other/ReportTableColumns";
import ReportTable from "./other/ReportTable";
import PaginationNav from "../other/PaginationNav";
import RoutingURLs from "../../constants/routing.constants";

class ReportsComment extends Component {

    constructor(props) {
        super(props);

        this.onDeleteEntity = this.onDeleteEntity.bind(this);
        this.onDeleteReport = this.onDeleteReport.bind(this);
    }

    onDeleteEntity() {
        console.log('Delete Entity');
    }

    onDeleteReport() {
        console.log('Delete Report');
    }

    render() {
        let reports = [
            {
                commentId: 'id1',
                reason: 'Really bad comment!',
                content: 'Hi there1!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2013, 1)
            },
            {
                commentId: 'id2',
                reason: 'Whoop ugly!',
                content: 'Hi there2!',
                creatorUsername: 'penka123',
                reportDate: new Date(2017, 9)
            },
            {
                commentId: 'id3',
                reason: 'Meh!',
                content: 'Hi there3!',
                creatorUsername: 'roki49',
                reportDate: new Date(2015, 12)
            },
        ];

        return (
            <Fragment>
                <ReportTable>
                    {
                        (() => reports.map((report, index) => {
                            let {content, reason, creatorUsername, reportDate} = report;

                            return (
                                <ReportTableColumns
                                    onDeleteEntity={this.onDeleteEntity}
                                    onDeleteReport={this.onDeleteReport}
                                    entityName={'Comment'}
                                    index={index}
                                    reason={reason}
                                    creatorUsername={creatorUsername}
                                    reportDate={reportDate}>

                                    <td width="300px">
                                        <div style={{
                                            'overflow': 'scroll',
                                            'width': '100%',
                                            'maxHeight': '100px'
                                        }}>{content}</div>
                                    </td>

                                </ReportTableColumns>
                            );
                        }))()
                    }
                </ReportTable>
                <PaginationNav totalResults={reports.length} resultsPerPage={1} redirectPage={RoutingURLs.COMMENT.REPORT.ALL}/>
            </Fragment>
        );
    }

}

export default ReportsComment;