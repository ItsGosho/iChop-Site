import React, {Fragment} from "react";
import CreateReactClass from 'create-react-class';

var FooterLinksWrapper = CreateReactClass({
    render() {
        return (
            <Fragment>
                <h5 className="font-weight-bold text-uppercase mt-3 mb-4">{this.props.text}</h5>

                <ul className="list-unstyled">
                    {this.props.children}
                </ul>

            </Fragment>
        );
    }
});


export default FooterLinksWrapper;