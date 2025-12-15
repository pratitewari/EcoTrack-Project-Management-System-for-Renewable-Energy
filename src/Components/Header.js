import style from "../Assets/Styles/Header.module.css";
import { Navbar, Nav, Row } from "react-bootstrap";
import Dropdown from "react-bootstrap/Dropdown";
import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom/cjs/react-router-dom.min";
import { PiUserCircleFill } from "react-icons/pi";
import { IoMdArrowDropdown, IoMdNotifications } from "react-icons/io";
import { GrPowerReset } from "react-icons/gr";
import { IoIosSave } from "react-icons/io";
import Swal from "sweetalert2";
import { NavLink } from "react-router-dom";

function Header(props) {
  const history = useHistory();

  const logout = () => {
    history.push("/login");
    sessionStorage.clear();
  };

  return (
    <>
      {/* {role === "" || role === null || role === undefined ? (
        ""
      ) : ( */}
      <Navbar
        bg="white"
        variant="light"
        id={style.navbarContainer}
        style={{ borderBottomColor: props.color }}
      >
        <div
          className="container-fluid d-flex justify-content-between align-items-center"
          style={{ position: "relative" }}
        >
          <Navbar.Brand className="d-flex ms-2">
            <div>EcoTrack</div>
          </Navbar.Brand>

          <Nav.Item id={style.welcomeClass}>
            <span id={style.headName}>
              <span>Project Management System</span>
            </span>
          </Nav.Item>

          <Nav.Item className="d-flex align-items-center me-2">
            <Dropdown>
              <Dropdown.Toggle id={`dropdown-basic ${style.accountDiv}`}>
                <PiUserCircleFill
                  id={style.userIcon}
                  style={{ color: props.color }}
                />
                <IoMdArrowDropdown
                  id={style.dropdownArrowIcon}
                  style={{ color: props.color }}
                />
              </Dropdown.Toggle>

              <Dropdown.Menu
                className="dropdown-menu-end"
                id={style.profileDetailsDropdown}
              >
                <Dropdown.ItemText id={style.profileDetails}>
                  <ul
                    style={{
                      listStyle: "none",
                      marginBottom: 0,
                    }}
                  >
                    <li style={{ fontWeight: "400" }}>
                      {sessionStorage.getItem("name")}
                    </li>
                  </ul>
                </Dropdown.ItemText>
                <Dropdown.Divider />

                <Dropdown.Item onClick={logout}>Logout</Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </Nav.Item>
        </div>
      </Navbar>
    </>
  );
}

export default Header;
