import React, {Component} from 'react';
import FormHoc from "../../../../hocs/form.hoc";

class UserOptionsChangePassword extends Component {

    constructor(props) {
        super(props);

        this.state = {
            password: '',
            confirmPassword: '123'
        };

        this.onResetClick = this.onResetClick.bind(this);
    }


    onResetClick() {
        console.log(this.state.password);
        console.log(this.state.confirmPassword);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <div className="container d-flex justify-content-center align-items-center">
                <div className="col-md-lg">
                    <div className="row">
                        <form className="px-4 py-3">
                            <h6 align="center">Enter your new password:</h6>
                            <div className="dropdown-divider"/>
                            <div className="input-group mb-2">
                                <div className="input-group-prepend">
                                    <div className="input-group-text">🔒</div>
                                </div>
                                <input type="password" className="form-control" id="input-password-resetPasswordForm"
                                       autoComplete="off"
                                       data-placement="top"
                                       data-toggle="popover" title="Password Requirements:" data-html="true"
                                       data-trigger="focus"
                                       data-content="-At least one uppercase character</br>-At least one lowercase character</br>-At least 6 characters"
                                       name="password" placeholder="New Password..." onChange={onChange} value={this.state.password}/>
                                <div className="input-group-append">
                                    <button className="btn btn-outline-success"
                                            id="button-showPassword-resetPasswordForm"
                                            type="button">Show
                                    </button>
                                </div>
                            </div>
                            <div className="form-group">
                                <div className="input-group mb-2">
                                    <div className="input-group-prepend">
                                        <div className="input-group-text">🔒</div>
                                    </div>
                                    <input type="password" className="form-control"
                                           id="input-confirmPassword-resetPasswordForm"
                                           autoComplete="off"
                                           name="confirmPassword"
                                           placeholder="Confirm New Password..."
                                           onChange={onChange} value={this.state.confirmPassword}/>
                                </div>
                                <small
                                    id="error-passwordsDoesntMatch-resetPasswordForm">⚡Passwords doesn't match!
                                </small>
                            </div>
                            <div align="center">
                                <button id="button-proceedResetPassword-resetPasswordForm" type="button"
                                        data-style="zoom-in"
                                        className="btn btn-primary btn-ladda" onClick={this.onResetClick}>Reset
                                </button>
                                <div className="dropdown-divider"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }

}

export default UserOptionsChangePassword;