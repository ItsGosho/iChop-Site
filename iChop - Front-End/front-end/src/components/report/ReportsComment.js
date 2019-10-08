import React,{Component} from 'react';
import ReportTableColumnsWrapper from "./wrappers/ReportTableColumnsWrapper";
import ReportTableWrapper from "./wrappers/ReportTableWrapper";

class ReportsComment extends Component {


    render() {
        let reports = [
            {
                commentId: 'id1',
                reason: 'Really bad comment!',
                content: 'Hi there1!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(2013,1)
            },
            {
                commentId: 'id2',
                reason: 'Whoop ugly!',
                content: 'Hi there2!',
                creatorUsername: 'penka123',
                reportDate: new Date(2017,9)
            },
            {
                commentId: 'id3',
                reason: 'Meh!',
                content: 'Hi there3!',
                creatorUsername: 'roki49',
                reportDate: new Date(2015,12)
            },
        ];

        return (
            <ReportTableWrapper>
                {
                    (() => reports.map((report, index) => {
                        let {content, reason, creatorUsername, reportDate} = report;

                        return (
                            <ReportTableColumnsWrapper entityName={'Comment'} index={index} reason={reason} creatorUsername={creatorUsername} reportDate={reportDate}>
                                <td width="300px">
                                    <div style={{'overflow': 'scroll','width': '100%','maxHeight': '100px'}}>{content}</div>
                                </td>
                            </ReportTableColumnsWrapper>
                        );
                    }))()
                }
            </ReportTableWrapper>
        );
    }

}

export default ReportsComment;