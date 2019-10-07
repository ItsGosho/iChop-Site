import React from "react";
import CreateReactClass from 'create-react-class';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

var ReportTableWrapper = CreateReactClass({
    render() {
        let entityName = this.props.entityName;
        let thFirstName = this.props.thFirstName;
        let onDeleteEntity = this.props.onDeleteEntity;
        let onDeleteReport = this.props.onDeleteReport;

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
                                        <a className="dropdown-item" href="#">
                                            <button type="button" style="all: initial">❌Delete Comment</button>
                                        </a>
                                        <a className="dropdown-item" href="#">
                                            <button type="button" style="all: initial">😖Delete Report</button>
                                        </a>
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