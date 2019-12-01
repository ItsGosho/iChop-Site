import React from "react";
import Table from "react-bootstrap/Table";

const ReportTable = (props) => (
    <div className="table-responsive" align="center">
        <Table responsive style={{'width': '85%', 'textAlign': 'center'}}>

            <thead>
            <tr>
                <th scope="col">Reason</th>
                <th scope="col">Creator</th>
                <th scope="col">Entity Type</th>
                <th scope="col">Date</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>

            <tbody>
            {props.children}
            </tbody>

        </Table>
    </div>
);


export default ReportTable;