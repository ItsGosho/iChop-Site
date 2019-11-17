import React, {Component} from "react";

class InputGroupIcon extends Component {

    render() {
        let {icon, type, autoComplete,reference, name, placeholder, onChange} = this.props;

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
                           ref={reference}
                           placeholder={placeholder}
                           onChange={onChange}/>

                    {this.props.children}
                </div>
            </div>

        );
    }

};

export default InputGroupIcon;