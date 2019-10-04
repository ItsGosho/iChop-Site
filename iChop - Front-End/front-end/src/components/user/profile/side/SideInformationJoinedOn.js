import React, {Component} from 'react';
import dateFormat from 'dateformat';

class SideInformationJoinedOn extends Component {

    render() {
        let registrationDate = new Date();

        return (
            <div className="col-md-auto">
                <div className="row user-base-info-registration_date-title">
                    <div className="col-md-auto">
                        Joined on:
                    </div>
                    <div className="col-md-auto" style={{'columnWidth': '100px'}}>
                               <span style={{'display': 'inlineBlock', 'float': 'right'}}
                                     title={dateFormat(registrationDate, 'HH:mm')}>
                                   {dateFormat(registrationDate, 'MMM dd,yyyy')}
                               </span>
                    </div>
                </div>
            </div>
        );
    }
}

export default SideInformationJoinedOn;