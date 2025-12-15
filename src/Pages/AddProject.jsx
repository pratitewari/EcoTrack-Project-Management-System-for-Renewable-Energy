import React, { useState, useEffect } from "react";
import style from "../Assets/Styles/Budget.module.css";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile";

const AddProject = () => {
  const [formData, setFormData] = useState({
    projectName: "",
    description: "",
    type: "",
    location: "",
    capacity: "",
    startDate: "",
  });

  const typeOptions = ["Wind Energy", "Solar Energy", "Hydro Energy"];

  const locationOptions = [
    "Udupi",
    "Chamarajanagara",
    "Chikkamagaluru",
    "Dakshina Kannada",
    "Hassan",
    "Kodagu",
    "Mandya",
    "Mysuru",
  ];
      
  const handleFormDataChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave =  (e) => {
    e.preventDefault();
    console.log("Form Data Submitted:", formData);
    
     ServiceFile.saveProject(formData)
      .then((res) => {
        Swal.fire({
          icon: "success",
          title: "Success",
          text: "Project added successfully!",
        });
      })
      .catch((err) => {
        console.log(err);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Failed to add project or this project is already added!",
        });
      });
  };

  return (
    <>
      <Row>
        <div className="pageHeading">Project Details</div>
      </Row>
      <Row style={{ marginTop: "5px" }}>
        <div>
          <Card className={filterStyle.filterCard}>
            <form
              onSubmit={handleSave}
              className="d-flex flex-column w-100"
            >
              <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <input
                        type="text"
                        name="projectName"
                        className="form-input"
                        value={formData.projectName}
                        onChange={handleFormDataChange}
                        placeholder="Enter Project Name"
                        required
                      />
                      <div className="form-input-title">Project Name<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>
                </Row>
                <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <textarea
                        name="description"
                        className="form-input"
                        value={formData.description}
                        onChange={handleFormDataChange}
                        placeholder="Enter Description"
                        rows="3"
                        required
                      />
                      <div className="form-input-title">Description<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>
              </Row>
              
              <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <select
                        name="type"
                        className="form-input"
                        value={formData.type}
                        onChange={handleFormDataChange}
                        required
                      >
                        <option selected disabled value="">
                          Select One
                        </option>
                        {typeOptions.map((item, index) => (
                          <option key={index} value={item}>{item}</option>
                        ))}
                      </select>
                      <div className="form-input-title">Type<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <select
                        name="location"
                        className="form-input"
                        value={formData.location}
                        onChange={handleFormDataChange}
                        required
                      >
                        <option selected disabled value="">
                          Select One
                        </option>
                        {locationOptions.map((item, index) => (
                          <option key={index} value={item}>{item}</option>
                        ))}
                      </select>
                      <div className="form-input-title">Location<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>
              </Row>

              <Row className="mb-3">
                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <input
                        type="number"
                        name="capacity"
                        className="form-input"
                        value={formData.capacity}
                        onChange={handleFormDataChange}
                        placeholder="Enter Capacity in mW"
                        required
                      />
                      <div className="form-input-title">Capacity (mW)<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>

                <Col>
                  <div className="form-input-with-title-div">
                    <div>
                      <input
                        type="date"
                        name="startDate"
                        className="form-input"
                        value={formData.startDate}
                        onChange={handleFormDataChange}
                        required
                      />
                      <div className="form-input-title">Start Date<span className="text-danger">*</span></div>
                    </div>
                  </div>
                </Col>
              </Row>


              <Row>
                <Col className="d-flex justify-content-end">
                  <button
                    className="submitButton"
                    type="submit"
                  >
                    Save
                  </button>
                </Col>
              </Row>
            </form>
          </Card>
        </div>
      </Row>
    </>
  );
};

export default AddProject;

