import React,{Component} from 'react';
import PanePostReportModal from "./PanePostReportModal";

class PanePostActions extends Component {

    render() {

        return (
            <div className="col-md-12">
                        <span style="color: #7f7f7f;font-size: 10px"
                              th:text="*{#temporals.format(createdOn, 'MMM dd,yyyy')}">Jan 00,0000</span>

                <th:block th:if="${@postServicesImp.findById(post.id).creator.username.equals(#authentication.principal.username)
                                                or @postServicesImp.findById(post.id).user.username.equals(#authentication.principal.username)}
                                                or ${#authorization.expression('hasAuthority(''MODERATOR'')')}">
                    <form method="post" th:action="@{/post/{postId}/delete(postId=*{id})}"
                          style="all: initial">
                        <button type="submit" style="all:initial;cursor: pointer;"><span
                            style="font-size: 10px;color: #007bff">❌Delete</span></button>
                    </form>
                </th:block>

                <button type="button" th:id="'button-report_post-'+*{id}"
                        style="all:initial;cursor: pointer;">
                    <span style="font-size: 10px;color: #007bff">🎌Report</span></button>
                <PanePostReportModal/>
            </div>
        );
    }
}

export default PanePostActions;