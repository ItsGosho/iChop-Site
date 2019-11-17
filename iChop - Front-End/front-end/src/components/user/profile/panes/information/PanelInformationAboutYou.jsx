import React, {Component, Fragment} from 'react';
import './PanelInformationAboutYou.css'
import PropTypes from "prop-types";

class PanelInformationAboutYou extends Component {

    render() {
        let {aboutYou} = this.props;

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

PanelInformationAboutYou.propTypes = {
   aboutYou: PropTypes.string
};

export default PanelInformationAboutYou;