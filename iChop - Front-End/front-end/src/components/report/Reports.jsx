import React, {Component, Fragment} from 'react';
import ReportNav from "./other/ReportNav";
import ReportTableColumns from "./other/ReportTableColumns";
import ReportTable from "./other/ReportTable";
import './Reports.css';
import ReportServices from "../../services/report.services";
import {withRouter} from "react-router-dom";
import QueryHelper from "../../helpers/query.helper";

class Reports extends Component {

    constructor(props) {
        super(props);

        this.state = {
            reports: []
        };

        this.onDeleteReport = this.onDeleteReport.bind(this);
        this.iterateReports = this.iterateReports.bind(this);
        this.fetchReports = this.fetchReports.bind(this);
        this.queryHelper = new QueryHelper(this.props);
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        this.queryHelper = new QueryHelper(this.props);
    }

    async componentDidMount() {
        this.queryHelper.addQueryParameter('name', 'itsgosho');
        this.queryHelper.addQueryParameter('age', 17);
        await this.fetchReports();
    }

    async fetchReports() {
        let reports = await ReportServices.findBy();
        this.setState({reports});
    }

    iterateReports() {
        return this.state.reports.map((report, index) => {
            let {id, reason, creatorUsername, reportedOn, type} = report;

            return (
                <ReportTableColumns
                    id={id}
                    index={index}
                    creatorUsername={creatorUsername}
                    reason={reason}
                    type={type}
                    reportedOn={reportedOn}
                    onDeleteReport={this.onDeleteReport}>
                </ReportTableColumns>
            );
        })
    }

    async onDeleteReport(id, type) {
        let isSuccessful = await ReportServices.deleteById(id, type);

        console.log(isSuccessful);
        if (isSuccessful) {
            this.fetchReports();
        }
    }

    render() {
        return (
            <Fragment>
                <ReportNav/>

                <ReportTable>
                    {this.iterateReports()}
                </ReportTable>

                {/* <PaginationNav totalResults={reports.length}
                               resultsPerPage={1}
                               redirectPage={RoutingURLs.POST.REPORT.ALL}/>*/}
            </Fragment>
        );
    }

}

export default withRouter(Reports);