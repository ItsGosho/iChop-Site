import React, {Component, Fragment} from 'react';
import FormHoc from "../../../../hocs/form.hoc";
import UniversalPasswordsInputs from "../../../other/UniversalPasswordsInputs";
import InputGroupIcon from "../../components/InputGroupIcon";
import UserServices from "../../../../services/user.services";
import {compose} from "redux";
import {connect} from "react-redux";
import formsDispatchers from "../../../../redux/dispatchers/forms.dispatchers";

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

    async onRegister() {
        let {username, password, confirmPassword, email} = this.props.formData;

        let isSuccessful = await UserServices.register(username, password, confirmPassword, email);

        if (isSuccessful) {
            this.props.selectGuestLogin();
        }
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
                            onClick={this.props.selectGuestLogin}>
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

let mapState = (states) => {
    return {...states}
};

export default FormHoc(
    compose(connect(mapState, formsDispatchers))(GuestRegister)
)