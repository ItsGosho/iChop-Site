import React, {Component} from 'react';
import Roles from "../../../../constants/roles.constants";
import UserControlRoleLogs from "./UserControlRoleLogs";
import './UserControlRole.css'

class UserControlRole extends Component {

    constructor(props) {
        super(props);

        this.state = {
            previousRole: null,
            role: Roles.USER,
            nextRole: Roles.MODERATOR,
        };

        this.isDownAvailable = this.isDownAvailable.bind(this);
        this.isUpAvailable = this.isUpAvailable.bind(this);

        this.onRoleDown = this.onRoleDown.bind(this);
        this.onRoleUp = this.onRoleUp.bind(this);
    }


    isDownAvailable() {
        let {previousRole, role} = this.state;

        return role !== Roles.OWNER && previousRole !== null;
    }

    isUpAvailable() {
        let {role, nextRole} = this.state;

        return role !== Roles.OWNER && nextRole !== null && nextRole !== Roles.OWNER;
    }

    onRoleDown() {
        console.log('Role down!');
    }

    onRoleUp() {
        console.log('Role up!');
    }

    render() {
        let {role, previousRole, nextRole} = this.state;

        return (
            <div>
                <div className="row">
                    <div className="col-md-auto">
                        <span className="current-role">Current Role: <b>{role}</b></span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">

                        {this.isDownAvailable() ? (
                            <ChangeRoleButton icon={'👇🏻'}
                                              role={previousRole}
                                              onClick={this.onRoleDown}/>) : null}

                        {this.isUpAvailable() ? (
                            <ChangeRoleButton icon={'👆🏻'}
                                              role={nextRole}
                                              onClick={this.onRoleUp}/>) : null}
                    </div>
                </div>

                <div className="row">
                    <UserControlRoleLogs/>
                </div>
            </div>
        );
    }

}

const ChangeRoleButton = (props) => {
    let {icon, role, onClick} = props;

    return (
        <button className="btn btn-warning btn-sm change-role-button" onClick={onClick}>
            <span>{icon}</span>
            <span>{role}</span>
        </button>
    )
};

export default UserControlRole;