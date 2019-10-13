import React, {Component} from 'react';

class UserOptionsChangePassword extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isPasswordsShown: false
        };

        this.onResetClick = this.onResetClick.bind(this);
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

    onResetClick() {
        console.log(this.state.password);
        console.log(this.state.confirmPassword);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <form className="px-4 py-3">

                <div className="form-group">
                    <div className="input-group mb-2">

                        <div className="input-group-prepend">
                            <div className="input-group-text">🔒</div>
                        </div>

                        <input type="password"
                               className="form-control"
                               autoComplete="off"
                               data-trigger="focus"
                               name="password"
                               placeholder="Password..."
                               ref={this.passwordRef}
                               onChange={onChange}/>

                        <div className="input-group-append">
                            <button type="button"
                                    className="btn btn-outline-success"
                                    onClick={this.onShowPasswords}>
                                Show
                            </button>
                        </div>

                    </div>
                </div>

                <div className="form-group">
                    <div className="input-group mb-2">

                        <div className="input-group-prepend">
                            <div className="input-group-text">🔒</div>
                        </div>

                        <input type="password"
                               className="form-control"
                               autoComplete="off"
                               name="confirmPassword"
                               placeholder="Confirm Password..."
                               ref={this.confirmPasswordRef}
                               onChange={onChange}/>

                    </div>
                </div>

            </form>
        );
    }

}

export default UserOptionsChangePassword;