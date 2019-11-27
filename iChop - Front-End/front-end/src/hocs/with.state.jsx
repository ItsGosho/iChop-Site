import {connect} from "react-redux";

const withState = (Comp) => (connect((states) => ({...states}), null)(Comp));


export default withState;