import React, {Component} from 'react';
import UniversalPasswordsInputs from "../../../other/UniversalPasswordsInputs";
import NotificationHelper from "../../../../helpers/notification.helper";
import UserServices from "../../../../services/user.services";

class UserOptionsChangePassword extends Component {

    constructor(props) {
        super(props);

        this.onResetClick = this.onResetClick.bind(this);
    }

    onResetClick() {
        let {password, confirmPassword} = this.state;

        if (password !== confirmPassword) {
            NotificationHelper.showErrorNotification('Passwords are not equal!');
            return;
        }

        UserServices.changePassword(password,confirmPassword);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <form className="px-4 py-3">

                <UniversalPasswordsInputs onChange={onChange}/>

                <div align="center">

                    <button type="button"
                            data-style="zoom-in"
                            className="btn btn-primary btn-ladda"
                            onClick={this.onResetClick}>
                        Reset
                    </button>

                    <div className="dropdown-divider"/>
                </div>

            </form>
        );
    }

}

export default UserOptionsChangePassword;