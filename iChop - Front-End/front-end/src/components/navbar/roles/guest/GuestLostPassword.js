import React, {Component, Fragment} from 'react';
import FormHoc from "../../../../hocs/form.hoc";
import InputGroupIcon from "../../other/InputGroupIcon";
import {compose} from "redux";
import {connect} from "react-redux";
import navbarGuestDispatchers from "../../../../redux/dispatchers/navbar.guest.dispatchers";
import UserServices from "../../../../services/user.services";

class GuestLostPassword extends Component {


    constructor(props) {
        super(props);

        this.onForgottenPassword = this.onForgottenPassword.bind(this);
    }

    onForgottenPassword() {
        let {email} = this.props.formData;
        UserServices.forgottenPassword(email);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>
                <form className="px-4 py-3">

                    <InputGroupIcon icon={'👤/📧'}
                                    type={'text'}
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

let mapState = (states) => {
    return {...states}
};

export default FormHoc(
    compose(connect(mapState, navbarGuestDispatchers))(GuestLostPassword)
)