import React, {Fragment} from "react";
import PropTypes from 'prop-types';

const FooterLinksWrapper = ({text, children}) => (
    <Fragment>
        <div className="col-md-2 mx-auto">
            <h5 className="font-weight-bold text-uppercase mt-3 mb-4">{text}</h5>
            <ul className="list-unstyled">{children}</ul>
        </div>
    </Fragment>
);


export default FooterLinksWrapper;

FooterLinksWrapper.propTypes = {
    text: PropTypes.string
};