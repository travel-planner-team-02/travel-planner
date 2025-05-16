import React from "react";
import { Button, Layout } from "antd";
import LoginPage from "./components/LoginPage";
import UserHomePage from "./components/UserHomePage";
import {parseJwt} from "./utils"

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
            })
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
            username: null
        }, () => {
            window.location.reload();
        });
    }


    renderContent = () => { 
        if (!this.state.authed) { 
            return <LoginPage
                handleLoginSuccess={this.handleLoginSuccess}
                handleRegisterSuccess={this.handleRegisterSuccess} />
        }

        return <UserHomePage username={this.state.username} authed={ this.state.authed} />
    }

    render() { 
        return (
            <Layout style={{height: "100vh"}}>
                <Header style={{display:"flex", justifyContent: "space-between", backgroundColor: "#2c3e50"}}>
                    <div style={{fontSize: 20, fontWeight: 600, color:"white"}}>
                        Travel Planner
                    </div>
                    <div>
                        { 
                            this.state.authed && (
                                <div>
                                    <Button onClick={this.handleLogOut} shape="round" type="primary">
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
