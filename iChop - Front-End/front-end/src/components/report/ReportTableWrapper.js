import React from "react";
import CreateReactClass from 'create-react-class';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

var ReportTableWrapper = CreateReactClass({
    render() {
        return (
            <div class="table-responsive" align="center">
                <table class="table" style="width: 85%;text-align: center">
                    <thead>
                    <tr>
                        <th scope="col">Comment Content</th>
                        <th scope="col">Reason</th>
                        <th scope="col">Reporter</th>
                        <th scope="col">Report Date</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <th:block th:each="report : ${reports}" th:object="${report}">

                        <tr class="user-tr">
                            <td width="300px">
                                <div style="overflow: scroll;width: 100%;max-height: 100px" th:text="*{commentContent}"></div>
                            </td>
                            <td width="300px">
                                <div style="overflow: scroll;width: 100%;max-height: 100px" th:text="*{reason}"></div>
                            </td>
                            <td><a th:href="@{/user/{username}/profile(username=*{creatorUsername})}">ItsGosho</a></td>
                            <td th:text="*{#temporals.format(reportDate, 'dd MMM,yyyy')}">12 April,2019</td>
                            <td>
                                <div class="row">
                                    <div class="dropdown">
                                        <button class="btn btn-warning btn-sm dropdown-toggle" type="button" id="dropdownMenuButton"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ⚙Take Action!
                                        </button>
                                        <div class="dropdown-menu">
                                            <form method="post" th:action="@{/comment/{commentId}/delete(commentId=*{commentId})}"
                                                  style="all:initial">
                                                <a class="dropdown-item" href="#">
                                                    <button type="submit" style="all: initial">❌Delete Comment</button>
                                                </a>
                                            </form>
                                            <form method="post"
                                                  th:action="@{/comment/report/{reportId}/delete(reportId=*{reportId})}"
                                                  style="all:initial">
                                                <a class="dropdown-item" href="#">
                                                    <button type="submit" style="all: initial">😖Delete Report</button>
                                                </a>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </th:block>
                    </tbody>
                </table>
            </div>
        );
    }
});


export default ReportTableWrapper;