import React, {Component} from 'react';
import PassworsForm from "../../../other/PassworsForm";

class UserOptionsChangePassword extends Component {

    constructor(props) {
        super(props);


        this.onResetClick = this.onResetClick.bind(this);
    }


    onResetClick() {
        /*RESET*/
        let {password, confirmPassword} = this.state;

        console.log(password);
        console.log(confirmPassword);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <form className="px-4 py-3">

                <PassworsForm ref onChange={onChange}/>

                <div align="center">

                    <button type="button"
                            data-style="zoom-in"
                            className="btn btn-primary btn-ladda"
                            onClick={this.onResetClick}>
                        Reset
                    </button>

                    <div className="dropdown-divider"/>
                </div>

            </form>
        );
    }

}

export default UserOptionsChangePassword;