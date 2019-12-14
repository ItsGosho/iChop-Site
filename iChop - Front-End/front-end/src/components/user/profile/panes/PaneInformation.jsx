import React from 'react';
import PanelInformationBirthdate from "./information/PanelInformationBirthdate";
import PanelInformationAboutYou from "./information/PanelInformationAboutYou";
import './PaneInformation.css';
import withState from "../../../../hocs/with.state";


const PaneInformation = (props) => {
    let {birthDate, aboutYou} = props.userProfileInfo;

    return (
        <div className="row">
            <div className="w-100 top-10px">

                {birthDate != null ? (<PanelInformationBirthdate birthDate={birthDate}/>) : null}

                <div className="dropdown-divider"/>

                {aboutYou != null ? (<PanelInformationAboutYou aboutYou={aboutYou}/>) : null}

                {aboutYou == null && birthDate == null ? (<center><span>User has not set any information!</span></center>) : null}
            </div>
        </div>
    )
};


export default withState(PaneInformation);