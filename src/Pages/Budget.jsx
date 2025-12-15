import React, { useEffect, useState } from "react";
import style from "../Assets/Styles/Budget.module.css";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import ChartCardDataNotFound from "../Components/ChartCardDataNotFound";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile";
import { MdDelete } from "react-icons/md";
 
const Budget = () => {
  const [projectsList, setProjectsList] = useState([]);
  const [budgetList, setBudgetList] = useState([
    "Instruments",
    "Installation",
    "Staff Salary",
    "Miscellaneous",
  ]);
  const [formData, setFormData] = useState({
    projectId:"",
    projectName: "",
    budgetType: "",
    amount: "",
  });
 
  const handleFormDataChange = (e) => {
    console.log(e)
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
 
  const [tableData, setTableData] = useState([
  ]); //Empty this array when API is designed
 
  useEffect(() => {
    ServiceFile.getAllProjects()
      .then((res) => {
        console.log(res.data);
        setProjectsList(res.data);
      })
 
      .catch((err) => {
        console.log(err);
      });
  }, [])
 
 
 
 
  const getAllBudget = async () => {
    await ServiceFile.getAllBudget().then((res1) => {
      setTableData(res1.data);
    }).catch((err) => {
      console.log(err);
    });
  };
 
  const handleSave = async () => {
    await ServiceFile.saveBudget(formData)
      .then(async (res) => {
        await getAllBudget();
        //edit
        setFormData({
          projectId:"",
          projectName: "",
          budgetType: "",
          amount: ""
        });
          Swal.fire({
          icon: "success",
          title: "Success",
          text: "Equipment added successfully!",
        });
      })
      .catch((err) => {
        console.log(err);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Failed to add budget or budget already added for this project!",
          });
      });
 
   
  };
 
  useEffect(() => {
    getAllBudget();
  }, []);
 
  return (
    <>
      <Row>
        <div className="pageHeading">Add Budget</div>
      </Row>
      <Row style={{ marginTop: "5px" }}>
        <div>
          <Card className={filterStyle.filterCard}>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                handleSave();
              }}
              className="d-flex w-100"
            >
              <Row
                style={{
                  borderRight: "0.5px solid #6f82937a",
                  width: "90%",
                }}
              >
 
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <select
                        name="projectId"
                        className="form-input"
                        value={formData.projectId}
                        onChange={handleFormDataChange}
                        required
                      >
                        <option selected disabled value="">
                          Select One
                        </option>
                        {projectsList.map((item) => (
                          <option value={item.projectId}>{item.projectName}</option>
                        ))}
                      </select>
 
                      <div className="form-input-title">Project Name</div>
                    </div>
                  </div>
                </Col>
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <select
                        name="budgetType"
                        className="form-input"
                        onChange={handleFormDataChange}
                        required
                      >
                        <option selected disabled value="">
                          Select One
                        </option>
                        {budgetList.map((item) => (
                          <option value={item}>{item}</option>
                        ))}
                      </select>
 
                      <div className="form-input-title">Budget Type</div>
                    </div>
                  </div>
                </Col>
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <input
                        type="number"
                        name="amount"
                        className="form-input"
                        onChange={handleFormDataChange}
                        placeholder="Enter Budget Amount"
                        required
                      />
                      <div className="form-input-title">Budget Amount</div>
                    </div>
                  </div>
                </Col>
              </Row>
              <span
                className={filterStyle.filterCardIconsDiv}
                style={{ width: "10%" }}
              >
                <button
                  className="submitButton"
                  type="submit"
                  style={{ width: "70%" }}
                >
                  Save
                </button>
              </span>
            </form>
          </Card>
        </div>
      </Row>
      <div className="fullMainLowerDiv">
        <Row style={{ height: "100%" }}>
          <Col sm={12} style={{ overflow: "hidden", height: "100%" }}>
            <Card className="chartCard" style={{ height: "100%" }}>
              <Card.Body className="d-flex" style={{ overflow: "hidden" }}>
                <div className="tableDiv">
                  <table
                    className="table table-bordered table-tag table-striped"
                    style={{
                      minHeight: tableData.length > 0 ? "unset" : "100%",
                    }}
                  >
                    {/* Table Header (Displayed Once) */}
                    <thead className="table-head">
                      <tr>
                        <th>Project Name</th>
                        <th>Budget Type</th>
                        <th>Budget Amount</th>
                      </tr>
                    </thead>
                    <tbody>
                      {tableData.length > 0 ? (
                        tableData.map((item, index) => (
                          <React.Fragment key={index}>
                            {/* Actual table row */}
                            {item&&item.project&&<tr>
                              <td style={{ textAlign: "center" }}>{item.project.projectName}</td>
                              <td style={{ textAlign: "center" }}>{item.budgetType}</td>
                              <td style={{ textAlign: "center" }}>{item.budgetAmount}
                              </td>
                            </tr>}
                          </React.Fragment>
                        ))
                      ) : (
                        <tr>
                          <td colSpan={3}>
                            <ChartCardDataNotFound insideChartCanvas={true} />
                          </td>
                        </tr>
                      )}
                    </tbody>
                  </table>
                </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
};
 
export default Budget;
 
 