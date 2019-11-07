import React, {Component} from "react";
import {compose} from "redux";
import {connect} from "react-redux";

const withState = (Comp) => {

    let mapState = (states) => {
        return {...states}
    };

    return compose(
        connect(mapState, null)
    )(Comp);
};


export default withState;