import React, { useState, useEffect } from "react";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import { MdDelete } from "react-icons/md";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile"; // Ensure ServiceFile exposes getAllEquipment and saveMaintenance

/**
 * MaintenanceForm component allows users to add maintenance details for equipment.
 * It dynamically fetches project and equipment data (with nested equipment arrays) from
 * the API via getAllEquipment, auto-populates project details when a project is selected,
 * filters equipment accordingly, and displays maintenance records with deletion functionality.
 *
 * @component
 * @returns {JSX.Element} The rendered MaintenanceForm component.
 */
const MaintenanceForm = () => {
  // Updated state with consistent field names.
  const [formData, setFormData] = useState({
    projectId: "",
    projectName: "",
    equipmentId: "",
    maintenanceDate: "",
    maintenanceType: "",
    maintenanceDescription: "",
    maintenanceStatus: "",
  });

  // State for maintenance records.
  const [tableData, setTableData] = useState([]);
  // State for storing the fetched projects with nested equipment arrays.
  const [projectsEquipments, setProjectsEquipments] = useState([]);

  // Static options for maintenance type.
  const typeOptions = ["Repairs", "Periodic Maintenance", "Other"];

  /**
   * Fetches the project data (with nested equipment arrays) from the API on component mount.
   */
  useEffect(() => {
    ServiceFile.getAllEquipment()
      .then((res) => {
        // Expected res.data to be an array of project objects each containing an "equipment" array.
        setProjectsEquipments(res.data);
      })
      .catch((err) => {
        console.error("Error fetching projects and equipments:", err);
      });
  }, []);

  /**
   * Updates formData's projectName when a projectId is selected.
   * Also clears the equipment selection when the project changes.
   */
  useEffect(() => {
    if (formData.projectId && projectsEquipments.length > 0) {
      const selectedProject = projectsEquipments.find(
        (proj) => proj.projectId.toString() === formData.projectId
      );
      setFormData((prev) => ({
        ...prev,
        projectName: selectedProject ? selectedProject.projectName : "",
        equipmentId: "", // Explicitly clear equipment selection.
      }));
    }
  }, [formData.projectId, projectsEquipments]);

  /**
   * Generic change handler for input and select elements.
   *
   * @param {React.ChangeEvent<HTMLInputElement | HTMLSelectElement>} e - The change event.
   */
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  /**
   * Helper function to flatten maintenance records from the nested project-equipment structure.
   *
   * @param {Array} projects - Array of project objects with nested equipment arrays.
   * @returns {Array} Flattened array of maintenance records.
   */
  const flattenMaintenances = (projects) => {
    const flattened = [];
    projects.forEach((project) => {
      if (project.equipment && project.equipment.length > 0) {
        project.equipment.forEach((eq) => {
          if (eq.maintenances && eq.maintenances.length > 0) {
            eq.maintenances.forEach((maintenance) => {
              flattened.push({
                projectId: project.projectId,
                projectName: project.projectName,
                equipmentId: eq.equipmentId,
                equipmentNumber: eq.equipmentNumber,
                maintenanceDate: maintenance.maintenanceDate,
                maintenanceType: maintenance.maintenanceType,
                maintenanceDescription: maintenance.maintenanceDescription,
                maintenanceStatus: maintenance.maintenanceStatus,
              });
            });
          }
        });
      }
    });
    return flattened;
  };

  /**
   * Refreshes the maintenance records by calling the getAllEquipment API,
   * flattening the nested maintenances and updating the table data.
   */
  useEffect(() => {
    refreshEquipments();
  }, []);

  const refreshEquipments = () => {
    ServiceFile.getAllEquipment()
      .then((res) => {
        console.log(res.data);
        const flattenedMaintenances = flattenMaintenances(res.data);
        setTableData(flattenedMaintenances);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  /**
   * Handles the submission of the maintenance form. Validates required fields,
   * calls the API to save the maintenance record, updates the table data, and resets the form.
   *
   * @param {React.FormEvent<HTMLFormElement>} e - The form submission event.
   */
  const handleSubmit = (e) => {
    e.preventDefault();

    const {
      maintenanceType,
      maintenanceDate,
      maintenanceDescription,
      maintenanceStatus,
      equipmentId,
    } = formData;

    if (
      !maintenanceType ||
      !maintenanceDate ||
      !maintenanceDescription ||
      !maintenanceStatus ||
      !equipmentId
    ) {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "Please fill out all required fields.",
      });
      return;
    }

    ServiceFile.saveMaintenance(formData)
      .then(() => {
        // Append the new record to the table.
        //setTableData((prev) => [...prev, formData]);
        refreshEquipments();

        // Reset the form.
        setFormData({
          projectId: "",
          projectName: "",
          equipmentId: "",
          maintenanceDate: "",
          maintenanceType: "",
          maintenanceDescription: "",
          maintenanceStatus: "",
        });

        Swal.fire({
          icon: "success",
          title: "Success",
          text: "Maintenance record added successfully!",
        });
      })
      .catch((err) => {
        console.error(err);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Failed to add maintenance record!",
        });
      });
  };

  // Filter equipments based on the selected project.
  const filteredEquipments = formData.projectId
    ? projectsEquipments.find(
        (proj) => proj.projectId.toString() === formData.projectId
      )?.equipment || []
    : [];

  return (
    <>
      <Row>
        <div className="pageHeading">Add Maintenance Details</div>
      </Row>

      <Row style={{ marginTop: "5px" }}>
        <div>
          <Card className={filterStyle.filterCard}>
            <form onSubmit={handleSubmit} className="d-flex flex-column w-100">
              <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <select
                      name="projectId"
                      className="form-input"
                      value={formData.projectId}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select Project
                      </option>
                      {projectsEquipments.map((proj) => (
                        <option key={proj.projectId} value={proj.projectId}>
                          {proj.projectName}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Project Name</div>
                  </div>
                </Col>

                <Col>
                  <div className="form-input-with-title-div">
                    <select
                      name="equipmentId"
                      className="form-input"
                      value={formData.equipmentId}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select Equipment
                      </option>
                      {filteredEquipments.map((eq) => (
                        <option key={eq.equipmentId} value={eq.equipmentId}>
                          {eq.equipmentNumber}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Equipment No.</div>
                  </div>
                </Col>
              </Row>

              <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <input
                      type="date"
                      name="maintenanceDate"
                      className="form-input"
                      value={formData.maintenanceDate}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Maintenance Date</div>
                  </div>
                </Col>

                <Col>
                  <div className="form-input-with-title-div">
                    <select
                      name="maintenanceType"
                      className="form-input"
                      value={formData.maintenanceType}
                      onChange={handleChange}
                      required
                    >
                      <option value="" disabled>
                        Select Type
                      </option>
                      {typeOptions.map((option, idx) => (
                        <option key={idx} value={option}>
                          {option}
                        </option>
                      ))}
                    </select>
                    <div className="form-input-title">Type</div>
                  </div>
                </Col>
              </Row>

              <Row className="mb-3">
                <Col md={6}>
                  <div className="form-input-with-title-div">
                    <input
                      type="text"
                      name="maintenanceStatus"
                      className="form-input"
                      placeholder="Enter Status"
                      value={formData.maintenanceStatus}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Status</div>
                  </div>
                </Col>
                <Col md={6}>
                  <div className="form-input-with-title-div">
                    <input
                      type="text"
                      name="maintenanceDescription"
                      className="form-input"
                      placeholder="Enter Description"
                      value={formData.maintenanceDescription}
                      onChange={handleChange}
                      required
                    />
                    <div className="form-input-title">Description</div>
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
                        <th>Maintenance Date</th>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Status</th>
                      </tr>
                    </thead>
                    <tbody>
                      {tableData.length > 0 ? (
                        tableData.map((item, index) => (
                          <tr key={index}>
                            <td>{item.projectName}</td>
                            <td>{item.equipmentNumber}</td>
                            <td>{item.maintenanceDate}</td>
                            <td>{item.maintenanceType}</td>
                            <td>{item.maintenanceDescription}</td>
                            <td>{item.maintenanceStatus}</td>
                           
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

export default MaintenanceForm;
