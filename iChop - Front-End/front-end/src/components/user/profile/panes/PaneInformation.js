import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat'

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
                                                        <span
                                                            style={{
                                                                'fontFamily': 'Consolas',
                                                                'fontSize': '20px'
                                                            }}>
                                                            <small>🎂</small>Birthday:
                                                            <span
                                                                style={{'fontFamily': 'Tahoma'}}>
                                                                {dateFormat(birthday, 'dd/MM/yyyy')}
                                                            </span>
                                                        </span>
                                                    );
                                                }
                                            })()
                                        }

                                        <div className="dropdown-divider"/>

                                        {
                                            (() => {
                                                if (aboutYou != null) {
                                                    return (
                                                        <Fragment>
                                                    <span style={{'fontFamily': 'Consolas', 'fontSize': '20px'}}>
                                                    <small>🖊️</small>About:</span>

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