import logo from "./logo.svg";
import "./App.css";
import "./Assets/Styles/common.css";
import { BrowserRouter, Switch, Route, Redirect } from "react-router-dom";
import Projects from "./Pages/Projects";
import Login from "./Pages/Login";
import Layout from "./Layout";
import AddProject from "./Pages/AddProject";
import Budget from "./Pages/Budget";
import AddExpenses from "./Pages/AddExpenses";
import AddEquipment from "./Pages/AddEquipment";
import AddMaintenance from "./Pages/AddMaintenance";

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/">
          <Login />
        </Route>
        <Route exact path="/login">
          <Login />
        </Route>
        <Route
          exact
          path="/allProjects"
          render={() => (
            <Layout>
              <Projects />
            </Layout>
          )}
        />
        <Route
          exact
          path="/addProject"
          render={() => (
            <Layout>
              <AddProject />
            </Layout>
          )}
        />
        <Route
          exact
          path="/addBudget"
          render={() => (
            <Layout>
              <Budget />
            </Layout>
          )}
        />
        <Route
          exact
          path="/addExpenses"
          render={() => (
            <Layout>
              <AddExpenses />
            </Layout>
          )}
        />
        <Route
          exact
          path="/addEquipment"
          render={() => (
            <Layout>
              <AddEquipment />
            </Layout>
          )}
        />
        <Route
          exact
          path="/addMaintenance"
          render={() => (
            <Layout>
              <AddMaintenance />
            </Layout>
          )}
        />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
