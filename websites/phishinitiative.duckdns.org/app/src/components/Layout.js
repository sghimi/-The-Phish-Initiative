import React from "react";
import {Outlet} from "react-router-dom";
import MyNavbar from "./MyNavbar";
import Footer from "./Footer";

const Layout = () => {
  return (
    <>
      <MyNavbar />
      <Footer />
      <Outlet />
    </>
  );
};

export default Layout;