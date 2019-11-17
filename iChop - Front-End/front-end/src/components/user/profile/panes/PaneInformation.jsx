import React, {Component} from 'react';
import dateFormat from 'dateformat'
import PanelInformationBirthdate from "./information/PanelInformationBirthdate";
import PanelInformationAboutYou from "./information/PanelInformationAboutYou";
import './PaneInformation.css';
import withState from "../../../../hocs/with.state";

class PaneInformation extends Component {

    render() {
        let {birthDate, aboutYou} = this.props.userProfileInfo;

        return (
            <div className="row">
                <div className="w-100 top-10px">

                    {birthDate != null ? (<PanelInformationBirthdate birthDate={birthDate}/>) : null}

                    <div className="dropdown-divider"/>

                    {aboutYou != null ? (<PanelInformationAboutYou aboutYou={aboutYou}/>) : null}

                    {aboutYou == null && birthDate == null ? (<span>User has not set any information!</span>) : null}
                </div>
            </div>
        )
    }
}


export default withState(PaneInformation);