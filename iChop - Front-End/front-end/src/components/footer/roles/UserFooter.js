import React, {Component} from 'react';
import FooterAuthenticatedLinks from "../etc/FooterAuthenticatedLinks";
import FooterLinksWrapper from "../wrappers/FooterLinksWrapper";

class UserFooter extends Component {


    render() {

        return (
            <FooterLinksWrapper text={'VISIT'}>
                <FooterAuthenticatedLinks/>
            </FooterLinksWrapper>
        );
    }

}

export default UserFooter;