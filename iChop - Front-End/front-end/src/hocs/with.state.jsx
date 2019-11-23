import React from "react";
import {compose} from "redux";
import {connect} from "react-redux";

const withState = (Comp) => (compose(connect((states) => ({...states}), null))(Comp));


export default withState;