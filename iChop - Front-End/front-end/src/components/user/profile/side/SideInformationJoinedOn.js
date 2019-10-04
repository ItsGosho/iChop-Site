import React,{Component} from 'react';

class SideInformationJoinedOn extends Component {

    render() {

        return (
            <div className="col-md-auto">
                <div className="row user-base-info-registration_date-title">
                    <div className="col-md-auto">
                        Joined on:
                    </div>
                    <div className="col-md-auto" style="column-width: 100px">
                               <span style="display: inline-block;float: right;"
                                     th:text="*{#temporals.format(registrationDate, 'MMM dd,yyyy')}"
                                     th:title="*{#temporals.format(registrationDate, 'HH:mm')}">
                                    Unknown 00,0000
                               </span>
                    </div>
                </div>
            </div>
        );
    }
}

export default SideInformationJoinedOn;