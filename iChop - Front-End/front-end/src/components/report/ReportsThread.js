import React, {Component, Fragment} from 'react';
import ReportTable from "./other/ReportTable";
import RoutingURLs from "../../constants/routing/routing.constants";
import ReportTableColumns from "./other/ReportTableColumns";
import {Link} from "react-router-dom";
import PaginationNav from "../other/PaginationNav";


class ReportsThread extends Component {

    constructor(props) {
        super(props);

        this.state = {
            reports: []
        };

        this.onDeleteEntity = this.onDeleteEntity.bind(this);
        this.onDeleteReport = this.onDeleteReport.bind(this);
        this.iterateReports = this.iterateReports.bind(this);
    }

    componentDidMount() {
        let reports = [
            {
                threadId: 'id1',
                reason: 'Really bad thread!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2018, 1)
            }
        ];

        this.setState({reports});
    }

    iterateReports() {
        return this.state.reports.map((report, index) => {
            let {threadId, reason, creatorUsername, reportDate} = report;

            let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId);

            return (
                <ReportTableColumns onDeleteEntity={this.onDeleteEntity}
                                    onDeleteReport={this.onDeleteReport}
                                    entityName={'Thread'}
                                    index={index}
                                    reason={reason}
                                    creatorUsername={creatorUsername}
                                    reportDate={reportDate}>
                    <th>
                        <Link to={threadReadUrl}>Link</Link>
                    </th>
                </ReportTableColumns>
            );
        })
    }

    onDeleteEntity() {
        console.log('Delete Entity');
    }

    onDeleteReport() {
        console.log('Delete Report');
    }

    render() {
        let {reports} = this.state;


        return (
            <Fragment>
                <ReportTable>
                    {this.iterateReports()}
                </ReportTable>

                <PaginationNav totalResults={reports.length}
                               resultsPerPage={1}
                               redirectPage={RoutingURLs.THREAD.REPORT.ALL}/>
            </Fragment>
        );
    }
}

export default ReportsThread;