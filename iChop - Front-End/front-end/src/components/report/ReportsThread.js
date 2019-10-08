import React, {Component, Fragment} from 'react';
import ReportTableWrapper from "./wrappers/ReportTableWrapper";
import RoutingURLs from "../../constants/routing.constants";
import ReportTableColumnsWrapper from "./wrappers/ReportTableColumnsWrapper";
import {Link} from "react-router-dom";
import ReactPaginate from 'react-paginate';

class ReportsThread extends Component {

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
                threadId: 'id1',
                reason: 'Really bad thread!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2018, 1)
            },
            {
                threadId: 'id2',
                reason: 'Whoop ugly!',
                creatorUsername: 'penka123',
                reportDate: new Date(2018, 1)
            },
            {
                threadId: 'id3',
                reason: 'Meh!',
                creatorUsername: 'roki49',
                reportDate: new Date(2018, 1)
            },
        ];


        return (
            <Fragment>
                <ReportTableWrapper>
                    {
                        (() => reports.map((report, index) => {
                            let {threadId, reason, creatorUsername, reportDate} = report;

                            let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId);

                            return (
                                <ReportTableColumnsWrapper onDeleteEntity={this.onDeleteEntity}
                                                           onDeleteReport={this.onDeleteReport}
                                                           entityName={'Thread'}
                                                           index={index}
                                                           reason={reason}
                                                           creatorUsername={creatorUsername}
                                                           reportDate={reportDate}>
                                    <th>
                                        <Link to={threadReadUrl}>Link</Link>
                                    </th>
                                </ReportTableColumnsWrapper>
                            );
                        }))()
                    }
                </ReportTableWrapper>
                <ReactPaginate/>
            </Fragment>
        );
    }
}

export default ReportsThread;