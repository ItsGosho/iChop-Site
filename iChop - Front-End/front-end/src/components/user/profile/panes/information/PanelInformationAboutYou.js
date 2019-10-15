import React, {Component, Fragment} from 'react';
import './PanelInformationAboutYou.css'

class PanelInformationAboutYou extends Component {

    render() {
        let aboutYou = 'Coding!';

        return (
            <Fragment>
                <span className="about-you-info-title">
                    <small>🖊️</small>About:
                </span>
                <textarea readOnly className="about-you-info-content">{aboutYou}</textarea>
            </Fragment>
        );
    }
}

export default PanelInformationAboutYou;