import React from "react";
import PropTypes from 'prop-types';


const InputGroupIcon = ({icon, type, autoComplete, reference, name, placeholder, onChange,children}) => (
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

            {children}
        </div>
    </div>
);

export default InputGroupIcon;


InputGroupIcon.propTypes = {
    icon: PropTypes.string,
    type: PropTypes.string,
    autoComplete: PropTypes.bool,
    reference: PropTypes.func,
    name: PropTypes.string,
    placeholder: PropTypes.string,
    onChange: PropTypes.func,
};