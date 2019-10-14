import React, {Component} from "react";

class InputGroupWithIcon extends Component {

    render() {
        let {icon, type, autoComplete, name, placeholder, onChange} = this.props;

        return (
            <div className="form-group">
                <div className="input-group mb-2">

                    <div className="input-group-prepend">
                        <div className="input-group-text">{icon}</div>
                    </div>

                    <input type={type}
                           className="form-control"
                           autoComplete={autoComplete}
                           name={name}
                           placeholder={placeholder}
                           onChange={onChange}/>
                </div>
            </div>

        );
    }

};

export default InputGroupWithIcon;