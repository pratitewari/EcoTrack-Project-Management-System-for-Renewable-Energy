import React from "react";
import style from "../Assets/Styles/ChartCardDataNotFound.module.css";
import PuffLoader from "react-spinners/PuffLoader";
// import SvgImage from "../Assets/Images/searching-data.svg";

const ChartCardDataNotFound = (props) => {
  return (
    <div
      className={
        props.insideChartCanvas
          ? style.parentDivInsideChartCanvas
          : style.parentDiv
      }
    >
      {props.dataLoaded == true ? (
        <PuffLoader size={50} />
      ) : (
        <>
          <img
            src={require("../Assets/Images/searching-data.png")}
            alt="No data found icon"
            width="110px"
            height="80px"
            style={{ marginBottom: "5px" }}
          />

          <span className={style.noDataSpan}>No Data Available</span>
        </>
      )}
    </div>
  );
};

export default ChartCardDataNotFound;
