import React, {Component} from 'react';
import FooterLeftAuthenticatedSide from "../etc/FooterLeftAuthenticatedSide";
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";

class UserFooter extends Component {


    render() {

        return (
            <FooterSideListWrapper text={'VISIT'}>
                <FooterLeftAuthenticatedSide/>
            </FooterSideListWrapper>
        );
    }

}

export default UserFooter;