import React, {Component} from 'react';
import dateFormat from "dateformat";
import './PanelInformationBirthday.css'
import PropTypes from "prop-types";

class PanelInformationBirthdate extends Component {

    render() {
        let {birthDate} = this.props;
        let formatPattern = 'dd/mmm/yyyy';


        return (
            <span className="birthday-title">
                <small>🎂</small>Birthdate:
                <span className="birthday-content">{dateFormat(birthDate, formatPattern)}</span>
            </span>
        );
    }
}

PanelInformationBirthdate.propTypes = {
    birthDate: PropTypes.object
};

export default PanelInformationBirthdate;