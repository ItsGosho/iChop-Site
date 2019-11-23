import React, {Component, Fragment} from 'react';
import ReportTableColumns from "./other/ReportTableColumns";
import ReportTable from "./other/ReportTable";
import PaginationNav from "../other/PaginationNav";
import RoutingURLs from "../../constants/routing/routing.constants";
import './ReportsComment.css'

class ReportsComment extends Component {


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
                commentId: 'id1',
                reason: 'Really bad comment!',
                content: 'Hi there1!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2013, 1)
            }
        ];

        this.setState({reports})
    }

    iterateReports() {
        return this.state.reports.map((report, index) => {
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

                    <td className="td-content">
                        <div className="div-content">{content}</div>
                    </td>

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
               {/* <PaginationNav totalResults={reports.length}
                               resultsPerPage={1}
                               redirectPage={RoutingURLs.COMMENT.REPORT.ALL}/>*/}
            </Fragment>
        );
    }

}

export default ReportsComment;