import React, { useState, useEffect } from "react";
import style from "../Assets/Styles/Login.module.css";
import Swal from "sweetalert2";
import { FaUser, FaLock, FaEye, FaEyeSlash } from "react-icons/fa";
import { useHistory } from "react-router-dom";

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const his = useHistory();
  const validCredentials = [
    { username: "prati", password: "123456", name: "Prati Tewari" },
    { username: "dishita", password: "123456", name: "Dishita Agrawal" },
  ];
  const getLoggedInUser = (credentials) => {
    return validCredentials.find(
      (cred) =>
        cred.username === credentials.username &&
        cred.password === credentials.password
    );
  };

  const handleLogin = (e) => {
    e.preventDefault();
    const user = getLoggedInUser(credentials);

    if (user) {
      sessionStorage.setItem("name", user.name);
      his.push("/allProjects");
    } else {
      Swal.fire({
        icon: "error",
        title: "Login Failed",
        text: "Invalid username or password",
        showConfirmButton: true,
      });
    }
  };

  return (
    <section className={style.login}>
      <div className={style.loginBox}>
        <div
          className={`row justify-content-center align-items-center ${style.left}`}
        >
          <div className={style.loginFormParent}>
            <div className={`${style.logoIcon}`}>
              <h3 className="text-center" style={{ marginTop: "40px" }}>
                SIGN IN
              </h3>
            </div>

            <form
              className={`col-md-10 col-sm-12 ${style.loginForm}`}
              // className={style.loginForm}
              onSubmit={handleLogin}
            >
              <div className={style.inputDiv}>
                <FaUser className={style.inputIcon} />
                <input
                  type="text"
                  placeholder="Username/ Email"
                  //   id="username"
                  required
                  autoComplete="off"
                  maxLength="15"
                  value={credentials.username}
                  onChange={(e) =>
                    setCredentials({
                      ...credentials,
                      username: e.target.value,
                    })
                  }
                  onPaste={(e) => e.preventDefault()}
                />
                {/* {credentials.username != "" && (
                    <div className="form-input-title">Username</div>
                  )} */}
              </div>

              <div className={style.inputDiv}>
                <FaLock className={style.inputIcon} />
                <input
                  type={showPassword ? "text" : "password"}
                  placeholder="Password"
                  id="password"
                  required
                  style={{ paddingRight: "40px" }}
                  autoComplete="off"
                  maxLength="15"
                  value={credentials.password}
                  onChange={(e) =>
                    setCredentials({
                      ...credentials,
                      password: e.target.value,
                    })
                  }
                  onPaste={(e) => e.preventDefault()}
                />

                {showPassword ? (
                  <FaEye
                    className={style.eyeIcon}
                    // title="Hide Password"
                    onClick={() => setShowPassword(!showPassword)}
                    // onMouseDown={() => setShowPassword(false)}
                    // onMouseUp={() => setShowPassword(false)}
                    // onMouseLeave={() => setShowPassword(false)}
                    // onMouseOut={() => setShowPassword(false)}
                    // onDrag={() => setShowPassword(false)}
                  />
                ) : (
                  <FaEyeSlash
                    className={style.eyeIcon}
                    // title="Show Password"
                    // onMouseDown={() => setShowPassword(true)}
                    onClick={() => setShowPassword(!showPassword)}
                  />
                )}
              </div>

              <div className={style.btnLogin}>
                <button className={style.actionBtn} type="submit">
                  LOGIN
                </button>
                <button
                  className={style.actionBtn}
                  type="button"
                  onClick={() => setCredentials({ username: "", password: "" })}
                >
                  RESET
                </button>
              </div>
            </form>
          </div>
        </div>
        <div className={style.right}>
          <div className={style.rightText}>
            <span>EcoTrack</span>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
