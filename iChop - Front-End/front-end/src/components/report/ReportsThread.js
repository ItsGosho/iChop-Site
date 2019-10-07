import React, {Component} from 'react';
import ReportTableWrapper from "./ReportTableWrapper";
import ReportTableActionButtons from "./ReportTableActionButtons";

class ReportsThread extends Component {

    render() {

        return (
            <ReportTableWrapper>
                <th className="user-tr-username"><a th:href="@{/thread/{threadId}/read(threadId=*{threadId})}">Link</a>
                </th>
                <td width="300px">
                    <div style="overflow: scroll;width: 100%;max-height: 100px" th:text="*{reason}"></div>
                </td>
                <td><a th:href="@{/user/{username}/profile(username=*{creatorUsername})}">ItsGosho</a></td>
                <td th:text="*{#temporals.format(reportDate, 'dd MMM,yyyy')}">12 April,2019</td>

                <ReportTableActionButtons entityName={'Thread'} onDeleteEntity={null} onDeleteReport={null}/>
                
            </ReportTableWrapper>
        );
    }
}

export default ReportsThread;