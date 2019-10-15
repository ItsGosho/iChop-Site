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
                    <div className="row info-row">
                        <div className="col-md">
                            Last online:
                        </div>
                        <div className="col-md">
                                <span className="info-content"
                                    title={dateFormat(lastOnline, 'HH:mm')}>
                                    {dateFormat(lastOnline, 'dd mmm,yyyy')}
                                </span>
                        </div>
                    </div>
                </div>

                <div className="col-md-auto">
                    <div className="row info-row">
                        <div className="col-md">
                            Joined on:
                        </div>
                        <div className="col-md">
                               <span className="info-content"
                                     title={dateFormat(registrationDate, 'HH:mm')}>
                                   {dateFormat(registrationDate, 'dd mmm,yyyy')}
                               </span>
                        </div>
                    </div>
                </div>

                <div className="col-md-auto">
                    <div className="row info-row">
                        <div className="col-md">
                            Messages:
                        </div>
                        <div className="col-md">
                               <span className="info-content">
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