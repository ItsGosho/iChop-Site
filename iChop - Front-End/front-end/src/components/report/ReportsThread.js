import React, {Component} from 'react';
import ReportTableWrapper from "./ReportTableWrapper";
import RoutingURLs from "../../constants/routing.constants";
import ReportTableColumnsWrapper from "./ReportTableColumnsWrapper";
import {Link} from "react-router-dom";

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
                        let {threadId, reason, creatorUsername, reportDate} = report;

                        let threadReadUrl = RoutingURLs.THREAD.VIEW.replace(':id', threadId);

                        return (
                           <ReportTableColumnsWrapper index={index} reason={reason} creatorUsername={creatorUsername} reportDate={reportDate}>
                               <th>
                                   <Link to={threadReadUrl}>Link</Link>
                               </th>
                           </ReportTableColumnsWrapper>
                        );
                    }))()
                }

            </ReportTableWrapper>
        );
    }
}

export default ReportsThread;