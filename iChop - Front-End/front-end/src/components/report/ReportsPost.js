import React, {Component, Fragment} from 'react';
import ReportTable from "./other/ReportTable";
import ReportTableColumns from "./other/ReportTableColumns";
import PaginationNav from "../other/PaginationNav";
import RoutingURLs from "../../constants/routing.constants";

class ReportsPost extends Component {

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
                postId: 'id1',
                reason: 'Really bad post!',
                content: 'Hi there1!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(1993, 1)
            },
            {
                postId: 'id2',
                reason: 'Whoop ugly!',
                content: 'Hi there2!',
                creatorUsername: 'penka123',
                reportDate: new Date(2007, 8)
            },
            {
                postId: 'id3',
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
                                    entityName={'Post'}
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

                <PaginationNav totalResults={reports.length} resultsPerPage={1} redirectPage={RoutingURLs.POST.REPORT.ALL}/>
            </Fragment>
        );
    }

}

export default ReportsPost;