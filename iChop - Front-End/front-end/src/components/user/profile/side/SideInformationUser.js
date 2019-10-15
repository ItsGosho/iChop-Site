import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";
import './SideInformationUser.css'

class SideInformationUser extends Component {


    render() {
        let lastOnline = new Date();
        let registrationDate = new Date();
        let totalMessages = 32;

        return (
            <Fragment>

                <div className="col-md-auto">
                    <div className="row user-base-info-row">
                        <div className="col-md-auto user-base-info-last-online-title">
                            Last online:
                        </div>
                        <div className="col-md-auto user-base-info-last-online-content">
                                <span title={dateFormat(lastOnline, 'HH:mm')}>
                                    {dateFormat(lastOnline, 'MMM dd,yyyy')}
                                </span>
                        </div>
                    </div>
                </div>

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

                <div className="col-md-auto">
                    <div className="row" style={{'fontSize': '11px'}}>
                        <div className="col-md-auto" style={{'columnWidth': '75px'}}>
                            Messages:
                        </div>
                        <div className="col-md-auto" style={{'columnWidth': '100px'}}>
                               <span style={{'display': 'inlineBlock','float': 'right'}}>
                                   {totalMessages}
                               </span>
                        </div>
                    </div>
                </div>

            </Fragment>
        );
    }

}

export default SideInformationUser;