import React, {Fragment} from "react";
import CreateReactClass from 'create-react-class';
import PropTypes from 'prop-types';

let FooterLinksWrapper = CreateReactClass({
    render() {
        return (
            <Fragment>
                <div className="col-md-2 mx-auto">
                    <h5 className="font-weight-bold text-uppercase mt-3 mb-4">{this.props.text}</h5>

                    <ul className="list-unstyled">
                        {this.props.children}
                    </ul>
                </div>
            </Fragment>
        );
    }
});


export default FooterLinksWrapper;

FooterLinksWrapper.propTypes = {
    text: PropTypes.string
};