import React from 'react';
import dateFormat from "dateformat";
import './PanelInformationBirthday.css'
import PropTypes from "prop-types";

const BIRTHDATE_DATE_PATTERN = 'dd/mmm/yyyy';

const PanelInformationBirthdate = ({birthDate}) => (
    <span className="birthday-title">
        <small>🎂</small>Birthdate:
        <span className="birthday-content">{dateFormat(birthDate, BIRTHDATE_DATE_PATTERN)}</span>
    </span>
);

export default PanelInformationBirthdate;

PanelInformationBirthdate.propTypes = {
    birthDate: PropTypes.instanceOf(Date)
};