import React, {Component, Fragment} from 'react';

class PanelInformationAboutYou extends Component {

    render() {
        let aboutYou = '';

        return (
            <Fragment>
                <span style={{'fontFamily': 'Consolas', 'fontSize': '20px'}}>
                    <small>🖊️</small>About:
                </span>
                <textarea readOnly name="content"
                          style={{
                              'border': '0px solid #ccc',
                              'borderRadius': '3px',
                              'width': '100%',
                              'resize': 'none',
                              'overflow': 'hidden',
                              'fontFamily': 'Consolas'
                          }}>{aboutYou}</textarea>
            </Fragment>
        );
    }
}

export default PanelInformationAboutYou;