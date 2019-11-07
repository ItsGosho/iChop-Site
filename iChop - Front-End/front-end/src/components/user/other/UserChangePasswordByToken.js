import React, {Component} from 'react';
import UniversalPasswordsInputs from "../../other/UniversalPasswordsInputs";

class UserChangePasswordByToken extends Component {

    constructor(props) {
        super(props);

        this.onChangeClick = this.onChangeClick.bind(this);
    }

    onChangeClick() {
        let {password, confirmPassword} = this.state;

        console.log(password);
        console.log(confirmPassword);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <div className="col-sm d-flex justify-content-center">
                <div className="card">
                    <div className="card-body">
                        <form className="px-4 py-3">

                            <UniversalPasswordsInputs onChange={onChange}/>

                            <div align="center">

                                <button type="button"
                                        data-style="zoom-in"
                                        className="btn btn-primary btn-ladda"
                                        onClick={this.onChangeClick}>
                                    Reset
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

export default UserChangePasswordByToken;