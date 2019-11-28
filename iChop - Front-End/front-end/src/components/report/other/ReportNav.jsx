import React from 'react';
import {DropdownButton, Form, FormControl, Nav, Navbar,Dropdown} from "react-bootstrap";
import styles from './ReportNav.module.css'

const ReportNav = () => (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className={styles.report_nav}>

        <Navbar.Brand>⚠ Reports</Navbar.Brand>

        <Navbar.Toggle/>
        <Navbar.Collapse>
            <Nav className="mr-auto">


                <DropdownButton title="Entity Type" variant="warning">
                    <Dropdown.Item>Post</Dropdown.Item>
                    <Dropdown.Item>Thread</Dropdown.Item>
                    <Dropdown.Item>Comment</Dropdown.Item>
                </DropdownButton>

            </Nav>

            <Form inline>
                <FormControl type="text" placeholder="Search by username" className="mr-sm-2" />
            </Form>
        </Navbar.Collapse>
    </Navbar>
);

export default ReportNav;