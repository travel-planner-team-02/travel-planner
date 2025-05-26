import React from "react";
import { Button, Layout } from "antd";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./components/LoginPage";
import UserHomePage from "./components/UserHomePage";
import CityDetailPage from "./components/CityDetailPage";
import { parseJwt } from "./utils";

const { Header, Content } = Layout;

class App extends React.Component {
    state = {
        authed: false,
        username: null,
    }

    componentDidMount() {
        const authToken = localStorage.getItem("authToken");
        if (authToken) {
            const parsed = parseJwt(authToken);
            this.setState({
                authed: true,
                username: parsed?.sub || null,
            });
        }
    }

    handleLoginSuccess = (token) => {
        localStorage.setItem("authToken", token);
        const parsed = parseJwt(token);
        this.setState({
            authed: true,
            username: parsed?.sub || null,
        }, () => {
            window.location.reload();
        });
    }

    handleRegisterSuccess = (token) => {
        localStorage.setItem("authToken", token);
        const parsed = parseJwt(token);
        this.setState({
            authed: true,
            username: parsed?.sub || null,
        }, () => {
            window.location.reload();
        });
    }

    handleLogOut = () => {
        localStorage.removeItem("authToken");
        this.setState({
            authed: false,
            username: null,
        }, () => {
            window.location.reload();
        });
    }

    render() {
        return (
            <Router>
                <Layout style={{ height: "100vh" }}>
                    <Header style={{ display: "flex", justifyContent: "space-between", backgroundColor: "#2c3e50" }}>
                        <div style={{ fontSize: 20, fontWeight: 600, color: "white" }}>
                            Travel Planner
                        </div>
                        <div>
                            {this.state.authed && (
                                <Button onClick={this.handleLogOut} shape="round" type="primary">
                                    LogOut
                                </Button>
                            )}
                        </div>
                    </Header>
                    <Content>
                        <Routes>
                            {/* Login route (default) */}
                            {!this.state.authed ? (
                                <Route
                                    path="*"
                                    element={
                                        <LoginPage
                                            handleLoginSuccess={this.handleLoginSuccess}
                                            handleRegisterSuccess={this.handleRegisterSuccess}
                                        />
                                    }
                                />
                            ) : (
                                <>
                                    {/* Home page route */}
                                    <Route
                                        path="/"
                                        element={
                                            <UserHomePage
                                                username={this.state.username}
                                                authed={this.state.authed}
                                            />
                                        }
                                    />
                                    {/* City detail page route */}
                                    <Route path="/cities/:cityId" element={<CityDetailPage />} />
                                </>
                            )}
                        </Routes>
                    </Content>
                </Layout>
            </Router>
        );
    }
}

export default App;