import React, {Component} from 'react';
import dateFormat from "dateformat";
import './PanelInformationBirthday.css'

class PanelInformationBirthday extends Component {

    render() {
        let formatPattern = 'dd/MM/yyyy';
        let birthday = dateFormat(Date.now(), formatPattern);

        return (
            <span className="birthday-title">
                <small>🎂</small>Birthday:
                <span className="birthday-content">{birthday}</span>
            </span>
        );
    }
}

export default PanelInformationBirthday;