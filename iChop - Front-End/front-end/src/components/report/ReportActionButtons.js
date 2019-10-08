import React, {Component} from 'react';
import Button from "react-bootstrap/Button";
import {Dropdown} from "react-bootstrap";

class ReportActionButtons extends Component {

    render() {
        let entityName = this.props.entityName;

        let {onDeleteEntity,onDeleteReport} = this.props;

        return (
            <div className="row">
                <Dropdown>

                    <Dropdown.Toggle variant={'warning'} size={'sm'}>⚙Take Action!</Dropdown.Toggle>

                    <Dropdown.Menu>
                        <Dropdown.Item onClick={onDeleteEntity}>❌Delete {entityName}</Dropdown.Item>
                        <Dropdown.Item onClick={onDeleteReport}>😖Delete Report</Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown>
            </div>
        );
    }
}

export default ReportActionButtons;