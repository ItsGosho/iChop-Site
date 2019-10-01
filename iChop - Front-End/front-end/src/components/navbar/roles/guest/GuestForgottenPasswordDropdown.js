import React,{Component} from 'react';

class GuestForgottenPasswordDropdown extends Component {


    render() {

        return (
            <div id="div-forgotten_password-dropdown">
                <form className="px-4 py-3" id="form-forgottenPassword">
                    <div className="form-group">
                        <div className="input-group mb-2">

                            <div className="input-group-prepend">
                                <div className="input-group-text">👤/📧</div>
                            </div>
                            <input type="text" className="form-control"
                                   id="input-usernameOrEmail-forgottenPasswordForm"
                                   autoComplete="off"
                                   name="usernameOrEmail" placeholder="Username or Email..."/>
                        </div>
                    </div>

                    <button type="button" id="button-goToLogin-forgottenPasswordForm"
                            className="btn btn-primary btn-sm">Back
                    </button>
                    <button id="button-proceedEmailSend-forgottenPasswordForm" type="button" data-style="zoom-in"
                            className="btn btn-success btn-sm">Send
                    </button>

                    <div className="dropdown-divider"></div>
                    <div id="container-notification-forgottenPassword">
                        <div className="alert alert-danger p-1"
                             id="error-noUserExistsWithTheProvidedCredentials-forgottenPasswordForm">
                            <strong>⚡</strong> No user exists with the provided credentials!
                        </div>
                        <div className="alert alert-info p-1" id="notification-pleaseWait-forgottenPasswordForm">
                            <strong>🔔</strong> Please wait!
                        </div>
                        <div className="alert alert-success p-1" id="notification-successful-forgottenPasswordForm">
                            <strong>✔</strong> Password reset token has been sent to your email!
                        </div>
                    </div>
                </form>
            </div>
        );
    }

}

export default GuestForgottenPasswordDropdown;