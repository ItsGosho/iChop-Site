import React, {Component} from 'react';
import {Dropdown} from "react-bootstrap";

class ReportActionButtons extends Component {

    render() {
        let entityName = this.props.entityName;

        let {onDeleteEntity, onDeleteReport} = this.props;

        return (
            <Dropdown>
                <Dropdown.Toggle variant={'warning'} size={'sm'}>⚙Take Action!</Dropdown.Toggle>

                <Dropdown.Menu>
                    <Dropdown.Item onClick={onDeleteEntity}>❌Delete {entityName}</Dropdown.Item>
                    <Dropdown.Item onClick={onDeleteReport}>😖Delete Report</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        );
    }
}

export default ReportActionButtons;