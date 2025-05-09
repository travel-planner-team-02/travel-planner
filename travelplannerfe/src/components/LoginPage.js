import React from "react";
import { Button, Modal, Form, Input, message, Space } from "antd";
import { login, register } from "../utils";

class LoginPage extends React.Component { 
    loginformRef = React.createRef();
    registerformRef = React.createRef();

    state = {
        loading: false,
        showLoginModal: false,
        showRegisterModal: false
    };

    handleLogin = async () => { 
        const loginformInstance = this.loginformRef.current;

        try {
            await loginformInstance.validateFields();
        } catch (error) { 
            return;
        }

        this.setState({ loading: true });

        try { 
            const resp = await login(loginformInstance.getFieldsValue(true));
            this.props.handleLoginSuccess(resp.token);
            this.setState({ showLoginModal: false });
        } catch (error){
            message.error(error.message);
        } finally {
            this.setState({ loading: false });
        }
    }

    handleRegister = async () => { 
        const registerformRef = this.registerformRef.current;

        try {
            await registerformRef.validateFields();
        } catch (error) {
            return;
        }

        this.setState({ loading: true });

        try {
            const resp = await register(registerformRef.getFieldsValue(true));
            this.props.handleRegisterSuccess(resp.token);
            this.setState({ showRegisterModal : false});
        } catch (error) {
            message.error(error.message);
        } finally {
            this.setState({ loading: false });
        }
    }

    render() { 
        const { showLoginModal, showRegisterModal, loading } = this.state; 
        return (
            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    paddingTop: "15vh",
                    height: "100vh",
                    gap: "16px"
                }}
            >
                <div style={{ fontSize: 20, fontWeight: 600, color: "blue" }}>
                    Explore this wanderful world
                </div>
                <div style={{display:"flex", justifyContent: "space-between", gap: "16px"}}>
                    <Button shape="round" type="primary" style={{ fontSize: 16, fontWeight: 500, height: "auto", padding: "0.6em 1.5em", lineHeight: 1.2, }}
                        onClick={() => this.setState({showLoginModal : true})}
                    >
                        Sign in
                    </Button>
                    <Button shape="round" type="primary" style={{ fontSize: 16, fontWeight: 500, height: "auto", padding: "0.6em 1.5em", lineHeight: 1.2 }}
                        onClick={() => this.setState({showRegisterModal : true})}
                    >
                        Sign on
                    </Button> 
                </div>
                {/*Login Modal*/}
                <Modal
                    title="Login"
                    open={ showLoginModal }
                    onCancel={() => this.setState({ showLoginModal: false })}
                    footer={null}
                    destroyOnClose
                >
                    <Form ref={this.loginformRef}>
                        <Form.Item name="username" rules={[{ required: true,  message: "please input your username!"}]}>
                            <Input placeholder="Username"/>
                        </Form.Item>
                        <Form.Item name="password" rules={[{required: true, message: "please input your password!"}]}>
                            <Input.Password placeholder="Password"/>
                        </Form.Item>
                    </Form>
                    <Space>
                        <Button onClick={this.handleLogin} disabled={loading} shape="round" type="primary">
                            Login
                        </Button>
                    </Space>
                </Modal>
                {/*Register Modal*/}
                <Modal
                    title="Register"
                    open={ showRegisterModal }
                    onCancel={() => this.setState({ showRegisterModal: false })}
                    footer={null}
                    destroyOnClose
                >
                    <Form ref={this.registerformRef}>
                        <Form.Item name="username" rules={[{ required: true,  message: "please input your username!"}]}>
                            <Input placeholder="Username"/>
                        </Form.Item>
                        <Form.Item name="password" rules={[{required: true, message: "please input your password!"}]}>
                            <Input.Password placeholder="Password"/>
                        </Form.Item>
                    </Form>
                    <Space>
                        <Button onClick={this.handleRegister} disabled={loading} shape="round" type="primary">
                            Register
                        </Button>
                    </Space>
                </Modal>
        </div>
        )
    }
}

export default LoginPage