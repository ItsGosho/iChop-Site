import React, {Component, Fragment} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

class GuestRegister extends Component {

    constructor(props) {
        super(props);

        this.onRegister = this.onRegister.bind(this);
    }

    onRegister() {
        let {username, password,confirmPassword,email} = this.props.formData;

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
                                   data-placement="top"
                                   data-toggle="popover" title="Password Requirements:" data-html="true"
                                   data-trigger="focus"
                                   data-content="-At least one uppercase character</br>-At least one lowercase character</br>-At least 6 characters"
                                   name="password" placeholder="Password..." onChange={onChange}/>
                            <div className="input-group-append">
                                <button className="btn btn-outline-success"
                                        type="button">Show
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
                                   name="confirmPassword" placeholder="Confirm Password..." onChange={onChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">📧</div>
                            </div>
                            <input type="email" className="form-control"  name="email"
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