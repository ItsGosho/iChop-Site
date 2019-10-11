import React, {Component, Fragment} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

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

        this.setState((prevState)=> ({isPasswordsShown: !prevState.isPasswordsShown}))
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

                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">👤</div>
                            </div>
                            <input type="text" className="form-control"
                                   name="username" placeholder="Username..." autoComplete="off" onChange={onChange}/>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control"
                                   autoComplete="off"
                                   data-trigger="focus"
                                   name="password" placeholder="Password..." ref={this.passwordRef}
                                   onChange={onChange}/>
                            <div className="input-group-append">
                                <button className="btn btn-outline-success"
                                        type="button" onClick={this.onShowPasswords}>Show
                                </button>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control"
                                   autoComplete="off"
                                   name="confirmPassword" placeholder="Confirm Password..."
                                   ref={this.confirmPasswordRef} onChange={onChange}/>
                        </div>
                    </div>

                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">📧</div>
                            </div>
                            <input type="email" className="form-control" name="email"
                                   autoComplete="off"
                                   placeholder="Email..." onChange={onChange}/>
                        </div>
                    </div>

                    <button type="button" onClick={this.props.selectLogin}
                            className="btn btn-primary btn-sm">Back
                    </button>

                    <button type="button"
                            className="btn btn-success btn-sm btn-ladda" data-style="zoom-in" onClick={this.onRegister}>
                        Register
                    </button>

                    <div className="dropdown-divider"/>
                </form>
            </Fragment>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestRegister));