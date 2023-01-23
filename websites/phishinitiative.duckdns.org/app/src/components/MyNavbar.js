import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import logo from './logo1.png';
import './mynavbar.style.css';

const navStyle = {
  fontFamily: 'Open Sans',
  fontSize: '20px',
  fontWeight: '200',
  color:'white'
}

const MyNavbar = () => {
  return (
    <>
  <Navbar fixed="top" collapseOnSelect expand="md"  variant="dark" className="animate-navbar nav-theme justify-content-between">
  <Navbar.Brand href="#home"> <img src= {logo} alt = '' width={200} height={50} /> </Navbar.Brand>
  <Navbar.Toggle aria-controls="responsive-navbar-nav" />
  <Navbar.Collapse id="responsive-navbar-nav">
    <Nav className="ml-auto">
      <Nav.Link style = {navStyle} href="" >Home</Nav.Link>
      <Nav.Link style = {navStyle} href="">About</Nav.Link>
      <Nav.Link style = {navStyle} href="">Contact</Nav.Link>
    
    </Nav>
  </Navbar.Collapse>
</Navbar>

    </>
  );
}

export default MyNavbar;