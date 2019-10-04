import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat'

class PaneInformation extends Component {

    render() {
        let information = {
            birthday: dateFormat(Date.now()),
            aboutYou: ''
        };

        return (
            <Fragment>
                {
                    (() => {
                        if (information != null) {
                            return (
                                    <div className="row">
                                        <div className="w-100" style="margin-top: 10px">

        <span th:if="*{birthDate != null}"
              style="font-family: Consolas;font-size: 20px"><small>🎂</small>Birthday: <span
            style="font-family: Tahoma"
            th:text="*{#temporals.format(birthDate, 'dd/MM/yyyy')}"></span></span>

                                            <div className="dropdown-divider"></div>
                                            <th:block th:if="*{aboutYou != null}">

                                                <span style="font-family: Consolas;font-size: 20px"><small>🖊️</small>About:</span>

                                                <textarea readOnly name="content"
                                                          style="border:0px solid #ccc;border-radius: 3px;width: 100%;resize: none;overflow: hidden;font-family: Consolas"
                                                          th:text="*{aboutYou}"></textarea>

                                            </th:block>
                                            <div className="dropdown-divider"></div>
                                        </div>
                                    </div>
                            );
                        } else {
                            return (<span>User has not any information!</span>);
                        }
                    })()
                }
            </Fragment>
        );
    }
}

export default PaneInformation;