import {connect} from "react-redux";

const withDispatcher = (dispatcher) => {
    const mapToState = (states) => ({...states});

    return (Comp) => {
        return connect(mapToState, dispatcher)(Comp);
    };
};

export default withDispatcher;