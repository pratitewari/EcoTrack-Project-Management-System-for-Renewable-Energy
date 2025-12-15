import React, { useEffect, useState } from "react";
import style from "../Assets/Styles/Budget.module.css";
import { Card, Row, Col } from "react-bootstrap";
import Swal from "sweetalert2";
import filterStyle from "../Assets/Styles/Filter.module.css";
import ServiceFile from "../ServiceFile";


const ProjectPage = () => {
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    type: "",
    location: "",
    capacity: "",
    startDate: "",
  });

  const [projects, setProjects] = useState([]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const isEmpty = Object.values(formData).some((val) => val.trim() === "");
    if (isEmpty) {
      Swal.fire({
        icon: "error",
        title: "Error",
        text: "Please fill out all required fields.",
      });
      return;
    }

    setProjects([...projects, formData]);
    setFormData({
      name: "",
      description: "",
      type: "",
      location: "",
      capacity: "",
      startDate: "",
    });
  };

  
  useEffect(() => {
    ServiceFile.getAllProjects()
      .then( (res) => {
        //console.log(res.data);
        setProjects(res.data.map((item)=>({
          name: item.projectName,
          description: item.projectDescription,
          type: item.projectType,
          location: item.projectLocation,
          capacity: item.projectEnergyCapacity,
          startDate: item.projectStartDate,
        })))
      })

      .catch((err) => {
        console.log(err);
      });
  }, [])
  
  return (
    <>
      <Row>
        <div className="pageHeading">All Projects</div>
      </Row>

      {/* Table Section */}
      <div className="fullMainLowerDiv">
        <Row style={{ height: "100%" }}>
          <Col sm={12} style={{ overflow: "hidden", height: "100%" }}>
            <Card className="chartCard" style={{ height: "100%" }}>
              <Card.Body className="d-flex" style={{ overflow: "hidden" }}>
                <div className="tableDiv">
                  <table
                    className="table table-bordered table-tag table-striped"
                    style={{
                      minHeight: projects.length > 0 ? "unset" : "100%",
                    }}
                  >
                    <thead className="table-head">
                      <tr>
                        <th>Project Name</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Location</th>
                        <th>Capacity</th>
                        <th>Start Date</th>
                      </tr>
                    </thead>
                    <tbody>
                      {projects.length > 0 ? (
                        projects.map((proj, index) => (
                          <tr key={index}>
                            <td>{proj.name}</td>
                            <td>{proj.description}</td>
                            <td>{proj.type}</td>
                            <td>{proj.location}</td>
                            <td>{proj.capacity}</td>
                            <td>{proj.startDate}</td>
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

export default ProjectPage;
