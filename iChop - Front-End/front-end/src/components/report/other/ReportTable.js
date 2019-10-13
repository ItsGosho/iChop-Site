import React from "react";
import CreateReactClass from 'create-react-class';
import Table from "react-bootstrap/Table";
import styles from './ReportTable.module.css'

let ReportTable = CreateReactClass({
    render() {
        let thFirstName = this.props.thFirstName;

        return (
            <div className="table-responsive" align="center">
                <Table responsive className={styles.report_table}>

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