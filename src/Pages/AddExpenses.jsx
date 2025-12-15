import React, { useEffect, useState } from "react";
import style from "../Assets/Styles/Budget.module.css";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import ChartCardDataNotFound from "../Components/ChartCardDataNotFound";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile";
import { MdDelete } from "react-icons/md";

/**
 * ExpenseForm component is responsible for displaying a form to add expenses,
 * fetching project and budget data, and showing a table of current expenses.
 *
 * @component
 * @returns {JSX.Element} The rendered ExpenseForm component.
 */
const ExpenseForm = () => {
  // State for managing form input values.
  const [formData, setFormData] = useState({
    projectId: "",
    budgetId: "",
    expenseDate: "",
    expenseAmount: "",
  });

  // State for storing the list of expenses.
  const [tableData, setTableData] = useState([]);

  // State for the project and budget lists.
  const [projectsList, setProjectsList] = useState([]);
  const [budgetsList, setBudgetsList] = useState([]);

  /**
   * useEffect hook to initially fetch all expenses and projects.
   */
  useEffect(() => {
    refreshExpenses();
    refreshProjects();
  }, []);

  /**
   * Fetches all projects from the API and updates the projects state.
   */
  const refreshProjects = () => {
    ServiceFile.getAllProjects()
      .then((res) => {
        setProjectsList(res.data);
      })
      .catch((err) => {
        console.error("Error fetching projects:", err);
      });
  };

  /**
   * Fetches budgets for the selected project whenever the projectId changes.
   */
  useEffect(() => {
    if (formData.projectId) {
      ServiceFile.getprojectBudget(formData.projectId)
        .then((res) => {
          setBudgetsList(res.data.budgets);
        })
        .catch((err) => {
          console.error("Error fetching project budgets:", err);
        });
    } else {
      setBudgetsList([]);
    }
  }, [formData.projectId]);

  /**
   * Fetches all expenses from the API and updates the table data.
   */
  const refreshExpenses = () => {
    ServiceFile.getAllExpenses()
      .then((res) => {
        setTableData(res.data);
      })
      .catch((err) => {
        console.error("Error fetching expenses:", err);
      });
  };

  /**
   * Generic onChange handler for input and select elements.
   *
   * @param {React.ChangeEvent<HTMLInputElement | HTMLSelectElement>} e - The change event.
   */
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  /**
   * Specific onChange handler for the project dropdown.
   * Logs the selected projectId and updates the form data.
   *
   * @param {React.ChangeEvent<HTMLSelectElement>} e - The change event.
   */
  const handleProjectIdChange = (e) => {
    console.log("Selected ProjectId:", e.target.value);
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  /**
   * Handles the expense form submission.
   * Validates the form, refreshes the expense list, resets form data, and shows appropriate alerts.
   *
   * @param {React.FormEvent<HTMLFormElement>} e - The form submission event.
   */
  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      !formData.projectId ||
      !formData.budgetId ||
      !formData.expenseDate ||
      !formData.expenseAmount
    ) {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "Please fill out all required fields.",
      });
      return;
    }
    ServiceFile.saveExpenses(formData)
      .then((res) => {
        // Here you might want to add your expense submission logic.
        // For now, we simply refresh the expenses and reset the form.
        refreshExpenses();
        setFormData({
          projectId: "",
          budgetId: "",
          expenseDate: "",
          expenseAmount: "",
        });
        Swal.fire({
          icon: "success",
          title: "Success",
          text: "Expense added successfully!",
        });
      })
      .catch((err) => {
        console.log(err);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Failed to add project!",
        });
      });
  };

  return (
    <>
      <Row>
        <div className="pageHeading">Add Expenses</div>
      </Row>

      <Row style={{ marginTop: "5px" }}>
        <div>
          <Card className={filterStyle.filterCard}>
            <form onSubmit={handleSubmit} className="d-flex w-100">
              <Row
                style={{
                  borderRight: "0.5px solid #6f82937a",
                  width: "90%",
                }}
              >
                {/* Project Dropdown */}
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <select
                        name="projectId"
                        className="form-input"
                        value={formData.projectId}
                        onChange={handleProjectIdChange}
                        required
                      >
                        <option value="" disabled>
                          Select One
                        </option>
                        {projectsList.map((item) => (
                          <option key={item.projectId} value={item.projectId}>
                            {item.projectName}
                          </option>
                        ))}
                      </select>
                      <div className="form-input-title">Project Name</div>
                    </div>
                  </div>
                </Col>

                {/* Budget Type Dropdown */}
                <Col>
                  <div className="form-input-with-title-div">
                    <select
                      name="budgetId"
                      className="form-input"
                      value={formData.budgetId}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select One
                      </option>
                      {budgetsList.map((item) => (
                        <option key={item.budgetId} value={item.budgetId}>
                          {item.budgetType}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Budget Type</div>
                  </div>
                </Col>

                {/* Expense Date */}
                <Col>
                  <div className="form-input-with-title-div">
                    <input
                      type="date"
                      name="expenseDate"
                      className="form-input"
                      value={formData.expenseDate}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Expense Date</div>
                  </div>
                </Col>

                {/* Expense Amount Input */}
                <Col>
                  <div className="form-input-with-title-div">
                    <input
                      type="number"
                      name="expenseAmount"
                      className="form-input"
                      placeholder="Enter Amount"
                      value={formData.expenseAmount}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Amount</div>
                  </div>
                </Col>
              </Row>

              {/* Save Button */}
              <span
                className={filterStyle.filterCardIconsDiv}
                style={{ width: "10%" }}
              >
                <button
                  type="submit"
                  className="submitButton"
                  style={{ width: "70%" }}
                >
                  Save
                </button>
              </span>
            </form>
          </Card>
        </div>
      </Row>

      {/* Expense Table */}
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
                    <thead className="table-head">
                      <tr>
                        <th>Project</th>
                        <th>Budget Type</th>
                        <th>Expense Date</th>
                        <th>Amount</th>
                      </tr>
                    </thead>
                    <tbody>
                      {tableData.length > 0 ? (
                        tableData.map((item, index) => (
                          <tr key={index}>
                            <td>{item.project?.projectName || "N/A"}</td>
                            <td>{item.budget?.budgetType || "N/A"}</td>
                            <td>{item.expenseDate}</td>
                            <td>{item.expenseAmount}</td>
                          </tr>
                        ))
                      ) : (
                        <tr>
                          <td colSpan={4} style={{ textAlign: "center" }}>
                            No Data Available
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

export default ExpenseForm;
