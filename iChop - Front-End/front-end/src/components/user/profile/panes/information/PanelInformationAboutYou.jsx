import React, {Fragment} from 'react';
import './PanelInformationAboutYou.css'
import PropTypes from "prop-types";


const PanelInformationAboutYou = ({aboutYou}) => (
    <Fragment>
        <span className="about-you-info-title">
            <small>🖊️</small>About:
        </span>
        <textarea readOnly className="about-you-info-content">{aboutYou}</textarea>
    </Fragment>
);

export default PanelInformationAboutYou;


PanelInformationAboutYou.propTypes = {
    aboutYou: PropTypes.string
};