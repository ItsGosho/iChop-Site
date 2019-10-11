import React, {Component, Fragment} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

class GuestLostPassword extends Component {


    constructor(props) {
        super(props);

        this.onForgottenPassword = this.onForgottenPassword.bind(this);
    }

    onForgottenPassword() {
        let {usernameOrEmail} = this.props.formData;

        console.log(usernameOrEmail);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>
                <form className="px-4 py-3">

                    <div className="form-group">
                        <div className="input-group mb-2">

                            <div className="input-group-prepend">
                                <div className="input-group-text">👤/📧</div>
                            </div>

                            <input type="text"
                                   className="form-control"
                                   autoComplete="on"
                                   name="usernameOrEmail"
                                   placeholder="Username or Email..."
                                   onChange={onChange}/>

                        </div>
                    </div>

                    <button type="button"
                            className="btn btn-primary btn-sm"
                            onClick={this.props.selectLogin}>
                        Back
                    </button>

                    <button type="button"
                            data-style="zoom-in"
                            className="btn btn-success btn-sm"
                            onClick={this.onForgottenPassword}>
                        Send
                    </button>

                    <div className="dropdown-divider"/>
                </form>
            </Fragment>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestLostPassword));