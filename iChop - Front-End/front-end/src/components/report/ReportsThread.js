import React, {Component, Fragment} from 'react';
import ReportTableWrapper from "./ReportTableWrapper";
import ReportTableActionButtons from "./ReportTableActionButtons";
import RoutingURLs from "../../constants/routing.constants";
import {Link} from "react-router-dom";
import formatDate from 'dateformat'

class ReportsThread extends Component {

    render() {
        let reports = [];


        return (
            <ReportTableWrapper>

                {
                    (() => {
                        reports.map((report, index) => {
                            let {threadId, reason, creatorUsername, reportDate} = report;

                            let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId);
                            let creatorProfile = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);

                            return (
                                <Fragment>
                                    <th>
                                        <Link to={threadReadUrl}>Link</Link>
                                    </th>

                                    <td width="300px">
                                        <div style={{
                                            'overflow': 'scroll',
                                            'width': '100%',
                                            "maxHeight": '100px'
                                        }}>{reason}</div>
                                    </td>

                                    <td>
                                        <Link to={creatorProfile}>{creatorUsername}</Link>
                                    </td>

                                    <td>{formatDate(reportDate, 'dd MMM,yyyy')}</td>

                                    <ReportTableActionButtons entityName={'Thread'} onDeleteEntity={null}
                                                              onDeleteReport={null}/>
                                </Fragment>
                            );
                        })
                    })()
                }

            </ReportTableWrapper>
        );
    }
}

export default ReportsThread;