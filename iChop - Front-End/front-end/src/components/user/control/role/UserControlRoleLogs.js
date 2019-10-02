import React, {Component, Fragment} from 'react';
import dateFormat from "dateformat";

class UserControlRoleLogs extends Component {


    render() {
        let logs = [
            {happenedOn: dateFormat(Date.now(), 'dd mmmm,yyyy HH:mm'), message: ' Changed the role to X'},
            {happenedOn: dateFormat(Date.now(), 'dd mmmm,yyyy HH:mm'), message: ' Changed the role to X'},
        ];

        return (
            <Fragment>
                <div className="col-md-auto">
                    <small><b>Logs:</b></small>
                </div>
                <div className="col-md-auto">
                    <ul>

                        {
                            logs.map((item, index) => {
                                return (
                                    <li key={index}>
                                        <small>
                                            <b>[{item.happenedOn}]</b>
                                            <span>{item.message}</span>.
                                        </small>
                                    </li>
                                );
                            })
                        }
                    </ul>
                </div>
            </Fragment>
        );
    }

}

export default UserControlRoleLogs;