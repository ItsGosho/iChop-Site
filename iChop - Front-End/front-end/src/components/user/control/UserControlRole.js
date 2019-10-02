import React, {Component} from 'react';
import Roles from "../../../constants/roles.constants";
import dateFormat from 'dateformat';

class UserControlRole extends Component {


    render() {
        let previousRole = null;
        let role = Roles.USER;
        let nextRole = Roles.MODERATOR;

        let logs = [
            {happenedOn: dateFormat(Date.now(), "dd mmmm,yyyy HH:mm"), message: ' Changed the role to X'},
            {happenedOn: dateFormat(Date.now(), "dd mmmm,yyyy HH:mm"), message: ' Changed the role to X'},
        ];

        return (
            <div>
                <div className="row">
                    <div className="col-md-auto">
                        <span style={{'fontFamily': 'Consolas'}}>Current Role: <b>{role}</b></span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">

                        {
                            (() => {
                                if (role !== Roles.OWNER) {

                                    if (previousRole !== null) {
                                        return (
                                            <button type="button" className="btn btn-warning btn-sm" style={{'marginRight':'10px'}}>
                                                <span>👇🏻</span><span>{previousRole}</span>
                                            </button>
                                        );
                                    }
                                }

                                return null;
                            })()
                        }

                        {
                            (() => {
                                if (role !== Roles.OWNER) {

                                    if (nextRole !== null && nextRole !== Roles.OWNER) {
                                        return (
                                            <button type="button" className="btn btn-warning btn-sm" style={{'marginRight':'10px'}}>
                                                <span>👆🏻</span>
                                                <span>{nextRole}</span>
                                            </button>
                                        );
                                    }
                                }

                                return null;
                            })()
                        }
                    </div>
                </div>
                <div className="row">
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
                </div>
            </div>
        );
    }

}

export default UserControlRole;