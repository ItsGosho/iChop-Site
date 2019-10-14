import React, {Component, Fragment} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";
import UniversalPasswordsInputs from "../../../other/UniversalPasswordsInputs";
import InputGroupIcon from "../../other/InputGroupIcon";

class GuestRegister extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isPasswordsShown: false
        };

        this.onRegister = this.onRegister.bind(this);
        this.onShowPasswords = this.onShowPasswords.bind(this);

        this.passwordRef = React.createRef();
        this.confirmPasswordRef = React.createRef();
    }

    onShowPasswords() {
        let passwordElement = this.passwordRef.current;
        let confirmPasswordElement = this.confirmPasswordRef.current;

        if (this.state.isPasswordsShown) {
            passwordElement.type = 'password';
            confirmPasswordElement.type = 'password';
        } else {
            passwordElement.type = 'text';
            confirmPasswordElement.type = 'text';
        }

        this.setState((prevState) => ({isPasswordsShown: !prevState.isPasswordsShown}))
    }

    onRegister() {
        let {username, password, confirmPassword, email} = this.props.formData;

        console.log(username);
        console.log(password);
        console.log(confirmPassword);
        console.log(email);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>
                <form className="px-4 py-3">

                    <InputGroupIcon icon={'👤'}
                                    type={'text'}
                                    autoComplete={'on'}
                                    name={'username'}
                                    placeholder={'Username...'}
                                    onChange={onChange}/>

                    <UniversalPasswordsInputs onChange={onChange}/>

                    <InputGroupIcon icon={'📧'}
                                    type={'email'}
                                    autoComplete={'on'}
                                    name={'email'}
                                    placeholder={'Email...'}
                                    onChange={onChange}/>

                    <button type="button"
                            className="btn btn-primary btn-sm"
                            onClick={this.props.selectLogin}>
                        Back
                    </button>

                    <button type="button"
                            className="btn btn-success btn-sm btn-ladda" data-style="zoom-in"
                            onClick={this.onRegister}>
                        Register
                    </button>

                    <div className="dropdown-divider"/>
                </form>
            </Fragment>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestRegister));