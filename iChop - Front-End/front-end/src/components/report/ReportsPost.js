import React,{Component} from 'react';
import ReportTableWrapper from "./wrappers/ReportTableWrapper";
import ReportTableColumnsWrapper from "./wrappers/ReportTableColumnsWrapper";

class ReportsPost extends Component {


    render() {
        let reports = [
            {
                postId: 'id1',
                reason: 'Really bad post!',
                content: 'Hi there1!',
                creatorUsername: 'itsgosho',
                reportDate: new Date(1993,1)
            },
            {
                postId: 'id2',
                reason: 'Whoop ugly!',
                content: 'Hi there2!',
                creatorUsername: 'penka123',
                reportDate: new Date(2007,8)
            },
            {
                postId: 'id3',
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
                            <ReportTableColumnsWrapper entityName={'Post'} index={index} reason={reason} creatorUsername={creatorUsername} reportDate={reportDate}>
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

export default ReportsPost;