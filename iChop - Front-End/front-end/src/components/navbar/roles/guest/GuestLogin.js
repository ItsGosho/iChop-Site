import React, {Component, Fragment} from 'react';
import FormHoc from "../../../../hocs/form.hoc";
import InputGroupIcon from "../../other/InputGroupIcon";
import {compose} from "redux";
import {connect} from "react-redux";
import UserServices from "../../../../services/user.services";
import navbarGuestDispatchers from "../../../../redux/dispatchers/navbar.guest.dispatchers";
import authenticatedUserInfoDispatchers from "../../../../redux/dispatchers/authenticated.user.info.dispatchers";

class GuestLogin extends Component {

    constructor(props) {
        super(props);

        this.onLogin = this.onLogin.bind(this);
    }

    async onLogin() {
        let {email, password} = this.props.formData;
        let user = await UserServices.login(email, password);

        if (user) {
            this.props.fetchAuthenticatedUserInfo();
        }
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>

                <form className="px-4 py-3">

                    <InputGroupIcon icon={'👤/📧'}
                                    type={'text'}
                                    autoComplete={'on'}
                                    name={'email'}
                                    placeholder={'Email...'}
                                    onChange={onChange}/>

                    <InputGroupIcon icon={'🔒'}
                                    type={'password'}
                                    autoComplete={'on'}
                                    name={'password'}
                                    placeholder={'Password...'}
                                    onChange={onChange}/>

                    <button type="button"
                            data-style="zoom-in"
                            className="btn btn-primary"
                            onClick={this.onLogin}>
                        Login
                    </button>

                    <div className="dropdown-divider"/>
                </form>

                <div className="dropdown-item">
                    <button type="button"
                            onClick={this.props.selectRegister}
                            className="btn btn-success btn-sm">
                        Register
                    </button>
                </div>

                <div className="dropdown-item">
                    <button type="button"
                            className="btn btn-warning btn-sm"
                            onClick={this.props.selectForgottenPassword}>
                        Forgotten Password
                    </button>
                </div>
            </Fragment>
        );
    }

}

let mapState = (states) => {
    return {...states}
};

export default FormHoc(
    compose(
        connect(mapState, navbarGuestDispatchers),
        connect(mapState, authenticatedUserInfoDispatchers),
    )(GuestLogin)
)