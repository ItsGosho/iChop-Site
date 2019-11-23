import React, {Component} from 'react';
import FooterAuthenticatedLinks from "../components/FooterAuthenticatedLinks";
import FooterLinksWrapper from "../components/FooterLinksWrapper";

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