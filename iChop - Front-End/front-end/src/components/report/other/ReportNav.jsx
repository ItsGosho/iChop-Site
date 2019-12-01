import React from 'react';
import {DropdownButton, Form, FormControl, Nav, Navbar, Dropdown} from "react-bootstrap";
import styles from './ReportNav.module.css'
import QueryHelper from "../../../helpers/query.helper";
import {withRouter} from "react-router-dom";
import PropTypes from 'prop-types'
import EntityTypes from "../../../constants/enums/entity.types.constants";

const ReportNav = (props) => {
    let queryHelper = new QueryHelper(props);

    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className={styles.report_nav}>

            <Navbar.Brand>⚠ Reports</Navbar.Brand>

            <Navbar.Toggle/>
            <Navbar.Collapse>
                <Nav className="mr-auto">


                    <DropdownButton title="Entity Type" variant="warning">
                        <Dropdown.Item onClick={() => {
                            queryHelper.addQueryParameter('type', EntityTypes.USER_PROFILE_COMMENT);
                            props.fetchData();
                        }}>Post</Dropdown.Item>
                        <Dropdown.Item onClick={() => {
                            queryHelper.addQueryParameter('type', EntityTypes.THREAD);
                            props.fetchData();
                        }}>Thread</Dropdown.Item>
                        <Dropdown.Item onClick={() => {
                            queryHelper.addQueryParameter('type', EntityTypes.THREAD_COMMENT);
                            props.fetchData();
                        }}>Comment</Dropdown.Item>
                    </DropdownButton>

                </Nav>

                <Form inline>
                    <FormControl type="text" placeholder="Search by username" className="mr-sm-2"/>
                </Form>
            </Navbar.Collapse>
        </Navbar>
    )
};

export default withRouter(ReportNav);

ReportNav.propTypes = {
    fetchData: PropTypes.func
};