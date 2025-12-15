import axios from "axios";

class ServiceFile {
  getAllProjects() {
    return(axios.get("http://localhost:8080/api/v1/projects"));
  }

  getAllBudget() {
    return axios.get("http://localhost:8080/api/v1/budgets");
  }

  getprojectBudget(projectId) {
    return axios.get(`http://localhost:8080/api/v1/budgets/${projectId}`);
  }

  getAllEquipment() {
    return axios.get("http://localhost:8080/api/v1/equipment");
  }

  getAllExpenses() {
    return axios.get("http://localhost:8080/api/v1/expenses");
  }

  getAllMaintenance() {
    return axios.get("http://localhost:8080/api/v1/maintenance");
  }

  saveProject(data){
    return(axios.post("http://localhost:8080/api/v1/projects",
      {
        "projectName": data.projectName,
        "projectDescription": data.description,
        "projectType": data.type,
        "projectLocation": data.location,
        "projectStartDate": data.startDate,
        "projectEnergyCapacity": data.capacity
      }
    ));
  }

  saveBudget(data) {
    return axios.post("http://localhost:8080/api/v1/budgets",
      {
        "projectId": data.projectId,
        //"projectName": data.projectName,
        "budgetType": data.budgetType,
        "budgetAmount": data.amount
      }
    );
  }

  saveEquipment(data) {
    return axios.post("http://localhost:8080/api/v1/equipment",
      {
        "projectId": data.projectId,
        //"projectName": data.projectName,
        "equipmentNumber": data.equipmentNo,
        "equipmentType": data.type,
        "equipmentInstallationDate": data.installationDate,
        "equipmentWarrantyDate": data.warrantyDate,
        "equipmentQuantity": data.quantity
      }
    );
  }

  saveExpenses(data) {
    return axios.post("http://localhost:8080/api/v1/expenses",
      {
        "projectId": data.projectId,
        "budgetId": data.budgetId,
        "expenseDate": data.expenseDate,
        "expenseAmount": data.expenseAmount
      }
    );
  }

  saveMaintenance(data) {
    return axios.post("http://localhost:8080/api/v1/maintenance",
      {
        // "projectId": data.projectId,
        "maintenanceType": data.maintenanceType,
        "equipmentId": data.equipmentId,
        "maintenanceDate": data.maintenanceDate,
        "maintenanceDescription":data.maintenanceDescription,
        "maintenanceStatus": data.maintenanceStatus,
      }
    );
  }
}

export default new ServiceFile();
