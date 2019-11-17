import React, {Component} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing/routing.constants";
import {Form, FormControl, Nav, Navbar} from "react-bootstrap";
import styles from './ReportNav.module.css'

class ReportNav extends Component {


    render() {
        return (
            <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" className={styles.report_nav}>

                <Navbar.Brand>⚠ Reports</Navbar.Brand>

                <Navbar.Toggle/>
                <Navbar.Collapse>
                    <Nav className="mr-auto">


                        <Link className="nav-link" to={RoutingURLs.THREAD.REPORT.ALL}>Threads<span
                            className="sr-only">(current)</span></Link>

                        <Link className="nav-link" to={RoutingURLs.COMMENT.REPORT.ALL}>Comments<span
                            className="sr-only">(current)</span></Link>

                        <Link className="nav-link" to={RoutingURLs.POST.REPORT.ALL}>Posts<span
                            className="sr-only">(current)</span></Link>

                    </Nav>

                    <Form inline>
                        <FormControl type="text" placeholder="Search by username" className="mr-sm-2" />
                    </Form>
                </Navbar.Collapse>
            </Navbar>
        );
    }

}

export default ReportNav;