import React from "react";
import { UserOutlined } from "@ant-design/icons";
import { Button, Dropdown, Layout } from "antd";
import LoginPage from "./components/LoginPage";
import UserHomePage from "./components/UserHomePage";

const { Header, Content } = Layout;

class App extends React.Component { 
    state = {
        authed: false,
    }

    handleLoginSuccess = (token) => { 
        localStorage.setItem("authToken", token);
        this.setState({
            authed: true,
        });
    }
    handleRegisterSuccess = (token) => { 
        localStorage.setItem("authToken", token);
        this.setState({
            authed: true,
        });
    }


    renderContent = () => { 
        if (!this.state.authed) { 
            return <LoginPage
                handleLoginSuccess={this.handleLoginSuccess}
                handleRegisterSuccess={this.handleRegisterSuccess} />
        }

        return <UserHomePage/>
    }

    render() { 
        return (
            <Layout style={{height: "100vh"}}>
                <Header style={{display:"flex", justifyContent: "space-between", backgroundColor: "#34e5eb"}}>
                    <div style={{fontSize: 20, fontWeight: 600, color:"white"}}>
                        Travel Planner
                    </div>
                    <div>
                        { 
                            this.state.authed && (
                                <div>
                                    <Button onClick={() => this.setState({ authed : false })} shape="round" type="primary">
                                        LogOut
                                    </Button>
                                </div>
                            )
                        }
                    </div>
                </Header>
                <Content>
                    {this.renderContent()}
                </Content>
            </Layout>
        );
    }
}

export default App;
