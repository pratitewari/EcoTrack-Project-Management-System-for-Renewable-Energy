import React, { useState, useEffect } from "react";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile"; // Ensure ServiceFile exposes getAllProjects and getAllEquipment

/**
 * EquipmentForm component allows users to add new equipment details while
 * dynamically fetching the list of projects and equipments from the server.
 * When a project is selected from the dropdown, its name is automatically updated in the form.
 *
 * @component
 * @returns {JSX.Element} The rendered EquipmentForm component.
 */
const EquipmentForm = () => {
  // State to hold form data.
  const [formData, setFormData] = useState({
    projectId: "",
    projectName: "",
    equipmentNo: "",
    type: "",
    installationDate: "",
    warrantyDate: "",
    quantity: "",
  });

  // State to store the equipment records.
  const [tableData, setTableData] = useState([]);
  // State to hold the fetched list of projects.
  const [projectsList, setProjectsList] = useState([]);

  // Static options for equipment type.
  const typeOptions = ["Solar Panel", "Storage battery", "Meter", "Earthing Cable "];

  /**
   * Fetch the project list from the API when the component mounts.
   */
  useEffect(() => {
    ServiceFile.getAllProjects()
      .then((res) => {
        // Expected res.data to be an array of project objects with 'projectId' and 'projectName'
        setProjectsList(res.data);
      })
      .catch((err) => {
        console.error("Error fetching projects:", err);
      });
  }, []);

  /**
   * Updates the formData's projectName when the projectId selection changes.
   */
  useEffect(() => {
    if (formData.projectId && projectsList.length > 0) {
      const selectedProject = projectsList.find(
        (proj) => proj.projectId === formData.projectId
      );
      if (selectedProject) {
        setFormData((prev) => ({
          ...prev,
          projectName: selectedProject.projectName,
        }));
      } else {
        setFormData((prev) => ({ ...prev, projectName: "" }));
      }
    }
  }, [formData.projectId, projectsList]);

  /**
   * Generic change handler for input and select elements.
   *
   * @param {React.ChangeEvent<HTMLInputElement | HTMLSelectElement>} e - The change event.
   */
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  /**
   * Flattens the API response (projects with nested equipment arrays)
   * into a single array of equipment records.
   *
   * @param {Array} projects - The array of project objects from the API.
   * @returns {Array} Flattened equipment list.
   */
  const flattenEquipments = (projects) => {
    let flattened = [];
    projects.forEach((project) => {
      if (project.equipment && project.equipment.length > 0) {
        project.equipment.forEach((eq) => {
          flattened.push({
            projectId: project.projectId,
            projectName: project.projectName,
            equipmentNo: eq.equipmentNumber,
            type: eq.equipmentType,
            installationDate: eq.equipmentInstallationDate,
            warrantyDate: eq.equipmentWarrantyDate,
            quantity: eq.equipmentQuantity,
          });
        });
      }
    });
    return flattened;
  };

  /**
   * Fetch and flatten the equipment records from the API.
   */
  useEffect(() => {
    refreshEquipments();
  }, []);

  const refreshEquipments = () => {
    ServiceFile.getAllEquipment()
      .then((res) => {
        console.log(res.data);
        // Flatten the nested equipment structure.
        setTableData(flattenEquipments(res.data));
      })
      .catch((err) => {
        console.log(err);
      });
  };

  /**
   * Handles the submission of the equipment form. Validates required fields,
   * updates the table data via API, resets the form, and shows alerts.
   *
   * @param {React.FormEvent<HTMLFormElement>} e - The form submission event.
   */
  const handleSubmit = (e) => {
    e.preventDefault();

    // Validate all required fields.
    if (
      !formData.projectId ||
      !formData.equipmentNo ||
      !formData.type ||
      !formData.installationDate ||
      !formData.warrantyDate ||
      !formData.quantity
    ) {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "Please fill out all required fields.",
      });
      return;
    }

    ServiceFile.saveEquipment(formData)
      .then((res) => {
        // Refresh equipments after saving.
        refreshEquipments();

        // Reset the form.
        setFormData({
          projectId: "",
          projectName: "",
          equipmentNo: "",
          type: "",
          installationDate: "",
          warrantyDate: "",
          quantity: "",
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
          text: "Failed to add project!",
        });
      });
  };

  return (
    <>
      <Row>
        <div className="pageHeading">Add Equipment</div>
      </Row>
      <Row style={{ marginTop: "5px" }}>
        <div>
          <Card className={filterStyle.filterCard}>
            <form onSubmit={handleSubmit} className="d-flex flex-column w-100">
              <Row className="mb-3">
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <select
                      name="projectId"
                      className="form-input"
                      value={formData.projectId}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select One
                      </option>
                      {projectsList.map((proj) => (
                        <option key={proj.projectId} value={proj.projectId}>
                          {`${proj.projectName}`}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Project</div>
                  </div>
                </Col>
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <input
                      type="text"
                      name="equipmentNo"
                      className="form-input"
                      placeholder="Enter Equipment No."
                      value={formData.equipmentNo}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Equipment No.</div>
                  </div>
                </Col>
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <select
                      name="type"
                      className="form-input"
                      value={formData.type}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select One
                      </option>
                      {typeOptions.map((item, index) => (
                        <option key={index} value={item}>
                          {item}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Type</div>
                  </div>
                </Col>
              </Row>
              <Row className="mb-3">
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <input
                      type="date"
                      name="installationDate"
                      className="form-input"
                      value={formData.installationDate}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Installation Date</div>
                  </div>
                </Col>
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <input
                      type="date"
                      name="warrantyDate"
                      className="form-input"
                      value={formData.warrantyDate}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Warranty Date</div>
                  </div>
                </Col>
                <Col md={4}>
                  <div className="form-input-with-title-div">
                    <input
                      type="number"
                      name="quantity"
                      className="form-input"
                      placeholder="Enter Quantity"
                      value={formData.quantity}
                      onChange={handleChange}
                      min="1"
                      required
                    />
                    <div className="form-input-title">Quantity</div>
                  </div>
                </Col>
              </Row>
              <Row style={{ marginTop: "10px" }}>
                <Col className="d-flex justify-content-end">
                  <button type="submit" className="submitButton">
                    Save
                  </button>
                </Col>
              </Row>
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
                    <thead className="table-head">
                      <tr>
                        <th>Project</th>
                        <th>Equipment No.</th>
                        <th>Type</th>
                        <th>Installation Date</th>
                        <th>Warranty Date</th>
                        <th>Quantity</th>
                      </tr>
                    </thead>
                    <tbody>
                      {tableData.length > 0 ? (
                        tableData.map((item, index) => (
                          <tr key={index}>
                            <td>{`${item.projectName}`}</td>
                            <td>{item.equipmentNo}</td>
                            <td>{item.type}</td>
                            <td>{item.installationDate}</td>
                            <td>{item.warrantyDate}</td>
                            <td>{item.quantity}</td>
                          </tr>
                        ))
                      ) : (
                        <tr>
                          <td colSpan={6} style={{ textAlign: "center" }}>
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

export default EquipmentForm;
