import React, {Component} from 'react';
import dateFormat from "dateformat";

class PanelInformationBirthday extends Component {

    render() {
        let birthday = dateFormat(Date.now());

        return (
            <span
                style={{
                    'fontFamily': 'Consolas',
                    'fontSize': '20px'
                }}>
                <small>🎂</small>Birthday:
                <span
                    style={{'fontFamily': 'Tahoma'}}>
                    {dateFormat(birthday, 'dd/MM/yyyy')}
                </span>
            </span>
        );
    }
}

export default PanelInformationBirthday;