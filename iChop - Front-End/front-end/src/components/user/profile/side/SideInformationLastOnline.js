import React,{Component} from 'react';
import dateFormat from "dateformat";

class SideInformationLastOnline extends Component {

    render() {
        let lastOnline = new Date();

        return (
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
        );
    }
}

export default SideInformationLastOnline;