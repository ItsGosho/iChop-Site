import React from "react";
import CreateReactClass from 'create-react-class';
import Table from "react-bootstrap/Table";

let ReportTable = CreateReactClass({
    render() {
        let thFirstName = this.props.thFirstName;

        return (
            <div className="table-responsive" align="center">
                <Table responsive style={{'width': '85%', 'textAlign': 'center'}}>

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
                    {this.props.children}
                    </tbody>

                </Table>
            </div>
        );
    }
});


export default ReportTable;