import {connect} from "react-redux";

const withDispatcher = (dispatcher,Comp) => {
    const mapToState = (states) => ({...states});

    return connect(mapToState, dispatcher)(Comp);
};

export default withDispatcher;