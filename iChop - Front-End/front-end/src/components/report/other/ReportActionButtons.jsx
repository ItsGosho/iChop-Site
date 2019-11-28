import React from 'react';
import {Dropdown} from "react-bootstrap";
import styles from './ReportActionButtons.module.css'
import PropTypes from 'prop-types';

const ReportActionButtons = (props) => {
    let {onDeleteReport} = props;

    return (
        <Dropdown className={styles.report_buttons}>
            <Dropdown.Toggle variant={'warning'} size={'sm'}>⚙Take Action!</Dropdown.Toggle>

            <Dropdown.Menu>
                <Dropdown.Item onClick={onDeleteReport}>😖Delete Report</Dropdown.Item>
            </Dropdown.Menu>
        </Dropdown>
    );
};

export default ReportActionButtons;

ReportActionButtons.propTypes = {
    onDeleteReport: PropTypes.func,
};