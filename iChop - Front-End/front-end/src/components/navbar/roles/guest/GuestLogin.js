import React, {Component, Fragment} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";
import InputGroupIcon from "../../other/InputGroupIcon";

class GuestLogin extends Component {

    constructor(props) {
        super(props);

        this.onLogin = this.onLogin.bind(this);
    }

    onLogin() {
        let {usernameOrEmail, password} = this.props.formData;

        console.log(usernameOrEmail);
        console.log(password);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>

                <form className="px-4 py-3">

                    <InputGroupIcon icon={'👤/📧'}
                                    type={'text'}
                                    autoComplete={'on'}
                                    name={'usernameOrEmail'}
                                    placeholder={'Username or Email...'}
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

export default FormHoc(navbarGuestReduxHoc(GuestLogin));