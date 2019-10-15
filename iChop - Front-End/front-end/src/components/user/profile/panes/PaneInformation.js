import React, {Component} from 'react';
import dateFormat from 'dateformat'
import PanelInformationBirthday from "./information/PanelInformationBirthday";
import PanelInformationAboutYou from "./information/PanelInformationAboutYou";

class PaneInformation extends Component {

    render() {
        let information = {
            birthday: dateFormat(Date.now()),
            aboutYou: ''
        };

        let {birthday, aboutYou} = information;

        return (
            <div className="row">
                <div className="w-100" style={{'marginTop': '10px'}}>

                    {birthday != null ? (<PanelInformationBirthday/>) : null}

                    <div className="dropdown-divider"/>

                    {aboutYou != null ? (<PanelInformationAboutYou/>) : null}

                    {aboutYou == null && birthday == null ? (<span>User has not any information!</span>) : null}
                </div>
            </div>
        )
    }
}


export default PaneInformation;