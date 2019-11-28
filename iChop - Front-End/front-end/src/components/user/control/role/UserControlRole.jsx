import React, {Component} from 'react';
import './UserControlRole.css'
import PropTypes from 'prop-types'
import UserServices from "../../../../services/user.services";

class UserControlRole extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            authority: '',
            hasNext: false,
            hasPrevious: false,
            nextRole: '',
            previousRole: '',
        };

        this.onRoleDown = this.onRoleDown.bind(this);
        this.onRoleUp = this.onRoleUp.bind(this);
        this.fetchData = this.fetchData.bind(this);
    }

    async fetchData() {
        let {username} = this.props;
        let {authority} = await UserServices.adminFindByUsername(username);

        let hasNext = await UserServices.hasNextRole(username);
        let hasPrevious = await UserServices.hasPreviousRole(username);
        let nextRole = await UserServices.nextRole(username);
        let previousRole = await UserServices.previousRole(username);


        this.setState({
            username,
            authority,
            hasNext,
            hasPrevious,
            nextRole,
            previousRole
        })
    }

    async componentDidMount() {
        this.fetchData();
    }

    async onRoleDown() {
        let {username} = this.state;
        await UserServices.roleDemote(username);
        this.fetchData();
    }

    async onRoleUp() {
        let {username} = this.state;
        await UserServices.rolePromote(username);
        this.fetchData();
    }

    render() {
        let {authority, hasNext, hasPrevious, nextRole, previousRole} = this.state;

        return (
            <div>
                <div className="row">
                    <div className="col-md-auto">
                        <span className="current-role">Current Role: <b>{authority}</b></span>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-auto">

                        {hasPrevious ? (
                            <ChangeRoleButton icon={'👇🏻'}
                                              role={previousRole}
                                              onClick={this.onRoleDown}/>) : null}

                        {hasNext ? (
                            <ChangeRoleButton icon={'👆🏻'}
                                              role={nextRole}
                                              onClick={this.onRoleUp}/>) : null}
                    </div>
                </div>

                <div className="row">
                  <small>Soon logs!</small>
                </div>
            </div>
        );
    }

}

export default UserControlRole;

const ChangeRoleButton = (props) => {
    let {icon, role, onClick} = props;

    return (
        <button className="btn btn-warning btn-sm change-role-button" onClick={onClick}>
            <span>{icon}</span>
            <span>{role}</span>
        </button>
    )
};

ChangeRoleButton.propTypes = {
    icon: PropTypes.string,
    role: PropTypes.string,
    onClick: PropTypes.func,
};