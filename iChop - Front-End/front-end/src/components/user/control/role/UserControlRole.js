import React, {Component} from 'react';
import Roles from "../../../../constants/roles.constants";
import UserControlRoleLogs from "./UserControlRoleLogs";

class UserControlRole extends Component {


    render() {
        let {username} = this.props;

        let previousRole = null;
        let role = Roles.USER;
        let nextRole = Roles.MODERATOR;

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
                                            <button type="button" className="btn btn-warning btn-sm"
                                                    style={{'marginRight': '10px'}}>
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
                                            <button type="button" className="btn btn-warning btn-sm"
                                                    style={{'marginRight': '10px'}}>
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
                    <UserControlRoleLogs/>
                </div>
            </div>
        );
    }

}

export default UserControlRole;