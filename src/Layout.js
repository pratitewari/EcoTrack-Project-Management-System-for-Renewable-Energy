import React, { useEffect, useState } from "react";
import Header from "./Components/Header";
import HeaderMenu from "./Components/HeaderMenu";
import { useHistory } from "react-router-dom";

const Layout = (props) => {
  const his = useHistory();
  useEffect(() => {
    if (!sessionStorage.getItem("name")) {
      his.push("/login");
    }
  }, [props.children]);

  return (
    <div
      className="d-flex flex-column"
      style={{ height: "100vh", width: "100vw" }}
    >
      <Header />
      <HeaderMenu />

      <main>{props.children}</main>
    </div>
  );
};

export default Layout;
