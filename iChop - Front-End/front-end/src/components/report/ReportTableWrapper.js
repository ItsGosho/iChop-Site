import React from "react";
import CreateReactClass from 'create-react-class';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

var ReportTableWrapper = CreateReactClass({
    render() {
        let thFirstName = this.props.thFirstName;

        return (
            <div className="table-responsive" align="center">
                <table className="table" style={{'width': '85%', 'textAlign': 'center'}}>
                    <thead>
                    <tr>
                        <th scope="col">{thFirstName}</th>
                        <th scope="col">Reason</th>
                        <th scope="col">Reporter</th>
                        <th scope="col">Report Date</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr>

                        {this.props.children}

                        <td>
                            <div className="row">
                                <div className="dropdown">
                                    <button className="btn btn-warning btn-sm dropdown-toggle" type="button"
                                            id="dropdownMenuButton"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ⚙Take Action!
                                    </button>
                                    <div className="dropdown-menu">
                                        <form method="post"
                                              th:action="@{/comment/{commentId}/delete(commentId=*{commentId})}"
                                              style="all:initial">
                                            <a className="dropdown-item" href="#">
                                                <button type="submit" style="all: initial">❌Delete Comment</button>
                                            </a>
                                        </form>
                                        <form method="post"
                                              th:action="@{/comment/report/{reportId}/delete(reportId=*{reportId})}"
                                              style="all:initial">
                                            <a className="dropdown-item" href="#">
                                                <button type="submit" style="all: initial">😖Delete Report</button>
                                            </a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        );
    }
});


export default ReportTableWrapper;