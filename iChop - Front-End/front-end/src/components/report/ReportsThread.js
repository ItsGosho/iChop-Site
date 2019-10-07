import React, {Component, Fragment} from 'react';
import ReportTableWrapper from "./ReportTableWrapper";
import ReportTableActionButtons from "./ReportTableActionButtons";
import RoutingURLs from "../../constants/routing.constants";
import {Link} from "react-router-dom";
import formatDate from 'dateformat'

class ReportsThread extends Component {

    render() {
        let reports = [
            {
                threadId: 'id1',
                reason: 'Really bad thread!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2018,1)
            },
            {
                threadId: 'id2',
                reason: 'Whoop ugly!',
                creatorUsername: 'penka123',
                reportDate: new Date(2018,1)
            },
            {
                threadId: 'id3',
                reason: 'Meh!',
                creatorUsername: 'roki49',
                reportDate: new Date(2018,1)
            },
        ];


        return (
            <ReportTableWrapper>

                {
                    (() => reports.map((report, index) => {
                        console.log(123);
                        let {threadId, reason, creatorUsername, reportDate} = report;

                        let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId);
                        let creatorProfile = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);

                        return (
                            <tr key={index}>
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

                                <td>{formatDate(reportDate, 'dd mmm,yyyy')}</td>

                                <ReportTableActionButtons entityName={'Thread'} onDeleteEntity={null}
                                                          onDeleteReport={null}/>
                            </tr>
                        );
                    }))()
                }

            </ReportTableWrapper>
        );
    }
}

export default ReportsThread;