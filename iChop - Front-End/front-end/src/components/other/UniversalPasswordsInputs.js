import React, {Component, Fragment} from 'react';
import InputGroupIcon from "../navbar/other/InputGroupIcon";

class UniversalPasswordsInputs extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isPasswordsShown: false
        };

        this.onShowPasswords = this.onShowPasswords.bind(this);
        this.onChange = this.onChange.bind(this);

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

    onChange() {
        let password = this.passwordRef.current.value;
        let confirmPassword = this.confirmPasswordRef.current.value;

        if (password !== confirmPassword) {
            this.passwordRef.current.style['border-color'] = 'red';
            this.confirmPasswordRef.current.style['border-color'] = 'red';
        } else {
            this.passwordRef.current.style['border-color'] = '';
            this.confirmPasswordRef.current.style['border-color'] = '';
        }
    }

    render() {
        let {onChange} = this.props;

        return (
            <Fragment>

                <InputGroupIcon icon={'🔒'}
                                type={'password'}
                                autoComplete={'off'}
                                name={'password'}
                                placeholder={'Password...'}
                                reference={this.passwordRef}
                                onChange={(event) => {
                                       onChange(event);
                                       this.onChange(event);
                                   }}>


                    <div className="input-group-append">
                        <button type="button"
                                className="btn btn-outline-success"
                                onClick={this.onShowPasswords}>
                            Show
                        </button>
                    </div>

                </InputGroupIcon>


                <InputGroupIcon icon={'🔒'}
                                type={'password'}
                                autoComplete={'off'}
                                name={'confirmPassword'}
                                placeholder={'Confirm Password...'}
                                reference={this.confirmPasswordRef}
                                onChange={(event) => {
                                       onChange(event);
                                       this.onChange(event);
                                   }}/>

            </Fragment>
        );
    }

}

export default UniversalPasswordsInputs;