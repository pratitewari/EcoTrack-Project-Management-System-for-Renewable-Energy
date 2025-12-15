import style from "../Assets/Styles/HeaderMenu.module.css";
import { NavLink } from "react-router-dom";
import React from "react";

function HeaderMenu(props) {
  return (
    <>
      <ul className={style.mainMenuUlTag}>
        <div className={`container-fluid ${style.mainMenuDivTag}`}>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/allProjects"}
              exact
            >
              <span>Projects</span>
            </NavLink>
          </li>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/addProject"}
              exact
            >
              <span>Add Project</span>
            </NavLink>
          </li>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/addBudget"}
              exact
            >
              <span>Budget</span>
            </NavLink>
          </li>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/addExpenses"}
              exact
            >
              <span>Expenses</span>
            </NavLink>
          </li>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/addEquipment"}
              exact
            >
              <span>Equipment</span>
            </NavLink>
          </li>
          <li>
            <NavLink
              className={style.menuItem}
              activeClassName={style.menubg}
              to={"/addMaintenance"}
              exact
            >
              <span>Maintenance</span>
            </NavLink>
          </li>
        </div>
      </ul>
    </>
  );
}

export default HeaderMenu;
