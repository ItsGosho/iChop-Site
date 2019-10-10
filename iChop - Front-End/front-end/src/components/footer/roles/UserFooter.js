import React, {Component} from 'react';
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterLinksWrapper from "../wrappers/FooterLinksWrapper";

class UserFooter extends Component {


    render() {

        return (
            <FooterLinksWrapper text={'VISIT'}>
                <FooterLeftAuthenticatedSide/>
            </FooterLinksWrapper>
        );
    }

}

export default UserFooter;