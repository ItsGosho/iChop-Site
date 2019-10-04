import React, {Component, Fragment} from 'react';
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
            <Fragment>
                {
                    (() => {
                        if (information != null) {
                            return (
                                <div className="row">
                                    <div className="w-100" style={{'marginTop': '10px'}}>
                                        {
                                            (() => {
                                                if (birthday != null) {
                                                    return (
                                                        <PanelInformationBirthday/>
                                                    );
                                                }
                                            })()
                                        }

                                        <div className="dropdown-divider"/>

                                        {
                                            (() => {
                                                if (aboutYou != null) {
                                                    return (
                                                       <PanelInformationAboutYou/>
                                                    );
                                                }
                                            })()
                                        }

                                        <div className="dropdown-divider"/>
                                    </div>
                                </div>
                            )
                        } else {
                            return (<span>User has not any information!</span>);
                        }
                    })()
                }
            </Fragment>
        )
    }
}


export default PaneInformation;